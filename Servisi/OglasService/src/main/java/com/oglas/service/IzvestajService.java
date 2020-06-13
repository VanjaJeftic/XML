package com.oglas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oglas.dto.IzvestajDTO;
import com.oglas.model.Izvestaj;
import com.oglas.model.Vozilo;
import com.oglas.repository.IzvestajRepository;

@Service
public class IzvestajService {

	@Autowired
	private IzvestajRepository izvestajRepository;
	@Autowired
	private VoziloService voziloservice;
	
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
	
	public Izvestaj sacuvajIzvestaj(IzvestajDTO izvestajDTO) {
		Vozilo vozilo = this.voziloservice.getVozilo(izvestajDTO.getVozilo());
		
		Izvestaj newIzvestaj = new Izvestaj(izvestajDTO, vozilo);
		
		return this.save(newIzvestaj);
	}
}
