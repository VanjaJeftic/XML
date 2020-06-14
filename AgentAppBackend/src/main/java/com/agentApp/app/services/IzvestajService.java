package com.agentApp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.dto.IzvestajDTO;
import com.agentApp.app.models.Izvestaj;
import com.agentApp.app.models.Vozilo;
import com.agentApp.app.models.Zahtev;
import com.agentApp.app.repository.IzvestajRepository;

@Service
public class IzvestajService {

	@Autowired
	private IzvestajRepository izvestajRepository;
	@Autowired
	private VoziloService voziloService;
	@Autowired
	private ZahtevService zahtevService;
	@Autowired
	private TerminZauzecaService terminService;
	
	public Izvestaj getOneIzvestaj(Long zahtevID, Long voziloID) {
		List<Izvestaj> izvestaji = this.izvestajRepository.findAll();
		
		for(Izvestaj i : izvestaji) {
			if(i.getZahtev() == null) {
				System.out.println("Izvestaj je null");
				//return null;
			}else {
				System.out.println("Ulazi u if petlju izvestaj Servisa:");
				if(i.getZahtev().getId().equals(zahtevID) && i.getVozilo().getId().equals(voziloID)) {
					System.out.println("Izvestaj u If je: " + i.getPredjeniKm() + ", ostalo: " + i);
					return i;
				}
			}
		}
		return null;
	}
	
	public Zahtev getOneIzvestaj(Long id) {
		List<Izvestaj> izvestaji = this.izvestajRepository.findAll();
		for(Izvestaj i : izvestaji) {
			if(i.getZahtev().getId().equals(id)) {
				return i.getZahtev();
			}
		}
		return null;
	}
	
	public void ukloniTerminZauzeca(Izvestaj i) {
		Vozilo v = i.getVozilo();
		Zahtev z = i.getZahtev();
		this.terminService.obrisiTermin(v, z);
	}
	
	public Izvestaj save(Izvestaj i) {
		return this.izvestajRepository.save(i);
	}
	
	public Izvestaj sacuvajIzvestaj(IzvestajDTO izvestajDTO) {
		Vozilo v = this.voziloService.getVozilo(izvestajDTO.getVozilo());
		Zahtev z = this.zahtevService.getOneZahtev(izvestajDTO.getZahtev());
		
		Izvestaj newIzvestaj = new Izvestaj(izvestajDTO, v, z);
		
		return this.save(newIzvestaj);
	}
}
