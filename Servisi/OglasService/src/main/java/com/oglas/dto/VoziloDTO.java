package com.oglas.dto;

import com.oglas.model.ImageModel;

import javax.persistence.*;


public class VoziloDTO {

    private Long id;

    private String markaVozila;

    private String modelVozila;

    private String klasaVozila;

    private String tipGoriva;

    private String vrstaMenjaca;

    private String predjeniKm;
    
    private boolean uoglasu;

    private String brsedistadeca;

    private boolean cdw;//kupovine Collision Damage Waiver protekcije



    private Long user_id;

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

  

    public boolean isCdw() {
        return cdw;
    }

    public void setCdw(boolean cdw) {
        this.cdw = cdw;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }


    public String getBrsedistadeca() {
		return brsedistadeca;
	}

	public void setBrsedistadeca(String brsedistadeca) {
		this.brsedistadeca = brsedistadeca;
	}

	public VoziloDTO() {
    }

    public VoziloDTO(String markaVozila, String modelVozila, String klasaVozila, String tipGoriva, String vrstaMenjaca, String predjeniKm, String brsedistadeca, boolean cdw, Long user_id) {
        this.markaVozila = markaVozila;
        this.modelVozila = modelVozila;
        this.klasaVozila = klasaVozila;
        this.tipGoriva = tipGoriva;
        this.vrstaMenjaca = vrstaMenjaca;
        this.predjeniKm = predjeniKm;
        this.brsedistadeca = brsedistadeca;
        this.cdw = cdw;
        this.user_id = user_id;
    }

	public boolean isUoglasu() {
		return uoglasu;
	}

	public void setUoglasu(boolean uoglasu) {
		this.uoglasu = uoglasu;
	}

    
}