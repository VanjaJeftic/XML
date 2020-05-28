package com.agentApp.app.models;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="zahtev")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Zahtev implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "oglas_id")
    private Oglas oglas;

    @Column(name="bundle_id")
    private Long Bundle_id;

    @Column(name="bundle")
    private boolean bundle;

    @ManyToOne
    @JoinColumn(name = "podnosilac_id")
    private Korisnik podnosilac;

    @Column(name="preuzimanje")
    private LocalDateTime preuzimanje;

    @Column(name="povratak")
    private LocalDateTime povratak;

    @Column(name="status")
    private String status;

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

    public Long getBundle_id() {
        return Bundle_id;
    }

    public void setBundle_id(Long bundle_id) {
        Bundle_id = bundle_id;
    }

    public boolean isBundle() {
        return bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle = bundle;
    }

    public Korisnik getPodnosilac() {
        return podnosilac;
    }

    public void setPodnosilac(Korisnik podnosilac) {
        this.podnosilac = podnosilac;
    }

    public LocalDateTime getPreuzimanje() {
        return preuzimanje;
    }

    public void setPreuzimanje(LocalDateTime preuzimanje) {
        this.preuzimanje = preuzimanje;
    }

    public LocalDateTime getPovratak() {
        return povratak;
    }

    public void setPovratak(LocalDateTime povratak) {
        this.povratak = povratak;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Zahtev() {
    }
}


