package com.oglas.dto;

import com.oglas.model.Izvestaj;

public class IzvestajDTO {

	private String predjeniKm;
	private String komentar;
	
	private Long vozilo;
	private Long zahtev;

	public IzvestajDTO() {
		
	}
	
	public IzvestajDTO(Izvestaj i) {
		super();
		this.predjeniKm = i.getPredjeniKm();
		this.komentar = i.getKomentar();
		this.zahtev = i.getZahtev();
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

	public Long getVozilo() {
		return vozilo;
	}

	public void setVozilo(Long vozilo) {
		this.vozilo = vozilo;
	}

	public Long getZahtev() {
		return zahtev;
	}

	public void setZahtev(Long zahtev) {
		this.zahtev = zahtev;
	}
	
}
