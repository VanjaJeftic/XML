package com.oglas.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OglasDTO {

    private Long id;

    private Long vozilo_id;

    private String mesto;

    // @Column(name="slike")
    //private ArrayList<String> slike;

    private Double cena;

    private Double popust;

    private Double cenaspopust;

    private Long user_id;

    private Long agent_id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OglasDTO(Long agent_id, Long user_id,Long id, Long vozilo_id, String mesto, Double cena, Double popust, Double cenaspopust) {
        this.id = id;
        this.vozilo_id = vozilo_id;
        this.mesto = mesto;
        this.cena = cena;
        this.popust = popust;
        this.cenaspopust = cenaspopust;
        this.user_id=user_id;
        this.agent_id=agent_id;
    }

    public OglasDTO() {
    }
}
