package com.oglas.dto;


import java.time.LocalDateTime;


public class CenovnikDTO {

	private Long id;
	
    private Long owner;
    
    private String naziv;
    
    private LocalDateTime vaziod;
    
    private String dat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public LocalDateTime getVaziod() {
		return vaziod;
	}

	public void setVaziod(LocalDateTime vaziod) {
		this.vaziod = vaziod;
	}

	
	
	public String getDat() {
		return dat;
	}

	public void setDat(String dat) {
		this.dat = dat;
	}

	public CenovnikDTO() {
		super();
	}

    

	
}

