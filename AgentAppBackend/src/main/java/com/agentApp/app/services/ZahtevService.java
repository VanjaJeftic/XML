package com.agentApp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.models.Zahtev;
import com.agentApp.app.repository.ZahtevRepository;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	public List<Zahtev> getAllZahtevi(){
		return zahtevRepository.findAll();
	}
	
	public Zahtev saveZahtev(Zahtev z) {
		return zahtevRepository.save(z);
	}
	
	public Long getLastGroupID() {
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		int size = zahtevi.size() - 1;
		return (zahtevi.get(size)).getBundle_id();
	}
}
