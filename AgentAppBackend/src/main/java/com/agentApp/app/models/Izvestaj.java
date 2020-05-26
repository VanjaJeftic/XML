package com.agentApp.app.models;

import javax.persistence.*;

@Entity
@Table(name="izvestaj")
public class Izvestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "oglas_id")
    private Oglas oglas;

    @Column(name="brprkm")
    private String brprkm;

    @Column(name="dodatno")
    private String dodatno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }

    public String getBrprkm() {
        return brprkm;
    }

    public void setBrprkm(String brprkm) {
        this.brprkm = brprkm;
    }

    public String getDodatno() {
        return dodatno;
    }

    public void setDodatno(String dodatno) {
        this.dodatno = dodatno;
    }

    public Izvestaj() {
    }
}
