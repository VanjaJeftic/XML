package com.oglas.controllers;

import com.oglas.dto.OglasDTO;
import com.oglas.model.Oglas;
import com.oglas.repository.OglasRepository;
import com.oglas.service.OglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OglasController {

	@GetMapping("/hello-worlds")
	public String helloWorld() {
		return "Hello World ";
	}

	private OglasService oglasService;
	private OglasRepository oglasRepository;

	@Autowired
	public OglasController(OglasService oglasService){
		this.oglasService=oglasService;
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody OglasDTO oglasDTO) {
		Oglas oglas = this.oglasService.createOrder(oglasDTO);
		return new ResponseEntity<>(oglas, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody OglasDTO oglasDTO) {
		Optional<Oglas> oglasdata = oglasRepository.findById(oglasDTO.getId());
		if(oglasdata.isPresent()){
			this.oglasService.update(oglasDTO);
			return new ResponseEntity<>("Successful updated oglas", HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}


	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		try {
			oglasService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}


}
