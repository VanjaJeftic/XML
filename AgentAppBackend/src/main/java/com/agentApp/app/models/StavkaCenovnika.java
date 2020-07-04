package com.agentApp.app.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agentApp.app.dto.StavkaCenovnikaDTO;

@Entity
@Table(name = "stavka")
public class StavkaCenovnika {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	  
	@ManyToOne
	@JoinColumn(name = "cenovnik_id")
	private Cenovnik cenovnik;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="oglas_id", referencedColumnName = "id")
	private Oglas oglas;
	
	@Column(name="cena_po_danu")
	private String cena_po_danu;
	
	@Column(name="cena_po_km")
	private String cena_po_km;
	
	@Column(name="cena_za_cdw")
	private String cena_za_cdw;
	
	@Column(name="br_dana_popust")
	private String br_dana_popust;
	
	@Column(name="popust")
	private String popust;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}



	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
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

	public StavkaCenovnika() {
		super();
	}

	public StavkaCenovnika(StavkaCenovnikaDTO stavkaDTO) {
		// TODO Auto-generated constructor stub
		this.br_dana_popust=stavkaDTO.getBr_dana_popust();
		this.cena_po_danu=stavkaDTO.getCena_po_danu();
		this.cena_po_km=stavkaDTO.getCena_po_km();
		this.cena_za_cdw=stavkaDTO.getCena_za_cdw();
		this.popust=stavkaDTO.getPopust();
	}
	
	
	
}
