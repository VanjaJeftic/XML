package com.zahtev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahtev.model.Zahtev;
import com.zahtev.repository.ZahtevRepository;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	public Zahtev save(Zahtev z) {
		return this.zahtevRepository.save(z);
	}
}
