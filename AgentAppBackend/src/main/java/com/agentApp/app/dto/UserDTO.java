package com.agentApp.app.dto;

import com.agentApp.app.models.Authority;
import com.agentApp.app.models.User;
import com.agentApp.app.models.Vozilo;

import java.sql.Timestamp;
import java.util.List;

public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private java.sql.Timestamp lastPasswordResetDate;
	private boolean enabled;
	private String lastname;
	private String firstname;
	private String adress;
	private String email;
	private String city;
	private String country;
	private String phoneNumber;
	private String uloga;
	private boolean nalogAktiviran;
	private List<Authority> authorities;

	public UserDTO(Long id, String username, String password, Timestamp lastPasswordResetDate, boolean enabled, String lastname, String firstname, String adress, String email, String city, String country, String phoneNumber, String uloga, boolean nalogAktiviran,List<Authority>authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.enabled = enabled;
		this.lastname = lastname;
		this.firstname = firstname;
		this.adress = adress;
		this.email = email;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.uloga = uloga;
		this.nalogAktiviran = nalogAktiviran;
		this.authorities=authorities;
	}
	
	public UserDTO(Vozilo v) {
		this.id = v.getUser().getId();
		this.username = v.getUser().getUsername();
		this.password = v.getUser().getPassword();
		this.lastPasswordResetDate = v.getUser().getLastPasswordResetDate();
		this.enabled = v.getUser().isEnabled();
		this.lastname = v.getUser().getLastname();
		this.firstname = v.getUser().getFirstname();
		this.adress = v.getUser().getAdress();
		this.email = v.getUser().getEmail();
		this.city = v.getUser().getCity();
		this.country = v.getUser().getCountry();
		this.phoneNumber = v.getUser().getPhoneNumber();
		this.uloga = v.getUser().getUloga();
		this.nalogAktiviran = v.getUser().isNalogAktiviran();
		this.authorities= (List<Authority>) v.getUser().getRoles();
	}

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(User u) {
		// TODO Auto-generated constructor stub
		this.id=u.getId();
		this.email=u.getEmail();
		this.username=u.getUsername();
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public boolean isNalogAktiviran() {
		return nalogAktiviran;
	}

	public void setNalogAktiviran(boolean nalogAktiviran) {
		this.nalogAktiviran = nalogAktiviran;
	}
}
