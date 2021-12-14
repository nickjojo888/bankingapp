package com.nickjojo.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// private variables allow for encapsulation
	// which is beneficial because it hides
	// the contents of the class and its data
	// so it cant be manipulated by others

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsServiceImpl;

	@Autowired
	private SessionRegistry sessionRegistry;

	@Bean
	DaoAuthenticationProvider provider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/banking/**").hasAnyRole("USER", "ADMIN").and().formLogin().loginPage("/login")
				.permitAll().failureUrl("/login-error").defaultSuccessUrl("/banking").and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/403");

		http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry);
	}

}
