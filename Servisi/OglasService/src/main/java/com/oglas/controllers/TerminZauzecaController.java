package com.oglas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.oglas.dto.TerminZauzecaDTO;
import com.oglas.model.TerminZauzeca;
import com.oglas.repository.TerminZauzecaRepository;
import com.oglas.service.TerminZauzecaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/termin")
public class TerminZauzecaController {
	
	
	private TerminZauzecaRepository terminRepository;
	private TerminZauzecaService terminServis;
	
	
	@Autowired
	public TerminZauzecaController(TerminZauzecaRepository terminRepository, TerminZauzecaService terminServis) {
		super();
		this.terminRepository = terminRepository;
		this.terminServis = terminServis;
	}



	@PostMapping("/createTermin")
	public ResponseEntity<?> create(@RequestBody TerminZauzecaDTO terminDTO){
		
		TerminZauzeca termin=this.terminServis.createTermin(terminDTO);
		return new ResponseEntity<>(termin,HttpStatus.OK);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody TerminZauzecaDTO terminDTO){
		Optional<TerminZauzeca> termindata=terminRepository.findById(terminDTO.getId());
		if(termindata.isPresent()){
			this.terminServis.update(terminDTO);
			return new ResponseEntity<>("Successful updated oglas", HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	//@PreAuthorize("hasRole('ROLE_operator')")
	//@PreAuthorize("hasAuthority('delete_oglas')")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		try {
			terminServis.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}