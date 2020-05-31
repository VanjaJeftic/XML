package com.oglas.model;

import com.oglas.dto.OglasDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="oglas")
public class Oglas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long user_id; //kreira oglas

    @Column(name="vozilo_id")
    private Long vozilo_id;

    @Column(name="mesto")
    private String mesto;

    @Column(name="cena")
    private Double cena;

    @Column(name="popust")
    private Double popust;

    @Column(name="cenaspopust")
    private Double cenaspopust;

    @Column(name="slobodanod")
    private LocalDateTime slobodanOd;

    @Column(name="slovbodando")
    private LocalDateTime slobodando;
    
    @Column(name="cdw")
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Long getVozilo_id() {
        return vozilo_id;
    }

    public void setVozilo_id(Long vozilo_id) {
        this.vozilo_id = vozilo_id;
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

    public Oglas(OglasDTO dto) {
        this.id = dto.getId();
        this.vozilo_id = dto.getVozilo_id();
        this.mesto = dto.getMesto();
        this.cena = dto.getCena();
        this.popust = dto.getPopust();
        this.cenaspopust = dto.getCenaspopust();
        this.user_id=dto.getUser_id();
        this.slobodando=dto.getSlobodando();
        this.slobodanOd=dto.getSlobodanod();
        this.cdw=dto.getCdw();
    }



    public Oglas() {
    }

 }
