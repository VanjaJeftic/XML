package com.agentApp.app.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

	public Oglas() {
    }
}
