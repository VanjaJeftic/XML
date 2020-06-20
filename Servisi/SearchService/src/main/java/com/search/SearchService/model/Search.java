package com.search.SearchService.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.search.SearchService.dto.OglasDTO;
import com.search.SearchService.dto.UserDTO;
import com.search.SearchService.dto.VoziloDTO;


@Entity
@Table(name="search")
public class Search implements Serializable {
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

    @Column(name="markavozila")
    private String markaVozila;

    @Column(name="modelvozila")
    private String modelVozila;

    @Column(name="klasavozila")
    private String klasaVozila;

    @Column(name="tipgoriva")
    private String tipGoriva;
    
    @Column(name="vrstamenjaca")
    private String vrstaMenjaca;
    
    @Column(name="predjenikm")
    private String predjeniKm;
    
    @Column(name="brsedistadeca")
    private String brSedistaDeca;
    
    @Column(name = "author")
    private String author;

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
	
    
	public String getMarkaVozila() {
		return markaVozila;
	}

	public void setMarkaVozila(String markaVozila) {
		this.markaVozila = markaVozila;
	}

	public String getModelVozila() {
		return modelVozila;
	}

	public void setModelVozila(String modelVozila) {
		this.modelVozila = modelVozila;
	}

	public String getKlasaVozila() {
		return klasaVozila;
	}

	public void setKlasaVozila(String klasaVozila) {
		this.klasaVozila = klasaVozila;
	}

	public String getTipGoriva() {
		return tipGoriva;
	}

	public void setTipGoriva(String tipGoriva) {
		this.tipGoriva = tipGoriva;
	}

	public String getVrstaMenjaca() {
		return vrstaMenjaca;
	}

	public void setVrstaMenjaca(String vrstaMenjaca) {
		this.vrstaMenjaca = vrstaMenjaca;
	}

	public String getPredjeniKm() {
		return predjeniKm;
	}

	public void setPredjeniKm(String predjeniKm) {
		this.predjeniKm = predjeniKm;
	}

	public String getBrSedistaDeca() {
		return brSedistaDeca;
	}

	public void setBrSedistaDeca(String brSedistaDeca) {
		this.brSedistaDeca = brSedistaDeca;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Search() {
    }
	
	public Search(OglasDTO oglas, VoziloDTO vozilo) {
		this.mesto = oglas.getMesto();
		this.slobodanOd = oglas.getSlobodanOd();
		this.slobodanDo = oglas.getSlobodando();
		this.cena = oglas.getCena();
		this.popust = oglas.getPopust();
		this.markaVozila = vozilo.getMarkaVozila();
		this.modelVozila = vozilo.getModelVozila();
		this.klasaVozila = vozilo.getKlasaVozila();
		this.tipGoriva = vozilo.getTipGoriva();
		this.vrstaMenjaca = vozilo.getVrstaMenjaca();
		this.predjeniKm = vozilo.getPredjeniKm();
		this.brSedistaDeca = vozilo.getBrSedistaDeca();
		this.author = oglas.getUser_id().toString();
    }
}
