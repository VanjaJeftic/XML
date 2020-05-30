package com.oglas.dto;

import javax.persistence.*;


public class VoziloDTO {

    private Long id;

    private String markaVozila;

    private String modelVozila;

    private String klasaVozila;

    private String tipGoriva;

    private String vrstaMenjaca;

    private String predjeniKm;

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

    public String getTipgoriva() {
        return tipGoriva;
    }

    public void setTipgoriva(String tipgoriva) {
        this.tipGoriva = tipgoriva;
    }

    public String getVrstamenjaca() {
        return vrstaMenjaca;
    }

    public void setVrstamenjaca(String vrstamenjaca) {
        this.vrstaMenjaca = vrstamenjaca;
    }

    public String getPredjeniKm() {
        return predjeniKm;
    }

    public void setPredjeniKm(String predjeniKm) {
        this.predjeniKm = predjeniKm;
    }

    public String getBrSedistaDeca() {
        return brsedistadeca;
    }

    public void setBrSedistaDeca(String brSedistaDeca) {
        brsedistadeca = brSedistaDeca;
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

    public VoziloDTO() {
    }

    public VoziloDTO(String markaVozila, String modelVozila, String klasaVozila, String tipgoriva, String vrstamenjaca, String predjeniKm, String brSedistaDeca, boolean cdw, Long user_id) {
        this.markaVozila = markaVozila;
        this.modelVozila = modelVozila;
        this.klasaVozila = klasaVozila;
        this.tipGoriva = tipgoriva;
        this.vrstaMenjaca = vrstamenjaca;
        this.predjeniKm = predjeniKm;
        brsedistadeca = brSedistaDeca;
        this.cdw = cdw;
        this.user_id = user_id;
    }

    public VoziloDTO(Long id, String markaVozila, String modelVozila, String klasaVozila, String tipgoriva, String vrstamenjaca, String predjeniKm, String brSedistaDeca, boolean cdw, Long user_id) {
        this.id = id;
        this.markaVozila = markaVozila;
        this.modelVozila = modelVozila;
        this.klasaVozila = klasaVozila;
        this.tipGoriva = tipgoriva;
        this.vrstaMenjaca = vrstamenjaca;
        this.predjeniKm = predjeniKm;
        brsedistadeca = brSedistaDeca;
        this.cdw = cdw;
        this.user_id = user_id;
    }
}