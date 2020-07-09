package com.oglas.dto;


public class StavkaCenovnikaDTO {

	private Long id;
	  
	private String cenovnik;

	private VoziloDTO vozilo;

	private String cena_po_danu;

	private String cena_po_km;
	
	private String cena_za_cdw;

	private String br_dana_popust;
	
	private String popust;
	
	private Long oglas_id;
	 
	//private String cenovnik_naziv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(String cenovnik) {
		this.cenovnik = cenovnik;
	}

	public VoziloDTO getVozilo() {
		return vozilo;
	}

	public void setVozilo(VoziloDTO vozilo) {
		this.vozilo = vozilo;
	}

	public String getCena_po_danu() {
		return cena_po_danu;
	}

	public void setCena_po_danu(String cena_po_danu) {
		this.cena_po_danu = cena_po_danu;
	}

	public String getCena_po_km() {
		return cena_po_km;
	}

	public void setCena_po_km(String cena_po_km) {
		this.cena_po_km = cena_po_km;
	}

	public String getCena_za_cdw() {
		return cena_za_cdw;
	}

	public void setCena_za_cdw(String cena_za_cdw) {
		this.cena_za_cdw = cena_za_cdw;
	}

	public String getBr_dana_popust() {
		return br_dana_popust;
	}

	public void setBr_dana_popust(String br_dana_popust) {
		this.br_dana_popust = br_dana_popust;
	}

	public String getPopust() {
		return popust;
	}

	public void setPopust(String popust) {
		this.popust = popust;
	}

	

	public Long getOglas_id() {
		return oglas_id;
	}

	public void setOglas_id(Long oglas_id) {
		this.oglas_id = oglas_id;
	}

	public StavkaCenovnikaDTO() {
		super();
	}
	
}
