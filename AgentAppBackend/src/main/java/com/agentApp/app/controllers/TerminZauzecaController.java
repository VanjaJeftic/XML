package com.agentApp.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.TerminZauzecaDTO;
import com.agentApp.app.models.TerminZauzeca;
import com.agentApp.app.models.Vozilo;
import com.agentApp.app.services.TerminZauzecaService;
import com.agentApp.app.services.VoziloService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/termin")
public class TerminZauzecaController {

	protected final static Logger logger = LoggerFactory.getLogger(TerminZauzecaController.class);

	@Autowired
	private VoziloService voziloService;
	@Autowired
	private TerminZauzecaService terminService;
	
	@PutMapping
	public ResponseEntity<?> create(@RequestBody TerminZauzecaDTO terminDTO) {
		Vozilo v = this.voziloService.getVozilo(terminDTO.getVozilo_id());
		logger.info("Pronadjeno je vozilo");
		TerminZauzeca newTermin = new TerminZauzeca(terminDTO, v);
		
		boolean ok = this.terminService.zauzmiTermin(newTermin);
		if(ok) {
			logger.info("Termin je zauzet");
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		logger.info("Nije zauzet termin");
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
}
