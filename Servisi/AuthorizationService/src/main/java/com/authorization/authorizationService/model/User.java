package com.authorization.authorizationService.model;

import com.authorization.authorizationService.dto.UserDTO;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    public User() {
    }

    public User(UserDTO user) {
        this.nalogAktivan=user.isNalogAktivan();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.accountNonExpired = user.isAccountNonExpired();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.roles = user.getRoles();
        this.nalogAktivan=user.isNalogAktivan();
        this.ime=user.getIme();
        this.prezime=user.getPrezime();
        this.adresa=user.getAdresa();
        this.mesto=user.getMesto();
        this.telefon=user.getTelefon();
        this.potvrdalozinke=user.getPotvrdalozinke();
        this.maticnibroj=user.getMaticnibroj();
        this.nazivfirme=user.getNazivfirme();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "nazivfirme")
    private String nazivfirme;

    @Column(name = "mesto")
    private String mesto;

    @Column(name = "telefon")
    private Integer telefon;

    @Column(name = "potvrdalozinke")
    private String potvrdalozinke;

    @Column(name = "email")
    private String email;

    @Column(name="maticnibroj")
    private String maticnibroj;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
    @Column(name = "nalogaktivan")
    private boolean nalogAktivan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public User(User user) {
    }

    public String getNazivfirme() {
        return nazivfirme;
    }

    public void setNazivfirme(String nazivfirme) {
        this.nazivfirme = nazivfirme;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public String getPotvrdalozinke() {
        return potvrdalozinke;
    }

    public void setPotvrdalozinke(String potvrdalozinke) {
        this.potvrdalozinke = potvrdalozinke;
    }

    public boolean isNalogAktivan() {
        return nalogAktivan;
    }

    public void setNalogAktivan(boolean nalogAktivan) {
        this.nalogAktivan = nalogAktivan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getMaticnibroj() {
        return maticnibroj;
    }

    public void setMaticnibroj(String maticnibroj) {
        this.maticnibroj = maticnibroj;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
