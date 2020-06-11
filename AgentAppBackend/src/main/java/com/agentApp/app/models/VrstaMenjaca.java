package com.agentApp.app.models;

import com.agentApp.app.dto.VrstaMenjacaDTO;

import javax.persistence.*;

@Entity
@Table(name="vrstamenjaca")
public class VrstaMenjaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="naziv")
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

    public VrstaMenjaca() {
    }

    public VrstaMenjaca(VrstaMenjacaDTO dto) {
        this.id = dto.getId();
        this.naziv = dto.getNaziv();
    }
}
