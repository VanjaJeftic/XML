package com.oglas.dto;

import java.time.LocalDateTime;

public class TerminZauzecaDTO {

	private Long id;
    
    private Long vozilo_id;
    
    private LocalDateTime zauzetod;
    
    private LocalDateTime zauzetdo;

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

	public TerminZauzecaDTO() {
		super();
	}
    
    

}
