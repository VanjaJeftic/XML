package com.oglas.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class OglasDTO {

    private Long id;

    private Long vozilo_id;

    private Long user_id;

    private String mesto;

    private Double cena;

    private Double popust;

    private Double cenaspopust;

    private LocalDateTime slobodanod;

    private LocalDateTime slobodando;
    
    private Boolean cdw;
    
  private Boolean maxkm;
    
    private String ogranicenjekm;
    
    private String oddat;
    
    private String dodat;
    
    private boolean ustavci;

    public Boolean getCdw() {
		return cdw;
	}

	public void setCdw(Boolean cdw) {
		this.cdw = cdw;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVozilo_id() {
        return vozilo_id;
    }

    public void setVozilo_id(Long vozilo_id) {
        this.vozilo_id = vozilo_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Double getPopust() {
        return popust;
    }

    public void setPopust(Double popust) {
        this.popust = popust;
    }

    public Double getCenaspopust() {
        return cenaspopust;
    }

    public void setCenaspopust(Double cenaspopust) {
        this.cenaspopust = cenaspopust;
    }

    public LocalDateTime getSlobodanod() {
        return slobodanod;
    }

    public void setSlobodanod(LocalDateTime slobodanod) {
        this.slobodanod = slobodanod;
    }

    public LocalDateTime getSlobodando() {
        return slobodando;
    }

    public void setSlobodando(LocalDateTime slobodando) {
        this.slobodando = slobodando;
    }

    public OglasDTO(Long id, Long vozilo_id, Long user_id, String mesto, Double cena, Double popust, Double cenaspopust, LocalDateTime slobodanod, LocalDateTime slobodando,Boolean cdw) {
        this.id = id;
        this.vozilo_id = vozilo_id;
        this.user_id = user_id;
        this.mesto = mesto;
        this.cena = cena;
        this.popust = popust;
        this.cenaspopust = cenaspopust;
        this.slobodanod = slobodanod;
        this.slobodando = slobodando;
        this.cdw=cdw;
    }

    public OglasDTO() {
    }

    public OglasDTO(Long vozilo_id, Long user_id, String mesto, Double cena, Double popust, Double cenaspopust, LocalDateTime slobodanod, LocalDateTime slobodando,Boolean cdw) {
        this.vozilo_id = vozilo_id;
        this.user_id = user_id;
        this.mesto = mesto;
        this.cena = cena;
        this.popust = popust;
        this.cenaspopust = cenaspopust;
        this.slobodanod = slobodanod;
        this.slobodando = slobodando;
        this.cdw=cdw;
    }

	public Boolean getMaxkm() {
		return maxkm;
	}

	public void setMaxkm(Boolean maxkm) {
		this.maxkm = maxkm;
	}

	public String getOgranicenjekm() {
		return ogranicenjekm;
	}

	public void setOgranicenjekm(String ogranicenjekm) {
		this.ogranicenjekm = ogranicenjekm;
	}

	public String getOddat() {
		return oddat;
	}

	public void setOddat(String oddat) {
		this.oddat = oddat;
	}

	public String getDodat() {
		return dodat;
	}

	public void setDodat(String dodat) {
		this.dodat = dodat;
	}

	public boolean isUstavci() {
		return ustavci;
	}

	public void setUstavci(boolean ustavci) {
		this.ustavci = ustavci;
	}

    

}
