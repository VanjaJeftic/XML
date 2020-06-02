package com.zahtev.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zahtev.connections.OglasConnection;
import com.zahtev.dto.ShopCartItemsDTO;
import com.zahtev.dto.TerminZauzecaZahtevDTO;
import com.zahtev.dto.ZahtevDTO;
import com.zahtev.model.Zahtev;
import com.zahtev.service.ZahtevService;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping(value = "/zahtev")
public class ZahtevController {
	
	@Autowired
	private OglasConnection oglasConnection;
	@Autowired
	private ZahtevService zahtevService;
	
	@GetMapping("/test")
	public String zahtevi(){
//		List<Zahtev> zahtevi = zahtevService.getAllZahtevi();
//		return zahtevi;
		return "Hello from ZahteviService";
	}
	
	@PostMapping("/zahtev")
	public ResponseEntity<?> create(@RequestBody ShopCartItemsDTO listaZahteva) {

		Set<Long> vlasnici = new HashSet<>();
		Set<ZahtevDTO> forBundle = new HashSet<>();
		Long groupID = zahtevService.getLastGroupID() + 1;
		Long podnosilac = 3L;
		
		
		for(ZahtevDTO z : listaZahteva.getZahtevi()) {
			vlasnici.add(z.getOglas().getVozilo().getUser().getId());
		}

		for(ZahtevDTO z : listaZahteva.getZahtevi()) {
			boolean postoji = oglasConnection.verify(z.getOglas().getId());
			if(postoji) {
				Zahtev newZahtev = new Zahtev(z);
				newZahtev.setPodnosilac_id(podnosilac);
				newZahtev.setStatus("PENDING");
				if(z.isBundle()) {
					forBundle.add(z);
				}else {
					System.out.println("Nije bundle zahtev, id: " + z.getOglas().getId());
					newZahtev.setBundle_id(0L);
					zahtevService.save(newZahtev);
				}
			}
		}
		//BUNDLE Zahtevi
		for(Long vlasnik : vlasnici) {
			for(ZahtevDTO zahtev : forBundle) {
				if(zahtev.getOglas().getVozilo().getUser().getId().equals(vlasnik)) {
					Zahtev newZahtev = new Zahtev(zahtev);
					newZahtev.setBundle_id(groupID);
					newZahtev.setStatus("PENDING");
					newZahtev.setPodnosilac_id(podnosilac);
					zahtevService.save(newZahtev);
				}
			}
			groupID++;
		}
		return new ResponseEntity<>(HttpStatus.OK);
		//STARO
//		List<Zahtev> validno = new ArrayList<>();
//		for(ZahtevDTO zahtevDTO : listaZahteva.getZahtevi()) {
//			boolean postoji = oglasConnection.verify(zahtevDTO.getOglas_id());
//			if(postoji) {
//				Zahtev saved = zahtevService.save(new Zahtev(zahtevDTO));
//				validno.add(saved);
//			}
//		}
//		return validno;
	}
	
	@PostMapping("/zauzece")
	public boolean zauzece(@RequestBody TerminZauzecaZahtevDTO terminZahtev){
		List<Zahtev> zahtevi = this.zahtevService.getAllZahtevi();
		if(zahtevi == null || zahtevi.size() < 1) {
			System.out.println("Broj zahteva: " + zahtevi.size());
			return false;
		}
		for(Long id : terminZahtev.getOglasi()) {
			for(Zahtev z : zahtevi) {
				if(z.getOglas_id() == id) {
					z.setStatus("CANCELED");
					this.zahtevService.save(z);
				}
			}
		}
		return true;
	}
}
