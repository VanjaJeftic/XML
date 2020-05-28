package com.agentApp.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.models.Korisnik;
import com.agentApp.app.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepo;
	
	public Korisnik findOneKorisnik(Long id) {
		return korisnikRepo.findById(id).get();
	}
}
