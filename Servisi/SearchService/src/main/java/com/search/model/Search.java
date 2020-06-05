package com.search.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.search.dto.OglasDTO;
import com.search.dto.UserDTO;
import com.search.dto.VoziloDTO;

@Entity
@Table(name="search")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Search {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="mesto")
    private String mesto;

	@Column(name="slobodanod")
    private LocalDateTime slobodanOd;

    @Column(name="slobodando")
    private LocalDateTime slobodanDo;

    @Column(name="cena")
    private Double cena;

    @Column(name="popust")
    private Double popust;

    @Column(name="vozilo_markavozila")
    private String vozilo_markaVozila;

    @Column(name="vozilo_modelvozila")
    private String vozilo_modelVozila;

    @Column(name="vozilo_klasavozila")
    private String vozilo_klasaVozila;

    @Column(name="vozilo_tipgoriva")
    private String vozilo_tipGoriva;
    
    @Column(name="vozilo_vrstamenjaca")
    private String vozilo_vrstaMenjaca;
    
    @Column(name="vozilo_predjenikm")
    private String vozilo_predjeniKm;
    
    @Column(name="vozilo_brsedistadeca")
    private String vozilo_BrSedistaDeca;
    
    @Column(name = "user_firtname")
    private String user_firtname;

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

	public String getVozilo_markaVozila() {
		return vozilo_markaVozila;
	}

	public void setVozilo_markaVozila(String vozilo_markaVozila) {
		this.vozilo_markaVozila = vozilo_markaVozila;
	}

	public String getVozilo_modelVozila() {
		return vozilo_modelVozila;
	}

	public void setVozilo_modelVozila(String vozilo_modelVozila) {
		this.vozilo_modelVozila = vozilo_modelVozila;
	}

	public String getVozilo_klasaVozila() {
		return vozilo_klasaVozila;
	}

	public void setVozilo_klasaVozila(String vozilo_klasaVozila) {
		this.vozilo_klasaVozila = vozilo_klasaVozila;
	}

	public String getVozilo_tipGoriva() {
		return vozilo_tipGoriva;
	}

	public void setVozilo_tipGoriva(String vozilo_tipGoriva) {
		this.vozilo_tipGoriva = vozilo_tipGoriva;
	}

	public String getVozilo_vrstaMenjaca() {
		return vozilo_vrstaMenjaca;
	}

	public void setVozilo_vrstaMenjaca(String vozilo_vrstaMenjaca) {
		this.vozilo_vrstaMenjaca = vozilo_vrstaMenjaca;
	}

	public String getVozilo_predjeniKm() {
		return vozilo_predjeniKm;
	}

	public void setVozilo_predjeniKm(String vozilo_predjeniKm) {
		this.vozilo_predjeniKm = vozilo_predjeniKm;
	}

	public String getVozilo_BrSedistaDeca() {
		return vozilo_BrSedistaDeca;
	}

	public void setVozilo_BrSedistaDeca(String vozilo_BrSedistaDeca) {
		this.vozilo_BrSedistaDeca = vozilo_BrSedistaDeca;
	}

	public String getUser_firtname() {
		return user_firtname;
	}

	public void setUser_firtname(String user_firtname) {
		this.user_firtname = user_firtname;
	}
    
	public Search() {
    }
	
	public Search(OglasDTO oglas, VoziloDTO vozilo, UserDTO user) {
		this.mesto = oglas.getMesto();
		this.slobodanOd = oglas.getSlobodanOd();
		this.slobodanDo = oglas.getSlobodando();
		this.cena = oglas.getCena();
		this.popust = oglas.getPopust();
		this.vozilo_markaVozila = vozilo.getMarkaVozila();
		this.vozilo_modelVozila = vozilo.getModelVozila();
		this.vozilo_klasaVozila = vozilo.getKlasaVozila();
		this.vozilo_tipGoriva = vozilo.getTipGoriva();
		this.vozilo_vrstaMenjaca = vozilo.getVrstaMenjaca();
		this.vozilo_predjeniKm = vozilo.getPredjeniKm();
		this.vozilo_BrSedistaDeca = vozilo.getBrSedistaDeca();
		this.user_firtname = user.getFirstname();
    }
}
