package com.oglas.dto;

import java.time.LocalDateTime;

public class OglasVoziloDTO {

    private Long oglas_id;

    private Long vozilo_id;

    private Long user_id;

    private String mesto;

    private Double cena;

    private Double popust;

    private Double cenaspopust;

    private String markaVozila;

    private String modelVozila;

    private String klasaVozila;

    private String tipgoriva;

    private String vrstamenjaca;

    private String predjeniKm;

    private String BrSedistaDeca;

    private LocalDateTime slobodanod;

    private LocalDateTime slobodando;


    private boolean cdw;//kupovine Collision Damage Waiver protekcije


    public Long getOglas_id() {
        return oglas_id;
    }

    public void setOglas_id(Long oglas_id) {
        this.oglas_id = oglas_id;
    }

    public Long getVozilo_id() {
        return vozilo_id;
    }

    public void setVozilo_id(Long vozilo_id) {
        this.vozilo_id = vozilo_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
        return tipgoriva;
    }

    public void setTipgoriva(String tipgoriva) {
        this.tipgoriva = tipgoriva;
    }

    public String getVrstamenjaca() {
        return vrstamenjaca;
    }

    public void setVrstamenjaca(String vrstamenjaca) {
        this.vrstamenjaca = vrstamenjaca;
    }

    public String getPredjeniKm() {
        return predjeniKm;
    }

    public void setPredjeniKm(String predjeniKm) {
        this.predjeniKm = predjeniKm;
    }

    public String getBrSedistaDeca() {
        return BrSedistaDeca;
    }

    public void setBrSedistaDeca(String brSedistaDeca) {
        BrSedistaDeca = brSedistaDeca;
    }

    public boolean isCdw() {
        return cdw;
    }

    public void setCdw(boolean cdw) {
        this.cdw = cdw;
    }

    public LocalDateTime getSlobodanod() {
        return slobodanod;
    }

    public void setSlobodanod(LocalDateTime slobodanod) {
        this.slobodanod = slobodanod;
    }

    public LocalDateTime getSlobodando() {
        return slobodando;
    }

    public void setSlobodando(LocalDateTime slobodando) {
        this.slobodando = slobodando;
    }

    public OglasVoziloDTO(Long oglas_id, Long vozilo_id, String mesto, Double cena, Double popust, Double cenaspopust, Long user_id, Long agent_id, String markaVozila, String modelVozila, String klasaVozila, String tipgoriva, String vrstamenjaca, String predjeniKm, String brSedistaDeca, boolean cdw) {
        this.oglas_id = oglas_id;
        this.vozilo_id = vozilo_id;
        this.mesto = mesto;
        this.cena = cena;
        this.popust = popust;
        this.cenaspopust = cenaspopust;
        this.user_id = user_id;
        this.markaVozila = markaVozila;
        this.modelVozila = modelVozila;
        this.klasaVozila = klasaVozila;
        this.tipgoriva = tipgoriva;
        this.vrstamenjaca = vrstamenjaca;
        this.predjeniKm = predjeniKm;
        BrSedistaDeca = brSedistaDeca;
        this.cdw = cdw;
    }

    public OglasVoziloDTO() {
    }

    public OglasVoziloDTO(Long oglas_id, Long vozilo_id, Long user_id, String mesto, Double cena, Double popust, Double cenaspopust, String markaVozila, String modelVozila, String klasaVozila, String tipgoriva, String vrstamenjaca, String predjeniKm, String brSedistaDeca, LocalDateTime slobodanod, LocalDateTime slobodando, boolean cdw) {
        this.oglas_id = oglas_id;
        this.vozilo_id = vozilo_id;
        this.user_id = user_id;
        this.mesto = mesto;
        this.cena = cena;
        this.popust = popust;
        this.cenaspopust = cenaspopust;
        this.markaVozila = markaVozila;
        this.modelVozila = modelVozila;
        this.klasaVozila = klasaVozila;
        this.tipgoriva = tipgoriva;
        this.vrstamenjaca = vrstamenjaca;
        this.predjeniKm = predjeniKm;
        BrSedistaDeca = brSedistaDeca;
        this.slobodanod = slobodanod;
        this.slobodando = slobodando;
        this.cdw = cdw;
    }
}
