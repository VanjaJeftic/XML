package com.zahtev.service;

import java.util.List;

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
	public Long getLastGroupID() {
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		if(zahtevi == null || zahtevi.size() == 0) {
			return 0L;
		}
		int size = zahtevi.size() - 1;
		return (zahtevi.get(size)).getBundle_id();
	}
}
