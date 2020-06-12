package com.oglas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oglas.dto.IzvestajDTO;
import com.oglas.model.Izvestaj;
import com.oglas.service.IzvestajService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/izvestaj")
public class IzvestajController {

	@Autowired
	private IzvestajService izvestajService;
	
	@GetMapping("/{id}")
	public IzvestajDTO getIzvestaj(@PathVariable("id") Long id) {
		Izvestaj i = izvestajService.getOneIzvestaj(id);
		if(i == null) {
			return null;
		}else {
			return new IzvestajDTO(i);
		}
	}
}
