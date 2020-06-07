package com.admin.adminServis.dto;

public class ModelVozilaDTO {

    private Long id;
    private String naziv;
    private String id_marke; //ovo je zapravo naziv marke a ne id

    public String getId_marke() {
        return id_marke;
    }

    public void setId_marke(String id_marke) {
        this.id_marke = id_marke;
    }

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

    public ModelVozilaDTO() {
    }

    public ModelVozilaDTO(Long id, String naziv,String id_marke) {
        this.id = id;
        this.naziv = naziv;
        this.id_marke=id_marke;
    }
}
