package com.nickjojo.banking.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	public void sendEmail(SimpleMailMessage email);
	
}
