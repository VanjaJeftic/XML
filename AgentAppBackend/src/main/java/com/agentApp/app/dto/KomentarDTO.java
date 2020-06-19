package com.agentApp.app.dto;

import com.agentApp.app.models.Komentar;
import com.agentApp.app.models.Oglas;

import java.time.LocalDateTime;

public class KomentarDTO {

    private Long id;
    private Long oglas_id;
    private Long korisnik_id;
    private LocalDateTime datum;
    private int ocena;
    private String sadrzaj;
    private Long odgovor_id;
    private boolean odobren;
    private boolean odbijen;

    public KomentarDTO(Long id, Long oglas_id, Long korisnik_id, LocalDateTime datum, int ocena, String sadrzaj, Long odgovor_id, boolean odobren, boolean odbijen) {
        this.id = id;
        this.oglas_id = oglas_id;
        this.korisnik_id = korisnik_id;
        this.datum = datum;
        this.ocena = ocena;
        this.sadrzaj = sadrzaj;
        this.odgovor_id = odgovor_id;
        this.odobren = odobren;
        this.odbijen = odbijen;
    }

    public KomentarDTO(Komentar komentar) {
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

    public KomentarDTO() {
    }

    public Long getOdgovor_id() {
        return odgovor_id;
    }

    public void setOdgovor_id(Long odgovor_id) {
        this.odgovor_id = odgovor_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }
}
