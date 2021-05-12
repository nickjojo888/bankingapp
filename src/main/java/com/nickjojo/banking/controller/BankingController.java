package com.nickjojo.banking.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nickjojo.banking.entity.Deposit;
import com.nickjojo.banking.entity.Message;
import com.nickjojo.banking.entity.Security;
import com.nickjojo.banking.entity.StockDto;
import com.nickjojo.banking.entity.StockTransfer;
import com.nickjojo.banking.entity.Transaction;
import com.nickjojo.banking.entity.TransactionType;
import com.nickjojo.banking.entity.User;
import com.nickjojo.banking.entity.UserStockTicker;
import com.nickjojo.banking.entity.Withdraw;
import com.nickjojo.banking.service.DepositService;
import com.nickjojo.banking.service.EmailService;
import com.nickjojo.banking.service.RoleService;
import com.nickjojo.banking.service.SecurityService;
import com.nickjojo.banking.service.StockService;
import com.nickjojo.banking.service.TransactionService;
import com.nickjojo.banking.service.UserService;
import com.nickjojo.banking.service.UserStocksService;
import com.nickjojo.banking.service.WithdrawService;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Controller()
public class BankingController {

	// in stocks page , put the original html inside the stockbody, but upon
	// connect() method, clear it.
	// too much data being transmitted, make it so that the StockTransfer class is
	// only a list of the serializable stockdto class with the data which is
	// necessary.
	// connect automatically without connect button on stocks.html
	@Autowired
	private UserService userService;

	@Autowired
	private WithdrawService withdrawService;

	@Autowired
	private DepositService depositService;

	@Autowired
	private StockService stockService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SessionRegistry sessionRegistry;

	@Autowired
	private UserStocksService userStocksService;

	@Autowired
	private EmailService emailService;

	private ScriptEngineManager manager = new ScriptEngineManager();
	private ScriptEngine engine = manager.getEngineByName("JavaScript");

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Column(name = "date")
	@JsonFormat(pattern = "DD/MM/YYYY")
	private LocalDateTime date;

	private static final String DESTINATION = "/topic/stocks";

	@GetMapping("/banking")
	public String getIndex(Model model) throws IOException {
//		stack.push("/banking");
		User user = getCurrentUser();

		// Stock Holdings:
		List<Security> securities = securityService.findAllByUser_AccountNumber(user.getAccountNumber());

		// User Details
		model.addAttribute("username", user.getUsername());
		model.addAttribute("roles", user.getRoles());

		model.addAttribute("userFirstName", user.getFirstName());
		model.addAttribute("userLastName", user.getLastName());
		model.addAttribute("userBalance", user.getBalance());
		model.addAttribute("userAccountNumber", user.getAccountNumber());

		// User - Total Portfolio Value
		double balance = user.getBalance();
		for (Security s : securities) {

			balance += s.getAmount();
		}

		double roundOff = (double) Math.round(balance * 100) / 100;
		double todaysChange = todaysChange(user);

		model.addAttribute("totalValue", roundOff);
		model.addAttribute("securities", securities);
		model.addAttribute("todaysChange", Math.round(todaysChange));

		double todaysChangePercent = ((todaysChange / (roundOff - todaysChange)) * 100);
		String todaysChangePercentText = String.format("%.2f", todaysChangePercent);
		model.addAttribute("todaysChangePercent", todaysChangePercentText);
		return "banking.html";
	}

//todo: for more accurate future change, create a check to see when the stock was purchased, and if it was purchased on this day
// if it was, get the pricing for the stock at that time on this day
	public double todaysChange(User user) throws IOException {

		List<Security> userSecurities = securityService.findAllByUser_AccountNumber(user.getAccountNumber());

		double todaysChangeValue = 0;

		for (Security security : userSecurities) {
			String symbol = security.getStockCode();
			double dayChange = YahooFinance.get(symbol).getQuote().getChangeInPercent().doubleValue();
			double currentPrice = YahooFinance.get(symbol).getQuote().getPrice().doubleValue();
			double previousPrice = 0;
			System.out.println("current price: " + currentPrice);
			System.out.println(" DAYCHANGE : " + dayChange + "%");
			if (dayChange > 0.0) {
				previousPrice = (1 - (Math.abs(dayChange) / 100)) * currentPrice;
				System.out.println("previous price: " + previousPrice);
			} else if (dayChange < 0.0) {
				previousPrice = (1 + (Math.abs(dayChange) / 100)) * currentPrice;
				System.out.println("previous price (below 0.0 change): " + previousPrice);

			}
			double change = currentPrice - previousPrice;
			todaysChangeValue += (change * security.getQuantity());

			System.out.println(symbol + " " + dayChange);

		}
		return todaysChangeValue;

	}

