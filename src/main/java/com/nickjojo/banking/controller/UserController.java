package com.nickjojo.banking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nickjojo.banking.entity.Deposit;
import com.nickjojo.banking.entity.HistoricTransaction;
import com.nickjojo.banking.entity.Transaction;
import com.nickjojo.banking.entity.User;
import com.nickjojo.banking.entity.Withdraw;
import com.nickjojo.banking.service.DepositService;
import com.nickjojo.banking.service.TransactionService;
import com.nickjojo.banking.service.UserService;
import com.nickjojo.banking.service.WithdrawService;

@Controller("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private WithdrawService withdrawService;
	@GetMapping("/user/history")
	public String getHistory(Model model) {
		
		User user = getCurrentUser();
		
		// overload a history transaction class
		List<Transaction> transactions = transactionService.findAllByUser_AccountNumberOrderByDateDesc(user.getAccountNumber());
		
		model.addAttribute("transactions", transactions);
		
		return "history.html";

	}

	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return userService.findByUsername(((UserDetails) principal).getUsername());
		} else {
			System.out.println("User not logged in");
			return null;
		}
	}
}
