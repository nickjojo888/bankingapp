package com.nickjojo.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nickjojo.banking.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
