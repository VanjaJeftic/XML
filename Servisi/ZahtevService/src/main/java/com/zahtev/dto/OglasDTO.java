package com.zahtev.dto;

import java.time.LocalDateTime;

public class OglasDTO {

	private Long id;
	private String mesto;
	private VoziloDTO vozilo;
	
	private LocalDateTime slobodanOd;
	private LocalDateTime slobodanDo;
	
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

	@Override
	public String toString() {
		return "OglasDTO [id=" + id + ", mesto=" + mesto + ", vozilo=" + vozilo + ", slobodanOd=" + slobodanOd
				+ ", slobodanDo=" + slobodanDo + "]";
	}
	
	
}
