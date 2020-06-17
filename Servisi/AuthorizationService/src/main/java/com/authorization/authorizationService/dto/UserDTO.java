package com.authorization.authorizationService.dto;

import com.authorization.authorizationService.model.Role;

import java.util.List;

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String ime;
    private String prezime;
    private String adresa;
    private String mesto;
    private Integer telefon;
    private String potvrdalozinke;

    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private List<Role> roles;
    private boolean nalogAktivan;

    public UserDTO() {

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public UserDTO(List<Role>roles,Long id, String username, String password, String email, boolean enabled, boolean accountNonExpired,boolean nalogAktivan, boolean credentialsNonExpired, boolean accountNonLocked, String ime, String prezime, String adresa, String mesto, Integer telefon, String potvrdalozinke) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.roles=roles;
        this.nalogAktivan=nalogAktivan;
        this.ime=ime;
        this.prezime=prezime;
        this.adresa=adresa;
        this.mesto=mesto;
        this.telefon=telefon;
        this.potvrdalozinke=potvrdalozinke;
    }
}
