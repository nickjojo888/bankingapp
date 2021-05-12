package com.nickjojo.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getHome(Model model) {
		return "index.html";
	}
	
	@GetMapping("/403")
	public String getAccessDenied() {
		return "403.html";
	}

}
