package com.agentApp.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.OglasDTO;
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

	@PostMapping("/novi")
	//@PreAuthorize("hasAuthority('create_oglas')")
	public ResponseEntity<?> create(@RequestBody OglasDTO ovDTO) {

		System.out.println("Mesto "+ovDTO.getMesto()+" cena "+ovDTO.getCena()+" popust "+ovDTO.getPopust()+" id vozila " +ovDTO.getVozilo_id() + " datumi "+" od "+ ovDTO.getSlobodanOd() + " do "+ ovDTO.getSlobodanDo());
		Oglas oglas = this.oglasService.createOrder(ovDTO);
		//Oglas search = this.searchConnection.createSearch(new Oglas(ovDTO));

		return new ResponseEntity<>(oglas, HttpStatus.OK);
	}



}
