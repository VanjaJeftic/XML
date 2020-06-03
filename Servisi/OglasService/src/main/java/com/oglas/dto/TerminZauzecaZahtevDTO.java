package com.oglas.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TerminZauzecaZahtevDTO {

	private List<Long> oglasi = new ArrayList<>();
	private LocalDateTime preuzimanje;
	private LocalDateTime povratak;
	
	public TerminZauzecaZahtevDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<Long> getOglasi() {
		return oglasi;
	}

	public void setOglasi(List<Long> oglasi) {
		this.oglasi = oglasi;
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
	
}
