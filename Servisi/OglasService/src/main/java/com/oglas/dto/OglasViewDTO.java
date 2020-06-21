package com.oglas.dto;

import java.time.LocalDateTime;

import com.oglas.model.Oglas;

public class OglasViewDTO {
	
	private Long id;
	private String mesto;
	private VoziloViewDTO vozilo;
	
	private LocalDateTime slobodanOd;
	private LocalDateTime slobodanDo;
	
	public OglasViewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public OglasViewDTO(Oglas o, VoziloViewDTO v) {
		this.id = o.getId();
		this.slobodanOd = o.getSlobodanOd();
		this.slobodanDo = o.getSlobodando();
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

	public LocalDateTime getSlobodanOd() {
		return slobodanOd;
	}

	public void setSlobodanOd(LocalDateTime slobodanOd) {
		this.slobodanOd = slobodanOd;
	}

	public LocalDateTime getSlobodanDo() {
		return slobodanDo;
	}

	public void setSlobodanDo(LocalDateTime slobodanDo) {
		this.slobodanDo = slobodanDo;
	}
	
	
}
