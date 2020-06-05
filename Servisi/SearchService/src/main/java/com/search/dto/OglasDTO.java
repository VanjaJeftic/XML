package com.search.dto;

import java.time.LocalDateTime;

public class OglasDTO {
	private Long id;

    private Long vozilo_id;

    private Long user_id;

    private String mesto;

    private Double cena;

    private Double popust;

    private Double cenaspopust;

    private LocalDateTime slobodanOd;

    private LocalDateTime slobodando;
    
    private Boolean cdw;

    public Boolean getCdw() {
		return cdw;
	}

	public void setCdw(Boolean cdw) {
		this.cdw = cdw;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVozilo_id() {
        return vozilo_id;
    }

    public void setVozilo_id(Long vozilo_id) {
        this.vozilo_id = vozilo_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public void setSlobodanOd(LocalDateTime slobodanOd) {
        this.slobodanOd = slobodanOd;
    }

    public LocalDateTime getSlobodando() {
        return slobodando;
    }

    public void setSlobodando(LocalDateTime slobodando) {
        this.slobodando = slobodando;
    }

    
    public OglasDTO() {
    }
}
