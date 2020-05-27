package com.oglas.model;

import com.oglas.dto.OglasDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="oglas")
public class Oglas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long user_id; //kreira oglas

    @Column(name="agent_id")
    private Long agent_id; //kreira oglas- vucemo iz Agent Serice

    @Column(name="vozilo_id")
    private Long vozilo_id;

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(Long agent_id) {
        this.agent_id = agent_id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Oglas(OglasDTO dto) {
        this.id = dto.getId();
        this.vozilo_id = dto.getVozilo_id();
        this.mesto = dto.getMesto();
        this.cena = dto.getCena();
        this.popust = dto.getCena();
        this.cenaspopust = dto.getCenaspopust();
        this.agent_id=dto.getAgent_id();
        this.user_id=dto.getUser_id();
    }

    public Oglas() {
    }


    public Long getVozilo_id() {
        return vozilo_id;
    }

    public void setVozilo_id(Long vozilo_id) {
        this.vozilo_id = vozilo_id;
    }
}
