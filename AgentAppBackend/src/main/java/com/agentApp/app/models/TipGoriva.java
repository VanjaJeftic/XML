package com.agentApp.app.models;

import com.agentApp.app.dto.TipGorivaDTO;

import javax.persistence.*;

@Entity
@Table(name="tipgoriva")
public class TipGoriva {

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

    public TipGoriva() {
    }

    public TipGoriva(TipGorivaDTO dto) {
        this.id = dto.getId();
        this.naziv = dto.getNaziv();
    }
}
