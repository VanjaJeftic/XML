package com.messages.messages.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messages.messages.dto.PorukaDTO;
import com.messages.messages.model.Poruka;
import com.messages.messages.repository.PorukaRepository;

@Service
public class PorukaService {

	
	@Autowired
	private PorukaRepository porukaRepository;
	
	//@Autowired
	//private ZahtevRepository zahtevRepository;
	
	
	public boolean create(PorukaDTO porukaDTO, String username) {
		
		if(  porukaDTO.getSadrzaj()==null || porukaDTO.getBundle()==null) {
		
		return false;
		}		
			LocalDateTime lt  = LocalDateTime.now();
			System.out.println(lt);
			Poruka p=new Poruka();
			p.setDatum(lt);
			p.setSadrzaj(porukaDTO.getSadrzaj());
			p.setBundle(porukaDTO.getBundle());
			p.setIznm(porukaDTO.isIznm());
			p.setDat(lt.toString());
			p.setKreator(username);
			porukaRepository.save(p);
			
		System.out.println("Dobro je");
		return true;
		
	}


	public Poruka findByZahtev(Long id) {
		List<Poruka> poruke=this.porukaRepository.findAll();
		for(Poruka pp:poruke) {
			if(pp.getBundle()==id) {
				return pp;
			}
		}
		return null;
	}

	public List<Poruka> findByZahtevList(Long id) {
		List<Poruka> poruke=this.porukaRepository.findAll();
		List<Poruka> vrati=new ArrayList<Poruka>();
		for(Poruka pp:poruke) {
			if(pp.getBundle()==id) {
				vrati.add(pp);
				System.out.println(pp.getDatum());
			}
		}
		return vrati;
	}

	


	
}