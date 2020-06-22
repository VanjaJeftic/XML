package com.zahtev.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zahtev.connections.OglasConnection;
import com.zahtev.dto.IzvestajDTO;
import com.zahtev.dto.OglasDTO;
import com.zahtev.dto.ShopCartItemsDTO;
import com.zahtev.dto.TerminZauzecaDTO;
import com.zahtev.dto.TerminZauzecaZahtevDTO;
import com.zahtev.dto.ZahtevDTO;
import com.zahtev.dto.ZahtevViewDTO;
import com.zahtev.model.Zahtev;
import com.zahtev.service.ZahtevService;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping(value = "/zahtev")
public class ZahtevController {

	protected final static Logger logger = LoggerFactory.getLogger(ZahtevController.class);
	@Autowired
	private OglasConnection oglasConnection;
	@Autowired
	private ZahtevService zahtevService;
	
	@GetMapping("/test")
	public String zahtevi(){
		return "Hello from ZahteviService";
	}
	
	@PostMapping("/zahtev")
	public ResponseEntity<?> create(@RequestBody ShopCartItemsDTO listaZahteva) {

		boolean ok = this.zahtevService.create(listaZahteva);
		if(ok) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//Grupisanje zahteva na frontu
	@GetMapping("/{id}") //ID - ulogovani agent
	public Set<ZahtevViewDTO> sviBundleZahtevi(@PathVariable("id") Long agent){
		
		return this.zahtevService.sviBundleZahtevi(agent);
	}
	
	@PostMapping("/accept/{id}/{agent}")	//Agent - Ulogovani Agent
	public ResponseEntity<?> acceptRequest(@PathVariable("id") Long id, @PathVariable("agent") Long agent){
		boolean ok = this.zahtevService.acceptRequest(id, agent);
		if(ok) {
			return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//Provjera da li se neki PENDING zahtev poklapa sa novim zahtevom i menja status u CANCELED
	@PostMapping("/zauzece")
	public boolean zauzece(@RequestBody TerminZauzecaZahtevDTO terminZahtev){
		return this.zahtevService.zauzece(terminZahtev);
	}
	
	@GetMapping("izvestaj/{id}") //ID - ulogovani agent
	public Set<ZahtevViewDTO> sviBundleZahteviIzvestaj(@PathVariable("id") Long agent){
		
		return this.zahtevService.sviBundleZahteviIzvestaj(agent);
	}
	
	@GetMapping("/zahtev/{id}")
	public ResponseEntity<?> getOneZahtev(@PathVariable("id") Long id){
		ZahtevDTO z = this.zahtevService.getOneZahtev(id);
		if(z == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ZahtevDTO>(z, HttpStatus.OK);
	}

	
	@GetMapping("/zahtevi-podnosioca/{id}")
	public ResponseEntity<?> getZahteviPodnosioca(@PathVariable("id") Long id){
		List<Zahtev> zahtevi = this.zahtevService.getAllZahtevi();
		List<ZahtevDTO> zahteviPodnosioca = new ArrayList<>();
		for(Zahtev z : zahtevi) {
			if(z.getPodnosilac_id().equals(id)) {
				OglasDTO o = oglasConnection.getOneOglas(z.getOglas_id());
				ZahtevDTO zDTO = new ZahtevDTO(z);
				zDTO.setOglas(o);
				zahteviPodnosioca.add(zDTO);
			}
		}
		return new ResponseEntity<List<ZahtevDTO>>(zahteviPodnosioca, HttpStatus.OK);
	}
}
