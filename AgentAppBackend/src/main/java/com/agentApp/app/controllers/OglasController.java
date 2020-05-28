package com.agentApp.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.models.Oglas;
import com.agentApp.app.services.OglasService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/oglas")
public class OglasController {

	@Autowired
	private OglasService oglasService;
	
	@GetMapping
	public List<Oglas> getAllOglasi(){
		return oglasService.getAllOglasi();
	}
}
