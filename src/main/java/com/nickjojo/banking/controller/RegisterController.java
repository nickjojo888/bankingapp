package com.nickjojo.banking.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nickjojo.banking.entity.Role;
import com.nickjojo.banking.entity.User;
import com.nickjojo.banking.entity.UserStockTicker;
import com.nickjojo.banking.service.RoleService;
import com.nickjojo.banking.service.UserService;
import com.nickjojo.banking.service.UserStocksService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserStocksService userStocksService;

	private String[] defaultStartingStocks = { "AAPL", "BABA", "MSFT", "AXP", "BA", "AMD", "TSLA", "MA", "SHOP",
			"GOOGL", "KL" };

	@GetMapping("/register")
	public String getRegister(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register.html";
	}

	@PostMapping("/register")
	public String register(Model model, @ModelAttribute("user") User user) {
		if ((userService.findByUsername(user.getUsername()) != null)) {
			model.addAttribute("errorRegister", "User already exists with that username or email!");
			return "redirect:/register";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setBalance(25000);
		user.setEnabled(true);
		user.setAccountNumber(generateAccountNumber());
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.findRoleByRole_Id((long) 1).get());
		user.setRoles(roles);
		userService.save(user);

		// starting stocks
		
		for (String stockTicker : defaultStartingStocks) {
			UserStockTicker userStock = new UserStockTicker(user, stockTicker);
			userStocksService.save(userStock);
		
		}

		model.addAttribute("successRegister", "Successfully registered using email: " + user.getEmail());

		System.out.println(user.getAccountNumber() + " has just registered!");
		return "redirect:/login";
	}

	public String generateAccountNumber() {

		Random rand = new Random();
		String card = "NJ";
		for (int i = 0; i < 14; i++) {
			int n = rand.nextInt(10) + 0;
			card += Integer.toString(n);
		}
		for (int i = 0; i < 16; i++) {
			if (i % 4 == 0)
				System.out.print(" ");
			System.out.print(card.charAt(i));
		}

		List<User> users = userService.findAll();
		for (User u : users) {
			if (u.getAccountNumber().equals(card)) {

				// recursive algo if the acc num exists
				generateAccountNumber();
			}
		}

		return card;
	}

}
