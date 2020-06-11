package com.agentApp.app.dto;

public class KlasaVozilaDTO {

    private Long id;
    private String naziv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public KlasaVozilaDTO() {
    }

    public KlasaVozilaDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }



}
