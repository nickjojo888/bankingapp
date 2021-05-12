package com.nickjojo.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);

	}

}
