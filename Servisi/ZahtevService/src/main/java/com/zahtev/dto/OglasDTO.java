package com.zahtev.dto;

public class OglasDTO {

	private Long id;
	private String mesto;
	private VoziloDTO vozilo;
	
	public OglasDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public VoziloDTO getVozilo() {
		return vozilo;
	}

	public void setVozilo(VoziloDTO vozilo) {
		this.vozilo = vozilo;
	}
	
	
}
