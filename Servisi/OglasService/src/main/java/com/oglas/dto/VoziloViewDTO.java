package com.oglas.dto;

import com.oglas.model.Vozilo;

public class VoziloViewDTO {

	private Long id;
	private String markaVozila;
	private String modelVozila;
	private String klasaVozila;
	private String vrstaMenjaca;
	private String tipGoriva;
	private String predjeniKm;
	private String brsedistadeca;
	
	private UserViewDTO user;
	
	public VoziloViewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public VoziloViewDTO(Vozilo v) {
		this.markaVozila = v.getMarkaVozila();
    	this.modelVozila = v.getModelVozila();
    	this.klasaVozila = v.getKlasaVozila();
    	this.tipGoriva = v.getTipgoriva();
    	this.vrstaMenjaca = v.getVrstamenjaca();
    	this.predjeniKm = v.getPredjeniKm();
    	this.brsedistadeca = v.getBrSedistaDeca();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getVrstaMenjaca() {
		return vrstaMenjaca;
	}

	public void setVrstaMenjaca(String vrstaMenjaca) {
		this.vrstaMenjaca = vrstaMenjaca;
	}

	public String getTipGoriva() {
		return tipGoriva;
	}

	public void setTipGoriva(String tipGoriva) {
		this.tipGoriva = tipGoriva;
	}

	public String getPredjeniKm() {
		return predjeniKm;
	}

	public void setPredjeniKm(String predjeniKm) {
		this.predjeniKm = predjeniKm;
	}

	public String getBrsedistadeca() {
		return brsedistadeca;
	}

	public void setBrsedistadeca(String brsedistadeca) {
		this.brsedistadeca = brsedistadeca;
	}

	public UserViewDTO getUser() {
		return user;
	}

	public void setUser(UserViewDTO user) {
		this.user = user;
	}
	
}
