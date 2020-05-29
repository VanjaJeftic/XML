package com.oglas.controllers;

import com.oglas.dto.OglasDTO;
import com.oglas.dto.OglasVoziloDTO;
import com.oglas.dto.VoziloDTO;
import com.oglas.model.Oglas;
import com.oglas.model.Vozilo;
import com.oglas.repository.OglasRepository;
import com.oglas.repository.VoziloRepository;
import com.oglas.service.OglasService;
import com.oglas.service.VoziloService;
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
	private VoziloService voziloService;
	private OglasRepository oglasRepository;
	private VoziloRepository voziloRepository;

	@Autowired
	public OglasController(OglasService oglasService,VoziloService voziloService){
		this.oglasService=oglasService;
		this.voziloService=voziloService;
	}

	@PostMapping("/create")
	//@PreAuthorize("hasAuthority('create_oglas')")
	public ResponseEntity<?> create(@RequestBody OglasVoziloDTO ovDTO) {

		VoziloDTO vDTO=new VoziloDTO();

		vDTO.setMarkaVozila(ovDTO.getMarkaVozila());
		vDTO.setModelVozila(ovDTO.getModelVozila());
		vDTO.setKlasaVozila(ovDTO.getKlasaVozila());
		vDTO.setTipgoriva(ovDTO.getTipgoriva());
		vDTO.setVrstamenjaca(ovDTO.getVrstamenjaca());
		vDTO.setPredjeniKm(ovDTO.getPredjeniKm());
		vDTO.setBrSedistaDeca(ovDTO.getBrSedistaDeca());
		vDTO.setCdw(ovDTO.isCdw());
		vDTO.setUser_id(vDTO.getUser_id());

		Vozilo vozilo=this.voziloService.createVozilo(vDTO);

		OglasDTO oDTO=new OglasDTO();

		oDTO.setUser_id(ovDTO.getUser_id());
		oDTO.setVozilo_id(vozilo.getId());
		oDTO.setMesto(ovDTO.getMesto());
		oDTO.setCena(ovDTO.getCena());
		oDTO.setPopust(ovDTO.getPopust());
		oDTO.setCenaspopust(ovDTO.getCenaspopust());

		Oglas oglas = this.oglasService.createOrder(oDTO);

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
