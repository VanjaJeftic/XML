package com.authorization.authorizationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorization.authorizationService.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission,Integer>{
	
	Permission findByName(String name);
	

}
