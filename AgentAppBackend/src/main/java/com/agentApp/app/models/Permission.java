package com.agentApp.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "permission")
public class Permission implements  GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JsonIgnore
    protected List<Authority> roles;
    
    
    
    public List<Authority> getRoles() {
		return roles;
	}

	public void setRoles(List<Authority> roles) {
		this.roles = roles;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public Permission(PermissionDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }*/

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public Permission() {
		super();
	}
	
	
}