	@GetMapping("/banking/withdraw")
	public String getWithdraw(Model model) {
//		stack.push("/banking/withdraw");
		User user = getCurrentUser();
		model.addAttribute("username", user.getUsername());
		Withdraw withdraw = new Withdraw();
		model.addAttribute("withdraw", withdraw);
		return "withdraw.html";
	}

	@PostMapping("/banking/withdraw")
	public String postWithdraw(@ModelAttribute("withdraw") Withdraw withdraw, Model model) {
		Optional<User> withdrawToUser = userService.findUserByAccountNumber(withdraw.getToNumber());
		if (withdrawToUser.isPresent()) {
			if (getCurrentUser().getBalance() >= withdraw.getAmount()) {
				User theUser = withdrawToUser.get();
				theUser.setBalance(theUser.getBalance() + withdraw.getAmount());
				this.getCurrentUser().setBalance(getCurrentUser().getBalance() - withdraw.getAmount());
				withdraw.setUser(getCurrentUser());
				withdrawService.save(withdraw);
				model.addAttribute("username", getCurrentUser().getUsername());
				model.addAttribute("userFirstName", getCurrentUser().getFirstName());
				model.addAttribute("userLastName", getCurrentUser().getLastName());
				model.addAttribute("amountWithdrawn", withdraw.getAmount());
				model.addAttribute("toNumber", withdraw.getToNumber());

				return "withdrawSuccess.html";
			} else {
				model.addAttribute("noBalance", "Insufficient balance to cover withdrawal");
				return "withdraw.html";
			}

		} else if (withdrawToUser.get() == null) {
			model.addAttribute("noAccount", "Account Number Does Not Exist!");
			return "withdraw.html";
		}

		return "withdrawSuccess.html";
	}

	@GetMapping("/banking/deposit")
	public String getDeposit(Model model) {
//		stack.push("/banking/deposit");

		model.addAttribute("deposit", new Deposit());
		model.addAttribute("username", getCurrentUser().getUsername());
		model.addAttribute("accountNumber", new String());
		List<String> accountNumbers = new ArrayList<>();
		for (User user : userService.findAllByEmail(getCurrentUser().getEmail())) {
			accountNumbers.add(user.getAccountNumber());
		}
		model.addAttribute("accountNumber", new String());
		model.addAttribute("accountNumbers", accountNumbers);

		return "deposit.html";
	}

	@PostMapping("/banking/deposit")
	public String postDeposit(@ModelAttribute("deposit") Deposit deposit, Model model) {
		Optional<User> tempUser = userService.findUserByAccountNumber(deposit.getAccountNumber());
		System.out.println(deposit.getAccountNumber() + " IS ACC NUM");
		if (deposit.getAmount() <= 0) {
			System.out.println("deposit isn't a valid number");
		} else {
			if (tempUser.isPresent()) {
				User user = tempUser.get();
				user.setBalance(user.getBalance() + deposit.getAmount());
				deposit.setUser(user);
				depositService.save(deposit);
				model.addAttribute("userFirstName", getCurrentUser().getFirstName());
				model.addAttribute("userLastName", getCurrentUser().getLastName());
				model.addAttribute("amountDeposited", deposit.getAmount());
				return "depositSuccess.html";
			}
		}
		return "redirect:/banking/deposit";
	}

