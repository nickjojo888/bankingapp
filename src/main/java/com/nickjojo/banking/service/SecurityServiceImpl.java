package com.nickjojo.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickjojo.banking.entity.Security;
import com.nickjojo.banking.repository.SecurityRepository;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private SecurityRepository securityRepository;

	@Override
	public void save(Security security) {
		securityRepository.save(security);
	}


	@Override
	public List<Security> findAllByUser_AccountNumber(String accountNumber) {
		return securityRepository.findAllByUser_AccountNumber(accountNumber);
	
	}


	@Override
	public void deleteById(Long id) {
		securityRepository.deleteById(id);
		
	}


	@Override
	public void delete(Security security) {
		securityRepository.delete(security);
	}
	
	
}
