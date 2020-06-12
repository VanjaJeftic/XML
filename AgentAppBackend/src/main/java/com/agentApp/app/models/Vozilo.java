package com.agentApp.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agentApp.app.dto.VoziloDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="vozilo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vozilo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
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
    private VrstaMenjaca vrstamenjaca;

    @Column(name="predjenikm")
    private String predjeniKm;

    @Column(name="brsedistadeca")
    private String BrSedistaDeca;

    @Column(name="cdw")
    private boolean cdw;//kupovine Collision Damage Waiver protekcije

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "vozilo")
    private Oglas oglas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MarkaVozila getMarkaVozila() {
        return markaVozila;
    }

    public void setMarkaVozila(MarkaVozila markaVozila) {
        this.markaVozila = markaVozila;
    }

    public ModelVozila getModelVozila() {
        return modelVozila;
    }

    public void setModelVozila(ModelVozila modelVozila) {
        this.modelVozila = modelVozila;
    }

    public KlasaVozila getKlasaVozila() {
        return klasaVozila;
    }

    public void setKlasaVozila(KlasaVozila klasaVozila) {
        this.klasaVozila = klasaVozila;
    }

    public TipGoriva getTipgoriva() {
        return tipgoriva;
    }

    public void setTipgoriva(TipGoriva tipgoriva) {
        this.tipgoriva = tipgoriva;
    }

    public VrstaMenjaca getVrstamenjaca() {
        return vrstamenjaca;
    }

    public void setVrstamenjaca(VrstaMenjaca vrstamenjaca) {
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

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Oglas getOglas() {
	return oglas;
     }

    public void setOglas(Oglas oglas) {
	this.oglas = oglas;
    }

    public Vozilo() {
    }

	public Vozilo(VoziloDTO vozilo) {
		// TODO Auto-generated constructor stub
		this.id=vozilo.getId();
		this.BrSedistaDeca=vozilo.getBrsedistadeca();
		
	}
}
