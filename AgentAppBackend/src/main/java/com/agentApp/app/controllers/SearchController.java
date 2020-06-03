package com.agentApp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.models.Oglas;
import com.agentApp.app.services.OglasService;
import com.agentApp.app.services.SearchService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Autowired 
	private OglasService oglasService;
	
	@PostMapping
	public ResponseEntity<?> search(@RequestBody Oglas oglas) {
		List<Oglas> pretrazeniOglasi = new ArrayList<>();
		List<Oglas> sviOglasi = oglasService.getAllOglasi();
		
		for(Oglas o : sviOglasi) {
			if(o.getMesto().toLowerCase().contains(oglas.getMesto().toLowerCase()) ) {
				System.out.print("true \n");
				pretrazeniOglasi.add(o);
			}
			
		}
		return new ResponseEntity<>(pretrazeniOglasi, HttpStatus.OK);
	}
	
	
}
