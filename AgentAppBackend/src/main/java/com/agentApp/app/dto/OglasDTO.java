package com.agentApp.app.dto;

public class OglasDTO {

	private Long id;
	private VoziloDTO vozilo;
	private String mesto;
	
	public OglasDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VoziloDTO getVozilo() {
		return vozilo;
	}

	public void setVozilo(VoziloDTO vozilo) {
		this.vozilo = vozilo;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	
	
}
