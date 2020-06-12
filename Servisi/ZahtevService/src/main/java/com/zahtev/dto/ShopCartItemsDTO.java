package com.zahtev.dto;

import java.util.ArrayList;
import java.util.List;

public class ShopCartItemsDTO {

	private Long podnosilac;
	private List<ZahtevDTO> zahtevi;
	
	public ShopCartItemsDTO() {
		// TODO Auto-generated constructor stub
		this.zahtevi = new ArrayList<>();
	}
	
	
	public Long getPodnosilac() {
		return podnosilac;
	}


	public void setPodnosilac(Long podnosilac) {
		this.podnosilac = podnosilac;
	}


	public List<ZahtevDTO> getZahtevi() {
		return zahtevi;
	}

	public void setZahtevi(List<ZahtevDTO> zahtevi) {
		this.zahtevi = zahtevi;
	}
	
	
}