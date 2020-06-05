package com.admin.adminServis.model;

import com.admin.adminServis.dto.ModelVozilaDTO;

import javax.persistence.*;

@Entity
@Table(name="modelvozila")
public class ModelVozila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="naziv")
    private String naziv;

    @Column(name="id_marke")
    private String id_marke;

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

    public ModelVozila() {
    }

    public ModelVozila(ModelVozilaDTO dto) {
        this.id = dto.getId();
        this.naziv = dto.getNaziv();
        this.id_marke=dto.getId_marke();
    }
}
