package com.agentApp.app.controllers;

import java.util.List;

import com.agentApp.app.dto.OglasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agentApp.app.models.Oglas;
import com.agentApp.app.services.OglasService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/oglas")
public class OglasController {

	@Autowired
	private OglasService oglasService;
	
	@GetMapping
	public ResponseEntity<?> getAllOglasi(){
		List<Oglas> oglasi = oglasService.getAllOglasi();
		return new ResponseEntity<List<Oglas>>(oglasi, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOglas(@PathVariable("id") Long id){
		Oglas o = oglasService.findOneOglas(id);
		return new ResponseEntity<Oglas>(o, HttpStatus.OK);
	}


	@GetMapping("/oglas/{oglas}")
	public ResponseEntity<?> getOglas(@ModelAttribute("oglas") Oglas dto){
		System.out.println("oglas je "+ dto.getId());
		//Oglas o = oglasService.findOneOglas(id);
		return new ResponseEntity<Oglas>(dto, HttpStatus.OK);
	}


}