	@GetMapping("/banking/stocks/{symbol}")
	public String getStock(Model model, @PathVariable("symbol") String symbol) throws IOException {
//		stack.push("/banking/stocks/symbol");

		Stock stock = stockService.getStock(symbol);
		model.addAttribute("stock", stock);
		model.addAttribute("security", new Security());
		List<Security> securities = securityService
				.findAllByUser_AccountNumber(this.getCurrentUser().getAccountNumber());
		for (Security s : securities) {
			s.setAveragePrice((s.getAveragePrice() * 100) / 100);
			if (s.getStockCode().equals(symbol)) {
				model.addAttribute("availableShares", s.getQuantity());
			}

		}
		return "stock-page.html";
	}

	// add transactions for the transactions table
	@PostMapping("/banking/stock-search/buy/{symbol}")
	public RedirectView buyStock(Model model, @PathVariable("symbol") String symbol,
			@ModelAttribute("security") Security security, RedirectAttributes redirectAttributes) throws IOException {

		User theUser = this.getCurrentUser();
		Security alreadyOwnSecurity = null;

		security.setStockCode(symbol);

		security.setAmount(YahooFinance.get(security.getStockCode()).getQuote().getPrice().doubleValue()
				* (double) security.getQuantity());

		List<Security> usersSecurities = securityService.findAllByUser_AccountNumber(theUser.getAccountNumber());

		if (marketOpen() == true) {
			if (theUser.getBalance() >= security.getAmount()) {

				if (usersSecurities.isEmpty() == false) {

					for (Security s : usersSecurities) {

						System.out.println(s.getUser().getAccountNumber());

						if (s.getStockCode().equals(symbol)) {
							alreadyOwnSecurity = s;
							security.setUser(theUser);

							theUser.setBalance(theUser.getBalance() - security.getAmount());
							userService.save(theUser);

							// avg price calculation
							System.out.println("Already own security avg: " + alreadyOwnSecurity.getAveragePrice());
							System.out.println("new security avg: " + security.getAveragePrice());

							double alreadyOwnAmount = alreadyOwnSecurity.getAveragePrice()
									* alreadyOwnSecurity.getQuantity();
							double newSecurityAmount = security.getAveragePrice() * security.getQuantity();
							int newQuantity = alreadyOwnSecurity.getQuantity() + security.getQuantity();

							double newPrice = (newSecurityAmount + alreadyOwnAmount) / newQuantity;

							security.setQuantity(alreadyOwnSecurity.getQuantity() + security.getQuantity());
							security.setAmount(
									(double) Math.round((security.getAmount() + alreadyOwnSecurity.getAmount()) / 100)
											* 100);
							security.setAveragePrice((double) Math.round(newPrice * 100) / 100);
							System.out.println(security.getAveragePrice());

							securityService.save(security);

							securityService.delete(alreadyOwnSecurity);
							Transaction transaction = new Transaction(theUser, symbol, TransactionType.STOCK_BUY,
									security.getQuantity(),
									stockService.getStock(symbol).getQuote().getPrice().doubleValue(),
									LocalDateTime.now());

							transactionService.save(transaction);
						}
						security.setUser(theUser);

						// avg price
						double price = security.getAmount() / security.getQuantity();
						double securityRoundOff = (double) Math.round(price * 100) / 100;
						security.setAveragePrice(securityRoundOff);
						securityService.save(security);

					}
					theUser.setBalance(theUser.getBalance() - security.getAmount());
					userService.save(theUser);
					security.setUser(theUser);
					securityService.save(security);

				} else {

					Security theSecurity = new Security(theUser, symbol, security.getQuantity(), security.getAmount());

					// avg price
					double price = theSecurity.getAmount() / theSecurity.getQuantity();
					double securityRoundOff = (double) Math.round(price * 100) / 100;
					theSecurity.setAveragePrice(securityRoundOff);
					securityService.save(theSecurity);
					theUser.setBalance(theUser.getBalance() - theSecurity.getAmount());

					userService.save(theUser);

					// error: the lag between the fetching of the security price/saving the
					// transaction and the purchase price may pose some issues
					// one solution is to save the price bought at inside the security or divide the
					// amount by the quantity of shares to get purchase price
					Transaction transaction = new Transaction(theUser, symbol, TransactionType.STOCK_BUY,
							security.getQuantity(), stockService.getStock(symbol).getQuote().getPrice().doubleValue(),
							LocalDateTime.now());

					transactionService.save(transaction);

					redirectAttributes.addFlashAttribute("successfulBuy", "You have bought " + security.getQuantity()
							+ " shares of " + security.getStockCode() + " for $" + security.getAmount());
					return new RedirectView("/banking/stocks/" + symbol);

				}

			} else {
				redirectAttributes.addFlashAttribute("insufficientFunds",
						"You do not have enough money to buy this amount");
				return new RedirectView("/banking/stocks/" + symbol);
			}
		} else {
			DateTimeZone zone = DateTimeZone.forID("America/New_York");
			DateTime dt = new DateTime(zone);
			System.out.println("Stock market isn't open yet. Time is : " + dt.toDateTime());
			redirectAttributes.addFlashAttribute("notOpen", "Stock market is not open!");
			return new RedirectView("/banking/stocks/" + symbol);
		}
		return new RedirectView("/banking/stocks/" + symbol);
	}

