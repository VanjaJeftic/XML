package com.authorization.authorizationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorization.authorizationService.model.Permission;
import com.authorization.authorizationService.model.Role;
import com.authorization.authorizationService.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(RoleName name);
	Role findById(int id);

	
}