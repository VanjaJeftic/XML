package com.agentApp.app.dto;

import java.time.LocalDateTime;

import com.agentApp.app.models.Korisnik;
import com.agentApp.app.models.Oglas;

public class ZahtevDTO {
	
    private Long id;
    private Oglas oglas;
    private Long Bundle_id;
    private boolean bundle;
    private Korisnik podnosilac;
    private LocalDateTime preuzimanje;
    private LocalDateTime povratak;
    private String status;
    
    public ZahtevDTO() {
    	
    }

	public ZahtevDTO(Long id, Oglas oglas, Long bundle_id, boolean bundle, Korisnik podnosilac,
			LocalDateTime preuzimanje, LocalDateTime povratak, String status) {
		super();
		this.id = id;
		this.oglas = oglas;
		Bundle_id = bundle_id;
		this.bundle = bundle;
		this.podnosilac = podnosilac;
		this.preuzimanje = preuzimanje;
		this.povratak = povratak;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public Long getBundle_id() {
		return Bundle_id;
	}

	public void setBundle_id(Long bundle_id) {
		Bundle_id = bundle_id;
	}

	public boolean isBundle() {
		return bundle;
	}

	public void setBundle(boolean bundle) {
		this.bundle = bundle;
	}

	public Korisnik getPodnosilac() {
		return podnosilac;
	}

	public void setPodnosilac(Korisnik podnosilac) {
		this.podnosilac = podnosilac;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