	@PostMapping("/banking/stock-search/sell/{symbol}")
	public RedirectView sellStock(Model model, @PathVariable("symbol") String symbol,
			@ModelAttribute("security") Security security, RedirectAttributes redirectAttributes) throws IOException {

		User theUser = this.getCurrentUser();
		Stock stock = this.stockService.getStock(symbol);
		Security theSecurity = null;

		if (securityService.findAllByUser_AccountNumber(theUser.getAccountNumber()) != null) {
			List<Security> theSecurities = securityService.findAllByUser_AccountNumber(theUser.getAccountNumber());
			for (Security s : theSecurities) {
				if (s.getStockCode().equals(symbol)) {
					theSecurity = s;
				}
			}

			if (theSecurity == null) {
				redirectAttributes.addFlashAttribute("noStock", "You do not own this stock.");
				return new RedirectView("/banking/stocks/" + symbol);
			}

			if ((security.getAmount() > theSecurity.getAmount())) {

				redirectAttributes.addFlashAttribute("invalidAmount", "Amount exceeds current balance");
				return new RedirectView("/banking/stocks/" + symbol);
			} else {
				if (marketOpen() == true) {
					int newQuantity = theSecurity.getQuantity() - security.getQuantity();
					theSecurity.setQuantity(newQuantity);

					theSecurity
							.setAmount((YahooFinance.get(theSecurity.getStockCode()).getQuote().getPrice().doubleValue()
									* theSecurity.getQuantity()));

					if (newQuantity <= 0) {
						securityService.deleteById(theSecurity.getId());
						Transaction transaction = new Transaction(theUser, symbol, TransactionType.STOCK_SELL,
								security.getQuantity(),
								stockService.getStock(symbol).getQuote().getPrice().doubleValue(), LocalDateTime.now());

						transactionService.save(transaction);
					} else {
						securityService.save(theSecurity);

					}

					theUser.setBalance(theUser.getBalance()
							+ (YahooFinance.get(theSecurity.getStockCode()).getQuote().getPrice().doubleValue()
									* (double) security.getQuantity()));

					userService.save(theUser);
					Transaction transaction = new Transaction(theUser, symbol, TransactionType.STOCK_SELL,
							security.getQuantity(), stockService.getStock(symbol).getQuote().getPrice().doubleValue(),
							LocalDateTime.now());

					transactionService.save(transaction);
					redirectAttributes.addFlashAttribute("successSell", "Successfully sold " + theSecurity.getQuantity()
							+ " shares of " + theSecurity.getStockCode());
					return new RedirectView("/banking/stocks/" + symbol);

				} else {
					DateTimeZone zone = DateTimeZone.forID("America/New_York");
					DateTime dt = new DateTime(zone);
					System.out.println("Stock market isn't open yet. Time is : " + dt.toDateTime().toString());
					redirectAttributes.addFlashAttribute("notOpen", "Stock market is not open!");
					return new RedirectView("/banking/stocks/" + symbol);
				}
			}
		}

		return new RedirectView("/banking/stocks/" + symbol);
	}

