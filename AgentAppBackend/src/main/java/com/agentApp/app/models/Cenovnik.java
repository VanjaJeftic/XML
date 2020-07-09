package com.agentApp.app.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.agentApp.app.dto.CenovnikDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cenovnik")
public class Cenovnik {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name="naziv")
    private String naziv;
    
    @Column(name="vaziod")
    private LocalDateTime vaziod;
    
    @Column(name="dat")
    private String dat;
    
    @JsonIgnore
	@OneToMany(mappedBy = "cenovnik", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<StavkaCenovnika> stavka = new HashSet<StavkaCenovnika>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Set<StavkaCenovnika> getStavka() {
		return stavka;
	}

	public void setStavka(Set<StavkaCenovnika> stavka) {
		this.stavka = stavka;
	}

	public Cenovnik() {
		super();
	}

	public Cenovnik(CenovnikDTO cenovnikDTO) {
		// TODO Auto-generated constructor stub
		this.naziv=cenovnikDTO.getNaziv();
		//this.user=new User(cenovnikDTO.getUser());
		this.vaziod=cenovnikDTO.getVaziod();
		this.dat=cenovnikDTO.getVaziod().toString();
	}

	public String getDat() {
		return dat;
	}

	public void setDat(String dat) {
		this.dat = dat;
	}
    
	
    
    
	
}
