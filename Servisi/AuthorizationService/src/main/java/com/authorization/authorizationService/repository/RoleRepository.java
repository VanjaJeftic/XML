package com.authorization.authorizationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorization.authorizationService.model.Role;

public interface RoleRepository  extends JpaRepository<Role,Integer> {
	
	Role findByName(String name);

}
