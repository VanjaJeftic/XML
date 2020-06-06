package com.agentApp.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="search")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Search implements Serializable {

    @Column(name="mesto")
    private String mesto;

    @Column(name="datumi")
    private String datumi;

    @Column(name="marka")
    private String marka;

    @Column(name="model")
    private String model;

    @Column(name="minimalnaCena")
    private String minimalnaCena;

    @Column(name="maksimalnaCena")
    private String maksimalnaCena;
    
    public Search() {
    	super();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
}
