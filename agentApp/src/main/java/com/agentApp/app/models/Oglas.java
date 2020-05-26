package com.agentApp.app.models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="oglas")
public class Oglas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="vozilo_id")
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

    public Oglas() {
    }
}
