package com.admin.adminServis.model;

import com.admin.adminServis.dto.UserDTO;

import javax.persistence.*;
@Entity
@Table(name = "users")
public class User  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = true, unique = true)
	private String username;

	@Column(name = "uloga", nullable = false)
	private String uloga;
	
	@Column(name = "aktiviran", nullable = false)
	private boolean nalogAktiviran;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public User() {
		
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

	public boolean isNalogAktiviran() {
		return nalogAktiviran;
	}

	public void setNalogAktiviran(boolean nalogAktiviran) {
		this.nalogAktiviran = nalogAktiviran;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public User(UserDTO dto) {
		this.id = dto.getId();
		this.uloga = dto.getUloga();
		this.nalogAktiviran = dto.isNalogAktiviran();
	}
}
