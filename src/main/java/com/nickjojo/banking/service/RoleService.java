package com.nickjojo.banking.service;

import java.util.Optional;

import com.nickjojo.banking.entity.Role;

public interface RoleService {

	Optional<Role> findRoleByRole_Id(Long id);
}
