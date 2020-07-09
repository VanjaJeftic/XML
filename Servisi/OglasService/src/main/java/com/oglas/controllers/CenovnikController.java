package com.oglas.controllers;

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

import com.oglas.dto.CenovnikDTO;
import com.oglas.model.Cenovnik;
import com.oglas.repository.CenovnikRepository;
import com.oglas.service.CenovnikService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cenovnik")
public class CenovnikController {
	
	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private CenovnikRepository cenovnikRepository;
	
	@PostMapping
	public ResponseEntity<?> newCenovnik(@RequestBody CenovnikDTO cenovnikDTO){
		
		System.out.println("Kreiram cenovnik"+cenovnikDTO.getNaziv() + cenovnikDTO.getVaziod());
		
		Cenovnik cenovnik=this.cenovnikService.createCenovnik(cenovnikDTO);
		
		return new ResponseEntity<>(cenovnik,HttpStatus.OK);
		
	}
	
	@PutMapping
	public ResponseEntity<?> updateCenovnik(@RequestBody CenovnikDTO cenovnikDTO){
		
		Optional<Cenovnik> cenovnik=cenovnikRepository.findById(cenovnikDTO.getId());
		
		if(cenovnik.isPresent()) {
			this.cenovnikService.updateCenovnik(cenovnikDTO);
			
			return new ResponseEntity<>("Succesful updated cenovnik",HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCenovnik(@PathVariable("id") Long id){
	
		try {
			System.out.println("Brisem");
			cenovnikService.deleteCenovnik(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("Ne brisem");
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("moji/{id}")
	List<Cenovnik> getCenovnici(@PathVariable("id")  Long userId){
		return cenovnikRepository.findByOwner(userId);
	}
	
	@GetMapping("/{id}")
	Cenovnik getCenovnik(@PathVariable("id") Long id){
		//User u = this.userService.findByUsername(p.getName());	
		
		Cenovnik c=cenovnikRepository.findById(id).get();
		
		return c;
		
	}
	

}
