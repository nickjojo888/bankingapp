package com.nickjojo.banking;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nickjojo.banking.controller.BankingController;
import com.nickjojo.banking.entity.UserDetailsServiceImpl;
import com.nickjojo.banking.service.DepositService;
import com.nickjojo.banking.service.DepositServiceImpl;
import com.nickjojo.banking.service.EmailService;
import com.nickjojo.banking.service.EmailServiceImpl;
import com.nickjojo.banking.service.RoleService;
import com.nickjojo.banking.service.RoleServiceImpl;
import com.nickjojo.banking.service.SecurityService;
import com.nickjojo.banking.service.SecurityServiceImpl;
import com.nickjojo.banking.service.StockService;
import com.nickjojo.banking.service.StockServiceImpl;
import com.nickjojo.banking.service.TransactionService;
import com.nickjojo.banking.service.TransactionServiceImpl;
import com.nickjojo.banking.service.UserService;
import com.nickjojo.banking.service.UserServiceImpl;
import com.nickjojo.banking.service.UserStocksService;
import com.nickjojo.banking.service.UserStocksServiceImpl;
import com.nickjojo.banking.service.WithdrawService;
import com.nickjojo.banking.service.WithdrawServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = "com.nickjojo.banking")
@EnableJpaRepositories(basePackages = { "com.nickjojo.banking.repository" })
@EnableScheduling
@EnableAsync
public class BankingApplication extends WebMvcConfigurerAdapter implements CommandLineRunner, SchedulingConfigurer {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

	@Bean
	public UserDetailsService getUserDetails() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public EmailService emailService() {
		return new EmailServiceImpl();
	}

	@Bean
	public WithdrawService withdrawService() {
		return new WithdrawServiceImpl();
	}

	@Bean
	public DepositService depositService() {
		return new DepositServiceImpl();
	}

	@Bean
	public StockService stockService() {
		return new StockServiceImpl();
	}

	@Bean
	public RoleService roleService() {
		return new RoleServiceImpl();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public SecurityService securityService() {
		return new SecurityServiceImpl();
	}

	@Bean
	public TransactionService transactionService() {
		return new TransactionServiceImpl();
	}

	@Bean
	@Qualifier("UserStocksServiceImpl")
	public UserStocksService userStocksService() {
		return new UserStocksServiceImpl();
	}

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(10);
		taskScheduler.initialize();
		return taskScheduler;
	}

	@Bean(name = "threadPoolTaskExecutor")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());

	}
 

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("mcafos@gmail.com");
		mailSender.setPassword("28829499zoe");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		return mailSender;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	


}
