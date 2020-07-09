package com.oglas.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oglas.dto.StavkaCenovnikaDTO;
import com.oglas.model.Cenovnik;
import com.oglas.model.StavkaCenovnika;
import com.oglas.repository.CenovnikRepository;
import com.oglas.repository.StavkaCenovnikaRepository;
import com.oglas.service.StavkaCenovnikaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/stavka")
public class StavkaCenovnikaController {

	
	@Autowired
	private StavkaCenovnikaService stavkaService;
	
	@Autowired
	private StavkaCenovnikaRepository stavkaRepository;
	
	@Autowired
	private CenovnikRepository cenovnikRepository;
	
	@PostMapping
	public ResponseEntity<?> newStavkaCenovnika(@RequestBody StavkaCenovnikaDTO stavkaDTO){
		
		System.out.println("Oglas id "+stavkaDTO.getOglas_id());
		
		StavkaCenovnika  stavka=this.stavkaService.createStavkaCenovnika(stavkaDTO);
		
		return new ResponseEntity<>(stavka,HttpStatus.OK);
		
	}
	
	@PutMapping
	public ResponseEntity<?> updateStavkaCenovnika(@RequestBody StavkaCenovnikaDTO stavkaDTO){
		
		Optional<StavkaCenovnika> stavka=stavkaRepository.findById(stavkaDTO.getId());
		
		if(stavka.isPresent()) {
			this.stavkaService.updateStavka(stavkaDTO);
			
			return new ResponseEntity<>("Succesful updated stavka cenovnika",HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteStavkaCenovnika(@PathVariable("id") Long id){
		
		try {
			stavkaService.deleteStavka(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/{id}")
	List<StavkaCenovnika> getStavkeCenovnika(@PathVariable("id") Long id){
		
		List<StavkaCenovnika> stavke=stavkaRepository.findAll();
		List<StavkaCenovnika> trazene=new ArrayList<StavkaCenovnika>();
		Cenovnik c=cenovnikRepository.findById(id).get();
		
		for(StavkaCenovnika stavka:stavke) {
			if(stavka.getCenovnik().equals(c.getNaziv())) {
				
				trazene.add(stavka);
			}
		}
		
		return trazene;
		
	}
	
	@GetMapping("/stavka/{id}")
	StavkaCenovnika getStavkaCenovnika(@PathVariable("id") Long id){
		
		List<StavkaCenovnika> stavke=stavkaRepository.findAll();
		
		for(StavkaCenovnika stavka:stavke) {
			if(stavka.getId().equals(id)) {
				
				return stavka;
			}
		}
		
		return null;
		
	}
	

	@GetMapping("/oglas/{oglas}")
	StavkaCenovnika getStavkaCenovnikaOglas(@PathVariable("oglas") Long id){
		
		List<StavkaCenovnika> stavke=stavkaRepository.findAll();
		
		for(StavkaCenovnika stavka:stavke) {
			if(stavka.getOglas().getId().equals(id)) {
				
				return stavka;
			}
		}
		
		return null;
		
	}
	
	
}
