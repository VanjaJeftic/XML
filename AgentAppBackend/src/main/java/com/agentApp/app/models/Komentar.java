package com.agentApp.app.models;

import com.agentApp.app.dto.KomentarDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="komentar")
public class Komentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="oglas_id")
    private Long oglas_id;

    @Column(name="korisnik_id")
    private Long korisnik_id;

    @Column(name="datum")
    private LocalDateTime datum;

    @Column(name="ocena")
    private int ocena;

    @Column(name="sadrzaj")
    private String sadrzaj;

    @Column(name="odgovor_id")
    private Long odgovor_id;

    @Column(name="odobren")//od strane admina
    private boolean odobren;

    @Column(name="odbijen")//od strane admina
    private boolean odbijen;

    public Komentar(Komentar komentar) {
    }

/*
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "oglas_id")
    private Oglas oglas;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;
*/

    public Long getOglas_id() {
        return oglas_id;
    }

    public void setOglas_id(Long oglas_id) {
        this.oglas_id = oglas_id;
    }

    public Long getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(Long korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Long getOdgovor_id() {
        return odgovor_id;
    }

    public void setOdgovor_id(Long odgovor_id) {
        this.odgovor_id = odgovor_id;
    }

    public boolean isOdobren() {
        return odobren;
    }

    public void setOdobren(boolean odobren) {
        this.odobren = odobren;
    }

    public boolean isOdbijen() {
        return odbijen;
    }

    public void setOdbijen(boolean odbijen) {
        this.odbijen = odbijen;
    }

//@OneToOne
    //@JoinColumn(name="odgovor_id")
   // private Odgovor odgovor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }


    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Komentar(KomentarDTO dto) {
        this.id = dto.getId();
        this.oglas_id = dto.getOglas_id();
        this.korisnik_id = dto.getKorisnik_id();
        this.datum = dto.getDatum();
        this.ocena = dto.getOcena();
        this.sadrzaj = dto.getSadrzaj();
        this.odgovor_id = dto.getOdgovor_id();
        this.odbijen=dto.isOdbijen();
        this.odobren=dto.isOdobren();
    }

    public Komentar() {
    }
}
