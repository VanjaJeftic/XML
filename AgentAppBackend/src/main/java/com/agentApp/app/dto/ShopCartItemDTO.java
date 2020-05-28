package com.agentApp.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.agentApp.app.models.Zahtev;

public class ShopCartItemDTO {

	private List<Zahtev> zahtevi = new ArrayList<>();
	
	public ShopCartItemDTO() {
		
	}

	public List<Zahtev> getZahtevi() {
		return zahtevi;
	}

	public void setZahtevi(List<Zahtev> zahtevi) {
		this.zahtevi = zahtevi;
	}
}
