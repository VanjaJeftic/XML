package com.agentApp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.models.Zahtev;
import com.agentApp.app.repository.ZahtevRepository;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepo;
	
	public List<Zahtev> getAllZahtevi(){
		return zahtevRepo.findAll();
	}
	
	public Zahtev saveZahtev(Zahtev z) {
		return zahtevRepo.save(z);
	}
	
	public Long getLastGroupID() {
		List<Zahtev> zahtevi = zahtevRepo.findAll();
		int size = zahtevi.size() - 1;
		return (zahtevi.get(size)).getBundle_id();
	}
}
