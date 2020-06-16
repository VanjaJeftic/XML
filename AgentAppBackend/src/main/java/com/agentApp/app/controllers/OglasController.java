package com.agentApp.app.controllers;

import java.util.List;

import com.agentApp.app.dto.OglasDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import com.agentApp.app.dto.OglasDTO;
import com.agentApp.app.models.Oglas;
import com.agentApp.app.services.OglasService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/oglas")
public class OglasController {

	protected final static Logger logger = LoggerFactory.getLogger(OglasController.class);

	@Autowired
	private OglasService oglasService;
	
	@GetMapping
	public ResponseEntity<?> getAllOglasi(){
		List<Oglas> oglasi = oglasService.getAllOglasi();
		logger.info("Lista svih oglasa");
		return new ResponseEntity<List<Oglas>>(oglasi, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOglas(@PathVariable("id") Long id){
		Oglas o = oglasService.findOneOglas(id);
		logger.info("Pronadjen je oglas");
		return new ResponseEntity<Oglas>(o, HttpStatus.OK);
	}

	@PostMapping("/novi")
	@PreAuthorize("hasAuthority('create_oglas')")
	public ResponseEntity<?> create(@RequestBody OglasDTO ovDTO) {

		Oglas oglas = this.oglasService.createOrder(ovDTO);
		//Oglas search = this.searchConnection.createSearch(new Oglas(ovDTO));
		logger.info("Kreirana je narudzbina preko oglasa");
		return new ResponseEntity<>(oglas, HttpStatus.OK);
	}

	@GetMapping("/oglas/{oglas}")
	public ResponseEntity<?> getOglas(@ModelAttribute("oglas") Oglas dto){
		//Oglas o = oglasService.findOneOglas(id);
		logger.info("Pronadjen je oblas");
		return new ResponseEntity<Oglas>(dto, HttpStatus.OK);
	}


}
