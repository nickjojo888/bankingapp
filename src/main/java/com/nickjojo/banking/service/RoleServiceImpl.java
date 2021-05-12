package com.nickjojo.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nickjojo.banking.entity.Role;
import com.nickjojo.banking.repository.RoleRepository;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findRoleByRole_Id(Long id) {
		return roleRepository.findById(id);
	}

}
