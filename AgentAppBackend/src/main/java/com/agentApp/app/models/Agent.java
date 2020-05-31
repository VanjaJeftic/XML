package com.agentApp.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")	//, referencedColumnName = "user_id"
    private User user;

    @Column(name="nazivfirme")
    private String nazivfirme;

    @Column(name="pmb")
    private int pmb; //Poslovni matični broj

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

    public String getNazivfirme() {
        return nazivfirme;
    }

    public void setNazivfirme(String nazivfirme) {
        this.nazivfirme = nazivfirme;
    }

    public int getPmb() {
        return pmb;
    }

    public void setPmb(int pmb) {
        this.pmb = pmb;
    }

    public Agent() {
    }
}
