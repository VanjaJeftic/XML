package com.oglas.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oglas.dto.VoziloDTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="vozilo")
public class Vozilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@OneToOne
    @JoinColumn(name="markavozila_id")
    private MarkaVozila markaVozila;

    @OneToOne
    @JoinColumn(name="modelvozila_id")
    private ModelVozila modelVozila;

    @OneToOne
    @JoinColumn(name="klasavozila_id")
    private KlasaVozila klasaVozila;

    @OneToOne
    @JoinColumn(name="tipgoriva_id")
    private TipGoriva tipgoriva;

    @OneToOne
    @JoinColumn(name="vrstamenjaca_id")
    private VrstaMenjaca vrstamenjaca;*/


    @Column(name="markavozila_id")
    private String markaVozila;

    @Column(name="modelvozila_id")
    private String modelVozila;


    @Column(name="klasavozila_id")
    private String klasaVozila;

    @Column(name="tipgoriva_id")
    private String tipGoriva;


    @Column(name="vrstamenjaca_id")
    private String vrstaMenjaca;

    @Column(name="predjenikm")
    private String predjeniKm;

    @Column(name="brsedistadeca")
    private String BrSedistaDeca;

    @Column(name="cdw")
    private boolean cdw;//kupovine Collision Damage Waiver protekcije

    @Column(name = "user_id")
    private Long user_id;
    
    @JsonIgnore
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TerminZauzeca> zauzeti = new ArrayList<TerminZauzeca>();

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

    public void setTipGoriva(String tipGoriva) {
        this.tipGoriva = tipGoriva;
    }

    public String getVrstamenjaca() {
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
        return BrSedistaDeca;
    }

    public void setBrsedistadeca(String brsedistadeca) {
        BrSedistaDeca = brsedistadeca;
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
    
    
    public List<TerminZauzeca> getZauzeti() {
		return zauzeti;
	}

	public void setZauzeti(List<TerminZauzeca> zauzeti) {
		this.zauzeti = zauzeti;
	}

	public Vozilo() {
    }
    

    public Vozilo(String markaVozila, String modelVozila, String klasaVozila, String tipgoriva, String vrstamenjaca, String predjeniKm, String brSedistaDeca, boolean cdw, Long user_id) {
        this.markaVozila = markaVozila;
        this.modelVozila = modelVozila;
        this.klasaVozila = klasaVozila;
        this.tipGoriva = tipgoriva;
        this.vrstaMenjaca = vrstamenjaca;
        this.predjeniKm = predjeniKm;
        this.BrSedistaDeca = brSedistaDeca;
        this.cdw = cdw;
        this.user_id = user_id;

    }

    public Vozilo(VoziloDTO voziloDTO){
        this.markaVozila = voziloDTO.getMarkaVozila();
        this.modelVozila = voziloDTO.getModelVozila();
        this.klasaVozila = voziloDTO.getKlasaVozila();
        this.tipGoriva = voziloDTO.getTipGoriva();
        this.vrstaMenjaca = voziloDTO.getVrstaMenjaca();
        this.predjeniKm = voziloDTO.getPredjeniKm();
        this.BrSedistaDeca = voziloDTO.getBrsedistadeca();
        this.cdw = voziloDTO.isCdw();
        this.user_id = voziloDTO.getUser_id();
    }

    public Vozilo(String markaVozila, String modelVozila, String klasaVozila, String tipgoriva, String vrstamenjaca, String predjeniKm, String brSedistaDeca) {
        this.markaVozila = markaVozila;
        this.modelVozila = modelVozila;
        this.klasaVozila = klasaVozila;
        this.tipGoriva = tipgoriva;
        this.vrstaMenjaca = vrstamenjaca;
        this.predjeniKm = predjeniKm;
        this.BrSedistaDeca = brSedistaDeca;


    }

}