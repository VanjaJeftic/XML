package com.oglas.dto;

import com.oglas.model.Oglas;

public class OglasViewDTO {
	
	private Long id;
	private String mesto;
	private VoziloViewDTO vozilo;
	
	public OglasViewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public OglasViewDTO(Oglas o, VoziloViewDTO v) {
		this.id = o.getId();
		this.mesto = o.getMesto();
		this.vozilo = v;
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

	public VoziloViewDTO getVozilo() {
		return vozilo;
	}

	public void setVozilo(VoziloViewDTO vozilo) {
		this.vozilo = vozilo;
	}
	
	
}
