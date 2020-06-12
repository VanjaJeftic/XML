package com.oglas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oglas.model.Izvestaj;
import com.oglas.repository.IzvestajRepository;

@Service
public class IzvestajService {

	@Autowired
	private IzvestajRepository izvestajRepository;
	
	public Izvestaj getOneIzvestaj(Long vozilo_id) {
		List<Izvestaj> izvestaji = this.izvestajRepository.findAll();
		
		for(Izvestaj i : izvestaji) {
			if(i.getVozilo().getId().equals(vozilo_id)) {
				return i;
			}
		}
		return null;
	}
}
