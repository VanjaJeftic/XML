package com.search.SearchService.dto;

public class SearchDTO {
	private String mesto;
    private String datumi;
    private String marka;
    private String model;
    private String minimalnaCena;
    private String maksimalnaCena;
    private String vrstaGoriva;
    private String tipMenjaca;
    private String klasa;
    private int predjeniKilometri;
    private int planiraniKilometri;
    private int brSedistaZaDecu;
    private String cdw;
    
    public SearchDTO() {
    	super();
    }

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getDatumi() {
		return datumi;
	}

	public void setDatumi(String datumi) {
		this.datumi = datumi;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMinimalnaCena() {
		return minimalnaCena;
	}

	public void setMinimalnaCena(String minimalnaCena) {
		this.minimalnaCena = minimalnaCena;
	}

	public String getMaksimalnaCena() {
		return maksimalnaCena;
	}

	public void setMaksimalnaCena(String maksimalnaCena) {
		this.maksimalnaCena = maksimalnaCena;
	}

	public String getVrstaGoriva() {
		return vrstaGoriva;
	}

	public void setVrstaGoriva(String vrstaGoriva) {
		this.vrstaGoriva = vrstaGoriva;
	}

	public String getTipMenjaca() {
		return tipMenjaca;
	}

	public void setTipMenjaca(String tipMenjaca) {
		this.tipMenjaca = tipMenjaca;
	}

	public String getKlasa() {
		return klasa;
	}

	public void setKlasa(String klasa) {
		this.klasa = klasa;
	}

	public int getPredjeniKilometri() {
		return predjeniKilometri;
	}

	public void setPredjeniKilometri(int predjeniKilometri) {
		this.predjeniKilometri = predjeniKilometri;
	}

	public int getPlaniraniKilometri() {
		return planiraniKilometri;
	}

	public void setPlaniraniKilometri(int planiraniKilometri) {
		this.planiraniKilometri = planiraniKilometri;
	}

	public int getBrSedistaZaDecu() {
		return brSedistaZaDecu;
	}

	public void setBrSedistaZaDecu(int brSedistaZaDecu) {
		this.brSedistaZaDecu = brSedistaZaDecu;
	}

	public String getCdw() {
		return cdw;
	}

	public void setCdw(String cdw) {
		this.cdw = cdw;
	}
    
    
}