	@PostMapping("/banking/stock-search")
	public String searchStock(Model model, @ModelAttribute("stock") StockDto stock) throws IOException {
		if (stockService.getStock(stock.getName()) != null) {
			Stock theStock = stockService.getStock(stock.getName());
			model.addAttribute("theStock", theStock);
			return "stock-search.html";
		} else {
			model.addAttribute("unrealStock", "This stock does not exist.");
			return "redirect:/banking/stocks";

		}
	}

	@PostMapping("/banking/stocks/removeUserStockTicker/{ticker}")
	public String removeUserStockTicker(Model model, @PathVariable("ticker") String ticker) {
		User user = this.getCurrentUser();
		UserStockTicker stockTicker = new UserStockTicker(user, ticker);
		List<UserStockTicker> userStocks = userStocksService.findAllByUser_AccountNumber(user.getAccountNumber());

		for (UserStockTicker userStockTicker : userStocks) {
			if (userStockTicker.getTicker().equals(stockTicker.getTicker())) {
				System.out.println(userStockTicker.getTicker() + " " + stockTicker.getTicker());
				userStocksService.remove(stockTicker);
				System.out.println("removed stock!");
				return "redirect:/banking/stocks";

			}
		}
		System.out.println("error occurred, user does not have this stock");

		return "redirect:/banking/stocks";
	}

	@PostMapping("/banking/stocks/addUserStockTicker/{ticker}")
	public String addUserStockTicker(Model model, @PathVariable("ticker") String ticker) {
		User user = this.getCurrentUser();
		UserStockTicker stockTicker = new UserStockTicker(user, ticker);
		List<UserStockTicker> userStocks = userStocksService.findAllByUser_AccountNumber(user.getAccountNumber());

		for (UserStockTicker userStockTicker : userStocks) {
			if (userStockTicker.getTicker().equals(stockTicker.getTicker())) {
				System.out.println("stock already in watchlist");
				return "redirect:/banking/stocks";
			}
		}
		System.out.println("stock, " + stockTicker.getTicker() + ", added to watchlist");
		userStocksService.save(stockTicker);
		return "redirect:/banking/stocks";

	}

	@GetMapping("/banking/contact-us")
	public String contactUs(Model model) {
		Message message = new Message();
		model.addAttribute("message", message);
		return "contact-us.html";
	}

	@PostMapping("/banking/contact-us/")
	public RedirectView sendContactForm(@ModelAttribute("message") Message message,
			RedirectAttributes redirectAttributes) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(message.getTitle());
		email.setFrom(message.getEmail());
		email.setTo("mcafos@gmail.com");
		email.setText(message.getEmail());
		try {
			emailService.sendEmail(email);
			redirectAttributes.addFlashAttribute("emailSent", "Email has been sent!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("couldn't send email");
		}
		return new RedirectView("/banking/contact-us/");
	}

