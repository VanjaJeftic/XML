package com.search.dto;

public class SearchDTO {
	private String mesto;
    private String datumi;
    private String marka;
    private String model;
    private String minimalnaCena;
    private String maksimalnaCena;
    
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
    
    
}
