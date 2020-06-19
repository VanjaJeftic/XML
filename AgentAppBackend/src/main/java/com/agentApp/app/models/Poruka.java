package com.agentApp.app.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="poruka")
public class Poruka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="sadrzaj")
    private String sadrzaj;

    @Column(name = "datum")
    private LocalDateTime datum;
    
    @Column(name="zahtev_id")
    private Long zahtev;

    @Column(name="koIznm")
    private boolean iznm;
    
    @Column(name="kreator")
    private String kreator;
    
    @Column(name="dat")
    private String dat;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 
    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }


	public Long getZahtev() {
		return zahtev;
	}

	public void setZahtev(Long zahtev) {
		this.zahtev = zahtev;
	}

	
	public boolean isIznm() {
		return iznm;
	}

	public void setIznm(boolean iznm) {
		this.iznm = iznm;
	}


	public String getKreator() {
		return kreator;
	}

	public void setKreator(String kreator) {
		this.kreator = kreator;
	}

	public String getDat() {
		return dat;
	}

	public void setDat(String dat) {
		this.dat = dat;
	}

	public Poruka() {
    }
}
