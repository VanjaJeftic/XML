package com.agentApp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.models.Oglas;
import com.agentApp.app.services.SearchService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@GetMapping
	public ResponseEntity<?> search(@RequestBody Oglas oglas) {
		return new ResponseEntity<>(oglas, HttpStatus.OK);
	}
}
