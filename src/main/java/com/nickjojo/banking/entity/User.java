package com.nickjojo.banking.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "accountNumber")
	private String accountNumber;

	@Column(name = "balance")
	private double balance;

	@Column(name = "enabled")
	private boolean enabled;

	@OneToMany(mappedBy = "user")
	private List<Deposit> deposits;

	@OneToMany(mappedBy = "user")
	private List<Withdraw> withdrawals;

	@Column(name = "reset_token")
	private String resetToken;

	@Column(name = "timestamp")
	private String timeStamp;
	
	private String confirmPassword;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

//	// EAGER loads all relationships related to the parent object
//	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//	private Set<Role> roles = new HashSet<>();

	public User() {

	}

	public User(String username, String password, String email, String accountNumber, double balance, boolean enabled) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Deposit> getDeposits() {
		return deposits;
	}

	public void setDeposits(List<Deposit> deposits) {
		this.deposits = deposits;
	}

	public List<Withdraw> getWithdrawals() {
		return withdrawals;
	}

	public void setWithdrawals(List<Withdraw> withdrawals) {
		this.withdrawals = withdrawals;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
