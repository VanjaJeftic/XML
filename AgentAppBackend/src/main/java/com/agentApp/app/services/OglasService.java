package com.agentApp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.models.Oglas;
import com.agentApp.app.repository.OglasRepository;

@Service
public class OglasService {

	@Autowired
	private OglasRepository oglasRepository;
	
	public Oglas findOneOglas(Long id) {
		return oglasRepository.findById(id).get();
	}
	
	public List<Oglas> getAllOglasi(){
		return oglasRepository.findAll();
	}
}
