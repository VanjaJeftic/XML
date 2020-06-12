package com.zahtev.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.zahtev.dto.OglasDTO;
import com.zahtev.dto.ShopCartItemsDTO;
import com.zahtev.dto.TerminZauzecaZahtevDTO;
import com.zahtev.dto.ZahtevDTO;
import com.zahtev.dto.ZahtevViewDTO;
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
		return "Hello from ZahteviService";
	}
	
	@PostMapping("/zahtev")
	public ResponseEntity<?> create(@RequestBody ShopCartItemsDTO listaZahteva) {

		Set<Long> vlasnici = new HashSet<>();
		Set<ZahtevDTO> forBundle = new HashSet<>();
		Long groupID = zahtevService.getLastGroupID() + 1;
		Long podnosilac = listaZahteva.getPodnosilac();
		
		
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
					newZahtev.setBundle_id(groupID);
					zahtevService.save(newZahtev);
				}
			}
			groupID++;
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
	}
	
	//Grupisanje zahteva na frontu
	@GetMapping("/{id}") //ID - ulogovani agent
	public Set<ZahtevViewDTO> sviBundleZahtevi(@PathVariable("id") Long agent){
		
		Set<ZahtevViewDTO> bundleZahtevi = new HashSet<>();
		
		Set<Long> ids = zahtevService.getAllGroupIDs();
		
		//Grupise sve bundle zahteve
		for(Long id : ids) {
			List<Zahtev> zahteviGrouped = zahtevService.getAllByGroupID(id);
			
			ZahtevViewDTO zvdto = new ZahtevViewDTO();
			for(Zahtev z : zahteviGrouped) {
				zvdto.setBundleID(z.getBundle_id());
				OglasDTO oglas = this.oglasConnection.getOneOglas(z.getOglas_id());
				
				if(oglas.getVozilo().getUser().getId().equals(agent)) {
					zvdto.getBundleZahtevi().add(new ZahtevDTO(z, oglas));
					bundleZahtevi.add(zvdto);
				}
			}
		}
		return bundleZahtevi;
	}
	
	@PostMapping("/accept/{id}/{agent}")	//Agent - Ulogovani Agent
	public ResponseEntity<?> acceptRequest(@PathVariable("id") Long id, @PathVariable("agent") Long agent){
		List<Zahtev> zahtevi = this.zahtevService.getAllByGroupID(id);
		
		for(Zahtev z : zahtevi) {
			//Pronalazi sve zahteve koji su kreirani za oglas kome je AgentID "agent" i menja status
			OglasDTO oglas = this.oglasConnection.getOneOglas(z.getOglas_id());
			if(oglas.getVozilo().getUser().getId().equals(agent)) {
				z.setStatus("ACCEPTED");
				this.zahtevService.save(z);
				//Odbija sve ostale zahteve vezane za taj oglas ovog agenta
				this.zahtevService.odbijOstaleZahteve(z.getPreuzimanje(), z.getPovratak(), z.getOglas_id());
			}
		}
		
		
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/zauzece")
	public boolean zauzece(@RequestBody TerminZauzecaZahtevDTO terminZahtev){
		List<Zahtev> zahtevi = this.zahtevService.getAllZahtevi();
		if(zahtevi == null || zahtevi.size() < 1) {
			System.out.println("Broj zahteva: " + zahtevi.size());
			return false;
		}
		
		LocalDateTime preuzimanje = LocalDateTime.of(terminZahtev.getPreuzimanje().getYear(), terminZahtev.getPreuzimanje().getMonthValue(), terminZahtev.getPreuzimanje().getDayOfMonth(), terminZahtev.getPreuzimanje().getHour(), terminZahtev.getPreuzimanje().getMinute());
		LocalDateTime povratak = LocalDateTime.of(terminZahtev.getPovratak().getYear(), terminZahtev.getPovratak().getMonthValue(), terminZahtev.getPovratak().getDayOfMonth(), terminZahtev.getPovratak().getHour(), terminZahtev.getPovratak().getMinute());
		
		for(Long id : terminZahtev.getOglasi()) {
			for(Zahtev z : zahtevi) {
				if(z.getOglas_id() == id && (z.getStatus().equals("PENDING")) ) {
					LocalDateTime zahtevPreuzimanje = LocalDateTime.of(z.getPreuzimanje().getYear(), z.getPreuzimanje().getMonthValue(), z.getPreuzimanje().getDayOfMonth(), z.getPreuzimanje().getHour(), z.getPreuzimanje().getMinute());
					LocalDateTime zahtevPovratak = LocalDateTime.of(z.getPovratak().getYear(), z.getPovratak().getMonthValue(), z.getPovratak().getDayOfMonth(), z.getPovratak().getHour(), z.getPovratak().getMinute());
					
					
					if( (preuzimanje.isAfter(zahtevPreuzimanje) && povratak.isBefore(zahtevPovratak)
							|| (preuzimanje.isBefore(zahtevPreuzimanje) && povratak.isAfter(zahtevPreuzimanje))) 
							|| (preuzimanje.isBefore(zahtevPovratak) && povratak.isAfter(zahtevPovratak))
							|| (preuzimanje.isBefore(zahtevPreuzimanje) && povratak.isAfter(zahtevPovratak)) ) {
						System.out.println("Usao u izmenu statusa!");
						z.setStatus("CANCELED");
						this.zahtevService.save(z);
					}
				}
			}
		}
		return true;
	}
}
