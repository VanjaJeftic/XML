package com.zahtev.dto;

import java.time.LocalDateTime;

import com.zahtev.model.Zahtev;

public class ZahtevDTO {

	private Long id;
	
	private OglasDTO oglas;
	private Long bundle_id;
	private boolean bundle;
	//private Long podnosilac_id;
	private LocalDateTime preuzimanje;
	private LocalDateTime povratak;
	private String status;
	
	private IzvestajDTO izvestaj;
	
	public ZahtevDTO() {
		// TODO Auto-generated constructor stub
	}
	public ZahtevDTO(Zahtev z) {
		super();
		this.id = z.getId();
		this.bundle_id = z.getBundle_id();
		this.preuzimanje = z.getPreuzimanje();
		this.povratak = z.getPovratak();
		this.status = z.getStatus();
	}
	
	public ZahtevDTO(Zahtev z, OglasDTO oglas) {
		super();
		this.oglas = oglas;
		this.id = z.getId();
		this.bundle_id = z.getBundle_id();
		this.bundle = z.isBundle();
		this.preuzimanje = z.getPreuzimanje();
		this.povratak = z.getPovratak();
		this.status = z.getStatus();
	}
	public ZahtevDTO(Zahtev z, OglasDTO oglas, IzvestajDTO i) {
		super();
		this.oglas = oglas;
		this.id = z.getId();
		this.bundle_id = z.getBundle_id();
		this.bundle = z.isBundle();
		this.preuzimanje = z.getPreuzimanje();
		this.povratak = z.getPovratak();
		this.status = z.getStatus();
		this.izvestaj = i;
	}
	
	
	public OglasDTO getOglas() {
		return oglas;
	}


	public void setOglas(OglasDTO oglas) {
		this.oglas = oglas;
	}


	public Long getBundle_id() {
		return bundle_id;
	}

	public void setBundle_id(Long bundle_id) {
		this.bundle_id = bundle_id;
	}

	public boolean isBundle() {
		return bundle;
	}

	public void setBundle(boolean bundle) {
		this.bundle = bundle;
	}

//	public Long getPodnosilac_id() {
//		return podnosilac_id;
//	}
//
//	public void setPodnosilac_id(Long podnosilac_id) {
//		this.podnosilac_id = podnosilac_id;
//	}

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

	public IzvestajDTO getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(IzvestajDTO izvestaj) {
		this.izvestaj = izvestaj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
