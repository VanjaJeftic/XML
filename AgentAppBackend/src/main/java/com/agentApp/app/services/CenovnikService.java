package com.agentApp.app.services;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.dto.CenovnikDTO;
import com.agentApp.app.models.Cenovnik;
import com.agentApp.app.models.User;
import com.agentApp.app.repository.CenovnikRepository;
import com.agentApp.app.repository.StavkaCenovnikaRepository;

import com.agentApp.app.exception.NotFoundException;

@Service
public class CenovnikService {
	
	@Autowired
	CenovnikRepository cenovnikRepository;
	
	@Autowired
	StavkaCenovnikaRepository stavkaRepository;
	
	@Autowired
	private UserService userService;

	

	public Cenovnik createCenovnik(CenovnikDTO cenovnikDTO,Principal p) {
		
		User u = this.userService.findByUsername(p.getName());
	
		Cenovnik cenovnik=new Cenovnik(cenovnikDTO);
		cenovnik.setUser(u);
		this.cenovnikRepository.save(cenovnik);
		return cenovnik;
	}
	
	
	public Cenovnik updateCenovnik(CenovnikDTO cenovnikDTO) {
		
		Cenovnik cenovnik=this.cenovnikRepository.findById(cenovnikDTO.getId())
				.orElseThrow(() -> new NotFoundException("Cenovnik with that id doesn't exist!"));
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
