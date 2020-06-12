package com.agentApp.app.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agentApp.app.dto.OglasDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="oglas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Oglas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="vozilo_id", referencedColumnName = "id")
    private Vozilo vozilo;

    @Column(name="mesto")
    private String mesto;

   // @Column(name="slike")
    //private ArrayList<String> slike;

    @Column(name="cena")
    private Double cena;

    @Column(name="popust")
    private Double popust;

    @Column(name="cenaspopust")
    private Double cenaspopust;
    
    @Column(name="slobodanod")
    private LocalDateTime slobodanOd;

    @Column(name="slobodando")
    private LocalDateTime slobodando;
    
    
    @Column(name="cdw")
    private Boolean cdw;

    @JsonIgnore
    @OneToMany(mappedBy = "oglas", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Zahtev> zahtevi = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
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

    public Set<Zahtev> getZahtevi() {
		return zahtevi;
    }

    public void setZahtevi(Set<Zahtev> zahtevi) {
		this.zahtevi = zahtevi;
    }
    
    public LocalDateTime getSlobodanOd() {
        return slobodanOd;
    }

    public void setSlobodanOd(LocalDateTime slobodanOd) {
        this.slobodanOd = slobodanOd;
    }

    public LocalDateTime getSlobodanDo() {
        return slobodando;
    }

    public void setSlobodanDo(LocalDateTime slobodando) {
        this.slobodando = slobodando;
    }
    
    

    public Boolean getCdw() {
		return cdw;
	}

	public void setCdw(Boolean cdw) {
		this.cdw = cdw;
	}

	public Oglas() {
    }

	public Oglas(OglasDTO oglasdto) {
		// TODO Auto-generated constructor stub
		this.cdw=oglasdto.getCdw();
		this.cena=oglasdto.getCena();
		this.popust=oglasdto.getPopust();
		this.cenaspopust=oglasdto.getCenaspopust();
		this.mesto=oglasdto.getMesto();
		this.slobodando=oglasdto.getSlobodanDo();
		this.slobodanOd=oglasdto.getSlobodanOd();
		//Vozilo v=new Vozilo(oglasdto.getVozilo());
		//this.vozilo=v;
	}
}
