package com.agentApp.app.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="komentar")
public class Komentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "oglas_id")
    private Oglas oglas;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    @Column(name="datum")
    private LocalDateTime datum;

    @Column(name="ocena")
    private int Ocena;

    @Column(name="sadrzaj")
    private String sadrzaj;

    @OneToOne
    @JoinColumn(name="odgovor_id")
    private Odgovor odgovor;

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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public int getOcena() {
        return Ocena;
    }

    public void setOcena(int ocena) {
        Ocena = ocena;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Odgovor getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Odgovor odgovor) {
        this.odgovor = odgovor;
    }

    public Komentar() {
    }
}
