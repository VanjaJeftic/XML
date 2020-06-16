package com.oglas.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import com.oglas.dto.TerminZauzecaDTO;
import com.oglas.dto.TerminZauzecaZahtevDTO;
import com.oglas.model.Oglas;
import com.oglas.model.TerminZauzeca;
import com.oglas.model.Vozilo;
import com.oglas.repository.TerminZauzecaRepository;
import com.oglas.service.OglasService;
import com.oglas.service.TerminZauzecaService;
import com.oglas.service.VoziloService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/termin")
public class TerminZauzecaController {

	protected final static Logger logger = LoggerFactory.getLogger(TerminZauzecaController.class);

	@Autowired
	private TerminZauzecaRepository terminRepository;
	@Autowired
	private TerminZauzecaService terminServis;
	
	@Autowired
	private VoziloService voziloServis;
	@Autowired
	private OglasService oglasServis;
	
	@Autowired
	public TerminZauzecaController(TerminZauzecaRepository terminRepository, TerminZauzecaService terminServis) {
		super();
		this.terminRepository = terminRepository;
		this.terminServis = terminServis;
	}

	@PostMapping
	public ResponseEntity<?> zauzece(@RequestBody TerminZauzecaDTO termin){
		

		
		TerminZauzecaZahtevDTO tzz = new TerminZauzecaZahtevDTO();
		tzz.setPreuzimanje(termin.getZauzetod());
		tzz.setPovratak(termin.getZauzetdo());
		Vozilo vozilo = voziloServis.getVozilo(termin.getVozilo_id());
		
		int imaPodudaranja = this.terminServis.provjeraZauzetostiVozila(termin);
		if(imaPodudaranja == 0) {													//NEMA PODUDARANJA
			
			List<Oglas> oglasiZaVozilo = oglasServis.findOglasiByVoziloID(vozilo.getId());
			for(Oglas o : oglasiZaVozilo) {
				tzz.getOglasi().add(o.getId());
			}
			
			//Provjerava sa prihvacenim zahtevima da li moze uopste zauzeti tada vozilo, moguce je da je vec izdato nekom korisniku
			boolean ok = this.terminServis.zauzece(tzz);
			if(ok) {
				terminServis.createTermin(termin, vozilo);
				return new ResponseEntity<>(HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//Provjera da li se neki zahtev moze prihvatiti, zbog zauzetosti vozila za taj termin
	@PostMapping("/zauzece")	
	public int provjeraZauzetostiVozila(@RequestBody TerminZauzecaDTO terminZauzimanjaDTO) {
		
		return this.terminServis.provjeraZauzetostiVozila(terminZauzimanjaDTO);
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
			logger.info("Izmenjen je termin");
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
			logger.info("Termin je obrisan");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
