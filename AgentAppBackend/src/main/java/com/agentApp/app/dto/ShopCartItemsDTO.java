package com.agentApp.app.dto;

import java.util.ArrayList;
import java.util.List;

public class ShopCartItemsDTO {

	private List<ZahtevDTO> zahtevi = new ArrayList<>();
	
	public ShopCartItemsDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<ZahtevDTO> getZahtevi() {
		return zahtevi;
	}

	public void setZahtevi(List<ZahtevDTO> zahtevi) {
		this.zahtevi = zahtevi;
	}
	
}
