package com.agentApp.app.models;

import javax.persistence.*;

import com.agentApp.app.dto.IzvestajDTO;

@Entity
@Table(name="izvestaj")
public class Izvestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "vozilo_id")
	private Vozilo vozilo;

    @Column(name="predjenikm")
    private String predjeniKm;

    @Column(name="komentar")
    private String komentar;
    
    @OneToOne
    @JoinColumn(name="zahtev_id")
    private Zahtev zahtev;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public String getPredjeniKm() {
		return predjeniKm;
	}

	public void setPredjeniKm(String predjeniKm) {
		this.predjeniKm = predjeniKm;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Zahtev getZahtev() {
		return zahtev;
	}

	public void setZahtev(Zahtev zahtev) {
		this.zahtev = zahtev;
	}

	public Izvestaj() {
    }
	
	public Izvestaj(IzvestajDTO izvestaj, Vozilo v, Zahtev z) {
		super();
		this.predjeniKm = izvestaj.getPredjeniKm();
		this.komentar = izvestaj.getKomentar();
		this.zahtev = z;
		this.vozilo = v;
	}
}
