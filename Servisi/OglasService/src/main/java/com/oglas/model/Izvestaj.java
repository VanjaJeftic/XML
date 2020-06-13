package com.oglas.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.oglas.dto.IzvestajDTO;

@Entity
@Table(name = "izvestaj")
public class Izvestaj {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="predjenikm")
	private String predjeniKm;
	
	@Column(name="komentar")
	private String komentar;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "vozilo_id")
	private Vozilo vozilo;
	
	@Column(name = "zahtev_id")
	private Long zahtev;
	
	public Izvestaj() {
		
	}
	public Izvestaj(IzvestajDTO izvestajDTO, Vozilo vozilo) {
		super();
		this.predjeniKm = izvestajDTO.getPredjeniKm();
		this.komentar = izvestajDTO.getKomentar();
		this.vozilo = vozilo;
		this.zahtev = izvestajDTO.getZahtev();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPredjeniKm() {
		return predjeniKm;
	}

	public void setPredjeniKm(String predjeniKm) {
		this.predjeniKm = predjeniKm;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}
	public Long getZahtev() {
		return zahtev;
	}
	public void setZahtev(Long zahtev) {
		this.zahtev = zahtev;
	}
	
}
