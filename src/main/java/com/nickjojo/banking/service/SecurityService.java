package com.nickjojo.banking.service;

import java.util.List;

import com.nickjojo.banking.entity.Security;

public interface SecurityService {

	List<Security> findAllByUser_AccountNumber(String accountNumber);
	
	void save(Security security);
	
	void deleteById(Long id);
	
	void delete(Security security);
}
