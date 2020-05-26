package com.agentApp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentApp.app.models.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
	Authority findByName(String name);

}
