package com.agentApp.app.dto;

import com.agentApp.app.models.User;
import com.agentApp.app.models.Vozilo;

public class VoziloDTO {

	private Long id;
	
	private UserDTO user;
	
	private String markaVozila;

    private String modelVozila;

    private String klasaVozila;

    private String tipGoriva;

    private String vrstaMenjaca;

    private String predjeniKm;

    private String brsedistadeca;

    private boolean cdw;//kupovine Collision Damage Waiver protekcije

	
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

	public String getBrsedistadeca() {
		return brsedistadeca;
	}

	public void setBrsedistadeca(String brsedistadeca) {
		this.brsedistadeca = brsedistadeca;
	}

	public boolean isCdw() {
		return cdw;
	}

	public void setCdw(boolean cdw) {
		this.cdw = cdw;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VoziloDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public VoziloDTO(Vozilo v) {
		this.id = v.getId();
		this.user = new UserDTO(v);
		this.markaVozila = v.getMarkaVozila().getNaziv();
		this.modelVozila = v.getModelVozila().getNaziv();
		this.klasaVozila = v.getKlasaVozila().getNaziv();
		this.tipGoriva = v.getTipgoriva().getNaziv();
		this.vrstaMenjaca = v.getVrstamenjaca().getNaziv();
		this.predjeniKm = v.getPredjeniKm();
		this.brsedistadeca = v.getBrSedistaDeca();
		//this.cdw = v.get
	}
	
	
}
