package com.agentApp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.models.Oglas;
import com.agentApp.app.repository.OglasRepository;

@Service
public class OglasService {

	@Autowired
	private OglasRepository oglasRepo;
	
	public Oglas findOneOglas(Long oglasID) {
		return oglasRepo.findById(oglasID).get();
	}
	
	public List<Oglas> getAllOglasi(){
		return oglasRepo.findAll();
	}
}
