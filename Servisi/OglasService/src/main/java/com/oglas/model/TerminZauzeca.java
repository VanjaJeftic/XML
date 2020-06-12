package com.oglas.model;

import java.io.Serializable;
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

import com.oglas.dto.TerminZauzecaDTO;

@Entity
@Table(name="terminzauzeca")
public class TerminZauzeca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    @Column(name="vozilo_id")
//    private Long vozilo_id;
    
    @Column(name="zauzetod")
    private LocalDateTime zauzetod;
    
    @Column(name="zauzetdo")
    private LocalDateTime zauzetdo;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vozilo vehicle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Long getVozilo_id() {
//		return vozilo_id;
//	}
//
//	public void setVozilo_id(Long vozilo_id) {
//		this.vozilo_id = vozilo_id;
//	}

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
	

	public Vozilo getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vozilo vehicle) {
		this.vehicle = vehicle;
	}

	public TerminZauzeca() {
		super();
	}
	
	public TerminZauzeca(TerminZauzecaDTO terminDTO) {
		//this.vozilo_id=terminDTO.getVozilo_id();
		this.zauzetod=terminDTO.getZauzetod();
		this.zauzetdo=terminDTO.getZauzetdo();
	}
	
	public TerminZauzeca(TerminZauzecaDTO terminDTO, Vozilo vehicle) {
		//this.vozilo_id=terminDTO.getVozilo_id();
		this.zauzetod=terminDTO.getZauzetod();
		this.zauzetdo=terminDTO.getZauzetdo();
		this.vehicle = vehicle;
	}
    
    

}
