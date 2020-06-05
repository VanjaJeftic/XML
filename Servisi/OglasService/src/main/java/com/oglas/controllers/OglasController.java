
package com.oglas.controllers;

import com.oglas.dto.OglasDTO;
import com.oglas.dto.OglasViewDTO;
import com.oglas.dto.OglasVoziloDTO;
import com.oglas.dto.UserViewDTO;
import com.oglas.dto.VoziloDTO;
import com.oglas.dto.VoziloViewDTO;
import com.oglas.model.Oglas;
import com.oglas.model.Vozilo;
import com.oglas.repository.OglasRepository;
import com.oglas.repository.VoziloRepository;
import com.oglas.service.OglasService;
import com.oglas.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

public class OglasController {

	@GetMapping("/hello-worlds")
	public String helloWorld() {
		return "Hello World ";
	}

	private OglasService oglasService;
	private VoziloService voziloService;
	@Autowired
	private OglasRepository oglasRepository;
	@Autowired
	private VoziloRepository voziloRepository;

	@Autowired
	public OglasController(OglasService oglasService,VoziloService voziloService){
		this.oglasService=oglasService;
		this.voziloService=voziloService;
	}
	
	@GetMapping
	public List<Oglas> allOglasi(){
		List<Oglas> oglasi = this.oglasService.allOglasi();
		return oglasi;
	}
	
	@GetMapping("/{id}")
	public OglasViewDTO getOneOglas(@PathVariable("id") Long id ) {
		//UserViewDTO user = oglasService.getUser(3L);
		UserViewDTO user = new UserViewDTO();
		user.setFirstname("Goran");
		Oglas o = oglasService.getOneOglas(id);
		user.setId(o.getUser_id());
		Vozilo v = voziloService.getVozilo(o.getVozilo_id());
		VoziloViewDTO vozilo = new VoziloViewDTO(v);
		vozilo.setUser(user);
		OglasViewDTO oglas = new OglasViewDTO(o, vozilo);
		return oglas;
	}
	

	@PostMapping("/create")
	//@PreAuthorize("hasAuthority('create_oglas')")
	public ResponseEntity<?> create(@RequestBody OglasDTO ovDTO) {

		System.out.println("Usao"+ovDTO.getMesto()+ovDTO.getCena()+ovDTO.getPopust());
		Oglas oglas = this.oglasService.createOrder(ovDTO);

		return new ResponseEntity<>(oglas, HttpStatus.OK);
	}

	@PutMapping("/update")
	//@PreAuthorize("hasAuthority('update_oglas')")
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
	//@PreAuthorize("hasRole('ROLE_operator')")
	//@PreAuthorize("hasAuthority('delete_oglas')")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		try {
			oglasService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/verify/{oglas_id}")
	public boolean verify(@PathVariable("oglas_id") Long oglas_id){
		return oglasService.verify(oglas_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}


}
