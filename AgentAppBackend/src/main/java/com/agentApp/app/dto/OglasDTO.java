package com.agentApp.app.dto;

import java.time.LocalDateTime;

import com.agentApp.app.models.Oglas;
import com.agentApp.app.models.Vozilo;

public class OglasDTO {

	private Long id;
	
	private VoziloDTO vozilo;
	
	private String mesto;
	
	private Double cena;

    private Double popust;

    private Double cenaspopust;

    private LocalDateTime slobodanOd;

    private LocalDateTime slobodanDo;
    
    private Boolean cdw;
    
    private Long vozilo_id;
	
	public OglasDTO() {
		// TODO Auto-generated constructor stub
	}
	public OglasDTO(Oglas o, Vozilo v) {
		this.id = o.getId();
		this.mesto = o.getMesto();
		this.cena = o.getCena();
		this.popust = o.getPopust();
		this.cenaspopust = o.getCenaspopust();
		this.slobodanOd = o.getSlobodanOd();
		this.slobodanDo = o.getSlobodanDo();
		this.cdw = o.getCdw();
		this.vozilo = new VoziloDTO(v);
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

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}

	public Double getCenaspopust() {
		return cenaspopust;
	}

	public void setCenaspopust(Double cenaspopust) {
		this.cenaspopust = cenaspopust;
	}

	public LocalDateTime getSlobodanOd() {
		return slobodanOd;
	}

	public void setSlobodanod(LocalDateTime slobodanOd) {
		this.slobodanOd = slobodanOd;
	}

	public LocalDateTime getSlobodanDo() {
		return slobodanDo;
	}

	public void setSlobodando(LocalDateTime slobodando) {
		this.slobodanDo = slobodanDo;
	}

	public Boolean getCdw() {
		return cdw;
	}

	public void setCdw(Boolean cdw) {
		this.cdw = cdw;
	}

	public Long getVozilo_id() {
		return vozilo_id;
	}

	public void setVozilo_id(Long vozilo_id) {
		this.vozilo_id = vozilo_id;
	}
	
	
	
	
	
}
