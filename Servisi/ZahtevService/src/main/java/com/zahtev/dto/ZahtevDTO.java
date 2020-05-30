package com.zahtev.dto;

import java.time.LocalDateTime;

public class ZahtevDTO {

	private Long oglas_id;
	private Long bundle_id;
	private boolean bundle;
	private Long podnosilac_id;
	private LocalDateTime preuzimanje;
	private LocalDateTime povratak;
	private String status;
	
	public ZahtevDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getOglas_id() {
		return oglas_id;
	}

	public void setOglas_id(Long oglas_id) {
		this.oglas_id = oglas_id;
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

	public Long getPodnosilac_id() {
		return podnosilac_id;
	}

	public void setPodnosilac_id(Long podnosilac_id) {
		this.podnosilac_id = podnosilac_id;
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
