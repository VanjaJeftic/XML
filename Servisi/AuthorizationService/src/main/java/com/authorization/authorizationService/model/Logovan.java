package com.authorization.authorizationService.model;

import java.util.List;

public class Logovan {
	
	private String username;
	private String token;
    private String roles;
    private Long userId;
    private Boolean nalogAktivan;
    
    
    
    
    
    
	public Boolean getNalogAktivan() {
		return nalogAktivan;
	}
	public void setNalogAktivan(Boolean nalogAktivan) {
		this.nalogAktivan = nalogAktivan;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String string) {
		this.roles = string;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Logovan() {
		super();
	}
	
	

}
