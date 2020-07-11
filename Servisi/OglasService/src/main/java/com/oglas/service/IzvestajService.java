package com.oglas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oglas.connections.ZahtevConnection;
import com.oglas.dto.IzvestajDTO;
import com.oglas.dto.ZahtevDTO;
import com.oglas.model.Izvestaj;
import com.oglas.model.Vozilo;
import com.oglas.repository.IzvestajRepository;

@Service
public class IzvestajService {

	@Autowired
	private IzvestajRepository izvestajRepository;
	@Autowired
	private VoziloService voziloservice;
	@Autowired
	private ZahtevConnection zahtevConnection;
	@Autowired
	private TerminZauzecaService terminService;
	
	public Izvestaj getOneIzvestaj(Long vozilo_id, Long zahtev) {
		List<Izvestaj> izvestaji = this.izvestajRepository.findAll();
		
		for(Izvestaj i : izvestaji) {
			if(i.getZahtev() == null) {
				//return null;
			}else {
				if(i.getVozilo().getId().equals(vozilo_id) && i.getZahtev().equals(zahtev)) {
					return i;
				}
			}
		}
		return null;
	}
	
	public Izvestaj save(Izvestaj i) {
		return this.izvestajRepository.save(i);
	}
	
	//Dobavlja poslednji upisani BundleID
	public Long getLastGroupID() {
		List<Izvestaj> izvestaji = this.izvestajRepository.findSortedIzvestaj();
		return (izvestaji.get(0).getId());
	}
	
	public Izvestaj sacuvajIzvestaj(IzvestajDTO izvestajDTO) {
		Vozilo vozilo = this.voziloservice.getVozilo(izvestajDTO.getVozilo());
		
		int predjeniKmIzvestaj = Integer.parseInt(izvestajDTO.getPredjeniKm());
		int predjeniKilomentri = Integer.parseInt(vozilo.getPredjeniKm());
		int sum = predjeniKmIzvestaj + predjeniKilomentri;
		vozilo.setPredjeniKm(String.valueOf(sum));
		
		
		Izvestaj newIzvestaj = new Izvestaj(izvestajDTO, vozilo);
		
		this.voziloservice.saveVozilo(vozilo);
		return this.save(newIzvestaj);
	}
	
	public void ukloniTerminZauzeca(Izvestaj i) {
		ZahtevDTO z = this.zahtevConnection.getOneZahtev(i.getZahtev());
		this.terminService.obrisiTermin(i.getVozilo(), z);
	}
}