	public User getCurrentUser() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return userService.findByUsername(((UserDetails) principal).getUsername());

		}
		System.out.println("No user logged in");
		return null;
	}

	public boolean marketOpen() {

		DateTimeZone zone = DateTimeZone.forID("America/New_York");
		DateTime dt = new DateTime(zone);
		int minutes = dt.getMinuteOfDay();
		int hours = dt.getHourOfDay();
		System.out.println("HOURS: " + hours);
		System.out.println("hours");
		if ((hours > 9 || hours == 9) && (minutes > 30 || minutes == 30) && (hours < 16 || hours == 16)) {
			return true;
		}

		return false;
	}

	@GetMapping("/banking/stocks")
	public String stocks(Model model) throws NoSuchMethodException, IOException {

		model.addAttribute("stock", new StockDto());

		List<Stock> stocks = stockService.getDefaultStocks(getCurrentUser());
		model.addAttribute("stocks", stocks);

		return "stocks.html";
	}

	@GetMapping("/banking/stocks/history")
	public String getTransactionHistory(Model model) {
		List<UserDetails> users = getAllPrincipals();

		for (UserDetails u : users) {
			User user = userService.findByUsername(u.getUsername());
			List<Transaction> transactions = transactionService.findAllByUser_AccountNumber(user.getAccountNumber());
			Transaction t = new Transaction();
			model.addAttribute("transaction", t);
			model.addAttribute("transactions", transactions);

		}
		return "banking-history.html";
	}

	@PostMapping("/banking/stocks/history/search/{searchTerm}")
	public String getSpecificTransactionHistory(Model model, @PathVariable("searchTerm") String searchTerm) {
		List<UserDetails> users = getAllPrincipals();
		Transaction t = (Transaction) model.getAttribute("transaction");
		
		for (UserDetails u : users) {
			User user = userService.findByUsername(u.getUsername());
			List<Transaction> transactions = transactionService
					.findAllByUser_AccountNumberAndStockCode(user.getAccountNumber(), searchTerm);
			model.addAttribute("newTrans", transactions);

		}
		return "banking-history-specific.html";
	}

	// Cannot retrieve principal in a @Scheduled method, because
	// method call isn't initiated by a user. Therefore,
	// we need to call the user via the use of session principal
	// and convert it into an object.

	// A method annotated with @Scheduled is meant to be run separately, on a
	// different thread at a moment in time.
	@Scheduled(fixedRate = 60000)
	public void updatePortfolio() {

		List<UserDetails> principals = getAllPrincipals();
		for (UserDetails u : principals) {
			User user = userService.findByUsername(u.getUsername());
			for (Security s : securityService.findAllByUser_AccountNumber(user.getAccountNumber())) {

				try {
					s.setAmount(((YahooFinance.get(s.getStockCode()).getQuote().getPrice().doubleValue()
							* s.getQuantity())));

					System.out.println("quantity: " + s.getQuantity());
					securityService.save(s);
					System.out.println("saved user securities");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	@MessageMapping("/stocks")
//	@SendTo("/topic/stocks")
	@Scheduled(fixedRate = 3000)
	public void retrieveData() throws Exception {
		// if market is open, make fixed rate a constant defined at top. Else, only
		// catch the data once until market opens
		List<UserDetails> principals = getAllPrincipals();

		for (UserDetails u : principals) {
			User user = userService.findByUsername(u.getUsername());

			List<Stock> stocks = stockService.getDefaultStocks(user);
			List<StockDto> dtoStocks = new ArrayList<StockDto>();
			for (Stock s : stocks) {
				StockDto stockDto = new StockDto(s.getSymbol(), s.getName(), s.getQuote().getPrice(),
						s.getQuote().getChangeInPercent(), s.getDividend().getAnnualYield(), s.getQuote().getOpen());
				dtoStocks.add(stockDto);

			}

			StockTransfer stockTransfer = new StockTransfer(dtoStocks);
			simpMessagingTemplate.convertAndSend(DESTINATION, stockTransfer);
		}
	}

	@MessageMapping("/stocks/{name}")
	@Scheduled(fixedDelay = 3000)
	public void stockData() throws Exception {
		List<UserDetails> principals = getAllPrincipals();
		for (UserDetails u : principals) {
			User user = userService.findByUsername(u.getUsername());

			List<Stock> stocks = stockService.getDefaultStocks(user);
			for (Stock s : stocks) {
				StockDto stockDto = new StockDto(s.getSymbol(), s.getName(), s.getQuote().getPrice(),
						s.getQuote().getAsk(), s.getQuote().getBid(), s.getDividend().getAnnualYield(),
						s.getQuote().getChangeInPercent());
				simpMessagingTemplate.convertAndSend("/topic/stocks/" + s.getSymbol(), stockDto);

			}
		}
	}

	public List<UserDetails> getAllPrincipals() {
		List<UserDetails> principals = sessionRegistry.getAllPrincipals().stream()
				.filter(principal -> principal instanceof UserDetails).map(UserDetails.class::cast)
				.collect(Collectors.toList());
		return principals;
	}

}
