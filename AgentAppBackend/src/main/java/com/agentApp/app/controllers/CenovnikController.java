package com.agentApp.app.controllers;

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

import com.agentApp.app.dto.CenovnikDTO;
import com.agentApp.app.models.Cenovnik;
import com.agentApp.app.models.User;
import com.agentApp.app.repository.CenovnikRepository;
import com.agentApp.app.services.CenovnikService;
import com.agentApp.app.services.UserService;

import java.security.Principal;
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
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> newCenovnik(@RequestBody CenovnikDTO cenovnikDTO,Principal p){
		
		System.out.println("Kreiram cenovnik"+cenovnikDTO.getNaziv() + cenovnikDTO.getVaziod());
		
		Cenovnik cenovnik=this.cenovnikService.createCenovnik(cenovnikDTO,p);
		
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
			cenovnikService.deleteCenovnik(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping
	List<Cenovnik> getCenovnici(Principal p){
		User u = this.userService.findByUsername(p.getName());	
		return cenovnikRepository.findByUser(u);
	}
	
	@GetMapping("/{id}")
	Cenovnik getCenovnik(@PathVariable("id") Long id,Principal p){
		User u = this.userService.findByUsername(p.getName());	
		
		List<Cenovnik> c=cenovnikRepository.findByUser(u);
		
		for(Cenovnik cc:c) {
			if(cc.getId().equals(id)) {
				return cc;
			}
		}
		
		return null;
		
	}
	

}
