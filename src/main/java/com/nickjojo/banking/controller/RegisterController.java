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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
	public RedirectView register(Model model, @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		if ((userService.findByUsername(user.getUsername()) != null)) {
			
			redirectAttributes.addFlashAttribute("errorRegister", "\nUser already exists with that username or email!\n");
					return new RedirectView("/register");
		} else if(user.getPassword().length() < 8) {
			redirectAttributes.addFlashAttribute("lengthRegister", "\nPassword has to be of minimum length: 8\n");
			return new RedirectView("/register");
		} else if(!user.getConfirmPassword().equals(user.getPassword())) {
			redirectAttributes.addFlashAttribute("passwordRegister", "\n'Password' and 'Confirm Password' do not match.\n");
			return new RedirectView("/register");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setBalance(25000);
		user.setEnabled(true);
		user.setAccountNumber(generateAccountNumber(user.getFirstName(), user.getLastName()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.findRoleByRole_Id((long) 1).get());
		user.setRoles(roles);
		userService.save(user);
		for (String stockTicker : defaultStartingStocks) {
			UserStockTicker userStock = new UserStockTicker(user, stockTicker);
			userStocksService.save(userStock);
		}
		System.out.println(user.getAccountNumber() + " has just registered!");
		redirectAttributes.addFlashAttribute("successRegister", "Successfully registered using email: " + user.getEmail());

		return new RedirectView("/register");
	}

	public String generateAccountNumber(String firstName, String lastName) {
		Random rand = new Random();
		String card = firstName.substring(0, 1) +lastName.substring(0, 1);
		for (int i = 0; i < 14; i++) {
			int n = rand.nextInt(10) + 0;
			card += Integer.toString(n);
		}
		List<User> users = userService.findAll();
		for (User u : users) {
			if (u.getAccountNumber().equals(card)) {
				generateAccountNumber(firstName, lastName);
			}
		}
		return card;
	}

}
