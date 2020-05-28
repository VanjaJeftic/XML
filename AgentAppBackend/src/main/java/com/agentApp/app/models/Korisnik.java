package com.agentApp.app.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="korisnik")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")	//, referencedColumnName = "user_id"
    private User user;

    @Column(name="aktivan")
    private boolean aktivan;

    @Column(name="blokiran")
    private boolean blokiran;

    @Column(name="uklonjen")
    private boolean uklonjen;

    @Column(name="odbijenizahtevi")
    private int odbijeniZahtevi;
    
    @JsonIgnore
    @OneToMany(mappedBy = "podnosilac", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Zahtev> zahtevi = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }

    public boolean isUklonjen() {
        return uklonjen;
    }

    public void setUklonjen(boolean uklonjen) {
        this.uklonjen = uklonjen;
    }

    public int getOdbijeniZahtevi() {
        return odbijeniZahtevi;
    }

    public void setOdbijeniZahtevi(int odbijeniZahtevi) {
        this.odbijeniZahtevi = odbijeniZahtevi;
    }

    public Korisnik() {
    }
}
