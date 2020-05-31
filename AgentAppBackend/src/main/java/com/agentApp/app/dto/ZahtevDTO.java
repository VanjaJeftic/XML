package com.agentApp.app.dto;

import java.time.LocalDateTime;

public class ZahtevDTO {

	private LocalDateTime preuzimanje;
	private LocalDateTime povratak;
	private boolean bundle;
	private OglasDTO oglas;
	
	public ZahtevDTO() {
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getPreuzimanje() {
		return preuzimanje;
	}

	public void setPreuzimanje(LocalDateTime preuzimanje) {
		this.preuzimanje = preuzimanje;
	}

	public LocalDateTime getPovratak() {
		return povratak;
	}

	public void setPovratak(LocalDateTime povratak) {
		this.povratak = povratak;
	}

	public boolean isBundle() {
		return bundle;
	}

	public void setBundle(boolean bundle) {
		this.bundle = bundle;
	}

	public OglasDTO getOglas() {
		return oglas;
	}

	public void setOglas(OglasDTO oglas) {
		this.oglas = oglas;
	}

	
	
	
}
