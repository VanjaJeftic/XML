package com.zahtev.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zahtev.dto.ZahtevDTO;

@Entity
@Table(name = "zahtev")
public class Zahtev implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "oglas_id")
	private Long oglas_id;
	
	@Column(name = "bundle_id")
	private Long bundle_id;
	
	@Column(name = "bundle")
	private boolean bundle;
	
	@Column(name = "podnosilac_id")
	private Long podnosilac_id;
	
	@Column(name = "preuzimanje")
	private LocalDateTime preuzimanje;

	@Column(name = "povratak")
	private LocalDateTime povratak;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "kreiran")
	private LocalDateTime kreiran;
	
	public Zahtev() {
		// TODO Auto-generated constructor stub
	}
	public Zahtev(ZahtevDTO zahtevDTO) {
		super();
		this.oglas_id = zahtevDTO.getOglas().getId();
		this.bundle_id = zahtevDTO.getBundle_id();
		this.bundle = zahtevDTO.isBundle();
		//this.podnosilac_id = zahtevDTO.getPodnosilac_id();
		this.preuzimanje = zahtevDTO.getPreuzimanje();
		this.povratak = zahtevDTO.getPovratak();
		this.status = zahtevDTO.getStatus();
	}
	
	

	@Override
	public String toString() {
		return "Zahtev [id=" + id + ", oglas_id=" + oglas_id + ", bundle_id=" + bundle_id + ", bundle=" + bundle
				+ ", podnosilac_id=" + podnosilac_id + ", preuzimanje=" + preuzimanje + ", povratak=" + povratak
				+ ", status=" + status + "]";
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public LocalDateTime getKreiran() {
		return kreiran;
	}
	public void setKreiran(LocalDateTime kreiran) {
		this.kreiran = kreiran;
	}
	
	
}
