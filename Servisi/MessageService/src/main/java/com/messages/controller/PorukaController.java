package com.messages.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.messages.dto.PorukaDTO;
import com.messages.model.Poruka;
import com.messages.service.PorukaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/poruka")
public class PorukaController {

	@Autowired
	private PorukaService porukaService;
	
	protected final static Logger logger = LoggerFactory.getLogger(PorukaController.class);

	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody PorukaDTO porukaDTO){
		System.out.println("PORUKA "+porukaDTO.getSadrzaj()+porukaDTO.getBundle());
		if(porukaService.create(porukaDTO,"aaa")) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/{zahtevId}")
	public List<Poruka> sve(@PathVariable Long zahtevId){
		System.out.println("PORUKA "+ zahtevId);
		if(porukaService.findByZahtevList(zahtevId)!=null) {
			return porukaService.findByZahtevList(zahtevId);
		}else {
			return null;
		}
	}
}