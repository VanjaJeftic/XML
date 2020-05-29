package com.zahtev.dto;

import java.util.ArrayList;
import java.util.List;

public class ShopCartItemsDTO {

	private List<ZahtevDTO> zahtevi;
	
	public ShopCartItemsDTO() {
		// TODO Auto-generated constructor stub
		this.zahtevi = new ArrayList<>();
	}

	public List<ZahtevDTO> getZahtevi() {
		return zahtevi;
	}

	public void setZahtevi(List<ZahtevDTO> zahtevi) {
		this.zahtevi = zahtevi;
	}
	
	
}