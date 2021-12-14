package com.nickjojo.banking.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nickjojo.banking.entity.Role;
import com.nickjojo.banking.entity.User;
import com.nickjojo.banking.service.EmailService;
import com.nickjojo.banking.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String getLogin(Model model) {
		return "login.html";
	}

	@GetMapping("/login-error")
	public String login(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String errorMessage = null;
		if (session != null) {
			AuthenticationException ex = (AuthenticationException) session
					.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (ex != null) {
				errorMessage = ex.getMessage();
			}
		}
		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}

	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "forgot-password.html";
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
		User theUser = userService.findByEmail(user.getEmail());

		if (theUser == null) {
			model.addAttribute("invalidUser", "No user exists with that email!");
			System.out.println("NO USER WITH THAT EMAIL: ");
		} else {
			System.out.println("USER WITH EMAIL, " + user.getEmail() + ", FOUND!");
			for (Role role : theUser.getRoles()) {
				System.out.println(role.getName());
			}

			// Save token to DB
			theUser.setResetToken(UUID.randomUUID().toString());

			theUser.setTimeStamp(getCurrentTimeForToken());

			userService.save(theUser);

			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

			// Email Message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("nicholasjojo888@gmail.com");
			passwordResetEmail.setTo(theUser.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token="
					+ theUser.getResetToken() + " \n\nThis token will expire in 10 minutes.");

			emailService.sendEmail(passwordResetEmail);

			model.addAttribute("successMessage", "A password reset link has been sent to your email!");
		}
		return "redirect:/forgot-password";
	}

	@GetMapping("/reset")
	public String displayResetPassword(Model model, HttpSession session, @RequestParam("token") String token) {

		System.out.println(token);
		session.setAttribute("userToken", token);

		Optional<User> user = userService.findByResetToken(token);

		if (user.isPresent()) {
			model.addAttribute("token", token);
			model.addAttribute("user", new User());
		} else {
			model.addAttribute("errorMessage", "Invalid password reset token");
		}
		return "reset.html";
	}

	@PostMapping("/reset")
	public String setResetPassword(Model model, @ModelAttribute("user") User user, HttpServletRequest request,
			HttpSession session) {
		String token = session.getAttribute("userToken").toString();
		System.out.println(session.getAttribute("userToken").toString() + " IS THE TOKEN!");

		Optional<User> theUser = userService.findByResetToken(token);

		if (theUser.isPresent()) {
			User realUser = theUser.get();

			String timeStamp = new SimpleDateFormat("dd.HH.mm").format(new Date());
			String userTimeStamp = realUser.getTimeStamp();
			String[] currentTimeArr = timeStamp.split("\\.");
			String[] userTimeArr = userTimeStamp.split("\\.");

			if ((Integer.parseInt(currentTimeArr[2]) > Integer.parseInt(userTimeArr[2]))
					&& (Integer.parseInt(currentTimeArr[0]) == (Integer.parseInt(userTimeArr[0])))) {
				realUser.setResetToken(null);
				realUser.setTimeStamp(null);
				return "redirect:/tokenexpired";
			} else {

				realUser.setPassword(passwordEncoder.encode(user.getPassword()));

				realUser.setResetToken(null);
				realUser.setTimeStamp(null);

				userService.save(realUser);

				model.addAttribute("successReset", "You have successfully reset your password!");
				return "redirect:/login";
			}
		} else {
			return "redirect:/forgot-password";
		}

	}

	@GetMapping("/tokenexpired")
	public String tokenExpired() {
		return "token-expired.html";
	}

	public String getCurrentTimeForToken() {
		String timeStamp = new SimpleDateFormat("dd.HH.mm").format(new Date());
		System.out.println(timeStamp);

		String[] timeStampIntArr = timeStamp.split("\\.");

		int currentTimeMins = Integer.parseInt(timeStampIntArr[2]);
		int currentTimeHours = Integer.parseInt(timeStampIntArr[1]);
		if (currentTimeMins < 50) {
			currentTimeMins = currentTimeMins + 10;
			String newTimeStamp = new SimpleDateFormat("dd.HH.").format(new Date()) + Integer.toString(currentTimeMins);
			return newTimeStamp;
		} else {
			if (currentTimeMins >= 50) {
				currentTimeMins = (currentTimeMins + 10) - 60;
				currentTimeHours = currentTimeHours + 1;
				String newTimeStamp = new SimpleDateFormat("dd.").format(new Date())
						+ Integer.toString(currentTimeHours) + "." + Integer.toString(currentTimeMins);
				return newTimeStamp;
			}
		}
		return timeStamp;

	}

}
