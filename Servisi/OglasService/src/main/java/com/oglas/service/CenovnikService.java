package com.oglas.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oglas.connections.UserConnection;
import com.oglas.dto.CenovnikDTO;
import com.oglas.dto.UserViewDTO;
import com.oglas.model.Cenovnik;
import com.oglas.repository.CenovnikRepository;
import com.oglas.repository.StavkaCenovnikaRepository;


@Service
public class CenovnikService {
	
	@Autowired
	CenovnikRepository cenovnikRepository;
	
	@Autowired
	StavkaCenovnikaRepository stavkaRepository;


	public Cenovnik createCenovnik(CenovnikDTO cenovnikDTO) {
		
		
	
		Cenovnik cenovnik=new Cenovnik(cenovnikDTO);
		cenovnik.setOwner(cenovnikDTO.getOwner());
		this.cenovnikRepository.save(cenovnik);
		return cenovnik;
	}
	
	
	public Cenovnik updateCenovnik(CenovnikDTO cenovnikDTO) {
		
		Cenovnik cenovnik=this.cenovnikRepository.findById(cenovnikDTO.getId()).get();
		cenovnik.setId(cenovnik.getId());
		cenovnik.setNaziv(cenovnikDTO.getNaziv());
		cenovnik.setVaziod(cenovnikDTO.getVaziod());
		cenovnik.setDat(cenovnikDTO.getVaziod().toString());
		
		return this.cenovnikRepository.save(cenovnik);
	}
	

	public void deleteCenovnik(Long id) {
		cenovnikRepository.deleteById(id);
		return;
	}
	
	
}
