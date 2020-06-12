package com.oglas.dto;

import com.oglas.model.Izvestaj;

public class IzvestajDTO {

	private String predjeniKm;
	private String komentar;

	public IzvestajDTO() {
		
	}
	
	public IzvestajDTO(Izvestaj i) {
		super();
		this.predjeniKm = i.getPredjeniKm();
		this.komentar = i.getKomentar();
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
}
