package com.authorization.authorizationService.dto;

public class AgentDTO {

    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String adresa;
    private String maticnibroj;
private String email;
    private String nazivfirme;


    public AgentDTO() {
    }

    public AgentDTO(String ime, String prezime, String username, String password, String adresa, String maticnibroj, String nazivfirme, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.adresa = adresa;
        this.maticnibroj = maticnibroj;
        this.nazivfirme = nazivfirme;
        this.email=email;
    }

    public String getIme() {
        return ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMaticnibroj() {
        return maticnibroj;
    }

    public void setMaticnibroj(String maticnibroj) {
        this.maticnibroj = maticnibroj;
    }

    public String getNazivfirme() {
        return nazivfirme;
    }

    public void setNazivfirme(String nazivfirme) {
        this.nazivfirme = nazivfirme;
    }
}
