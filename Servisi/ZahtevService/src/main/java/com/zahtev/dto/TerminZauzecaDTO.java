package com.zahtev.dto;

import java.time.LocalDateTime;

public class TerminZauzecaDTO {

    private Long vozilo_id;
    private LocalDateTime zauzetod;
    private LocalDateTime zauzetdo;
    
    public TerminZauzecaDTO() {
		super();
	}
    public TerminZauzecaDTO(Long vozilo, LocalDateTime zauzetod, LocalDateTime zauzetdo) {
		super();
		this.vozilo_id = vozilo;
		this.zauzetod = zauzetod;
		this.zauzetdo = zauzetdo;
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
}
