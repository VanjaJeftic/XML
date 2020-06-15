package com.agentApp.app.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.agentApp.app.dto.TerminZauzecaDTO;

@Entity
@Table(name="terminzauzeca")
public class TerminZauzeca {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="zauzetod")
    private LocalDateTime zauzetod;
    
    @Column(name="zauzetdo")
    private LocalDateTime zauzetdo;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vozilo_id")
    private Vozilo vozilo;
    
    public TerminZauzeca(TerminZauzecaDTO termin, Vozilo v) {
    	super();
    	this.zauzetdo = termin.getZauzetdo();
    	this.zauzetod = termin.getZauzetod();
    	this.vozilo = v;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getZauzetod() {
		return zauzetod;
	}

	public void setZauzetod(LocalDateTime zauzetod) {
		this.zauzetod = zauzetod;
	}

	public LocalDateTime getZauzetdo() {
		return zauzetdo;
	}

	public void setZauzetdo(LocalDateTime zauzetdo) {
		this.zauzetdo = zauzetdo;
	}
	
	

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public TerminZauzeca() {
		super();
	}
}
