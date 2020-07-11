package com.zahtev.dto;

import java.util.HashSet;
import java.util.Set;

public class ZahtevViewDTO {

	private Long bundleID;
	private Set<ZahtevDTO> bundleZahtevi;
	
	private UserDTO user;
	
	public ZahtevViewDTO() {
		// TODO Auto-generated constructor stub
		this.bundleZahtevi = new HashSet<>();
	}

	public Long getBundleID() {
		return bundleID;
	}

	public void setBundleID(Long bundleID) {
		this.bundleID = bundleID;
	}

	public Set<ZahtevDTO> getBundleZahtevi() {
		return bundleZahtevi;
	}

	public void setBundleZahtevi(Set<ZahtevDTO> bundleZahtevi) {
		this.bundleZahtevi = bundleZahtevi;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ZahtevViewDTO [bundleID=" + bundleID + ", bundleZahtevi=" + bundleZahtevi + ", user=" + user + "]";
	}
	
}
