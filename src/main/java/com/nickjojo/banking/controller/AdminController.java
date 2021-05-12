package com.nickjojo.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nickjojo.banking.service.TransactionService;

@Controller
public class AdminController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/admin")
	public String getAdmin(Model model) {
		double thisMonthVolume = transactionService.getVolume();

		model.addAttribute("volume", thisMonthVolume);

		return "admin.html";
	}
}
