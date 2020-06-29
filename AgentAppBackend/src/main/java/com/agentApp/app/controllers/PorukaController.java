package com.agentApp.app.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.IzvestajDTO;
import com.agentApp.app.dto.OglasDTO;
import com.agentApp.app.dto.PorukaDTO;
import com.agentApp.app.services.PorukaService;
import com.agentApp.app.services.UserService;
import com.agentApp.app.services.ZahtevService;
import com.agentApp.app.dto.ZahtevBundleDTO;
import com.agentApp.app.dto.ZahtevDTO;
import com.agentApp.app.models.Izvestaj;
import com.agentApp.app.models.Poruka;
import com.agentApp.app.models.User;
import com.agentApp.app.models.Zahtev;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/poruka")

public class PorukaController {

	@Autowired
	private PorukaService porukaService;
	@Autowired
	private ZahtevService zahtevService;
	@Autowired
	private UserService userService;
	
	protected final static Logger logger = LoggerFactory.getLogger(ZahtevController.class);

	
	@GetMapping(value="/zahtev")
	public Set<ZahtevBundleDTO> poruke(Principal p){
		System.out.println("hocu prihvacene zahteve");
		User u=userService.findByUsername(p.getName());
		
		Set<ZahtevBundleDTO> bundleZahtevi = new HashSet<>();
		
		Set<Long> ids = zahtevService.getAllGroupIDs();
		System.out.println("KORISNIK "+u.getUsername());
		System.out.println(u.getRoles().get(0).getName());
		for(Long id : ids) {
			List<Zahtev> zahteviGrouped = this.zahtevService.getAllByAcceptedGroupID(id);
			System.out.println("hocu prihvacene zahteve FOR");
			ZahtevBundleDTO zbdto = new ZahtevBundleDTO();
			for(Zahtev z : zahteviGrouped) {
				zbdto.setBundleID(z.getBundle_id());
				System.out.println("Pre if-a "+z.getPodnosilac().getUser().getUsername());
				if(u.getRoles().get(0).getName().equals("ROLE_USER")) {
					System.out.println(u.getRoles().get(0));
				if(z.getPodnosilac().getUser().getId().equals(u.getId())) {
					Poruka mess =this.porukaService.findByZahtev(z.getId());
					System.out.println("Usao u if "+ u.getUsername());
					if(mess == null) {
						OglasDTO oglasDTO = new OglasDTO(z.getOglas(), z.getOglas().getVozilo());
						zbdto.getBundleZahtevi().add(new ZahtevDTO(z, oglasDTO));
						bundleZahtevi.add(zbdto);
						logger.info("Dodati bundle zahtevi");
					}	
				}
				}else {
					if(z.getOglas().getVozilo().getUser().getId().equals(u.getId())) {
						Poruka mess =this.porukaService.findByZahtev(z.getId());
						System.out.println("Usao u if "+ u.getUsername());
						if(mess == null) {
							OglasDTO oglasDTO = new OglasDTO(z.getOglas(), z.getOglas().getVozilo());
							zbdto.getBundleZahtevi().add(new ZahtevDTO(z, oglasDTO));
							bundleZahtevi.add(zbdto);
							logger.info("Dodati bundle zahtevi");
						}	
					}
				}
		
			}
			
		}
		return bundleZahtevi;
		
	}
	
	
	
	//Zahtevi oko kojih su razmenjene poruke
	@GetMapping(value="/zahtevi")
	public Set<ZahtevBundleDTO> porukePostoje(Principal p){
		System.out.println("hocu prihvacene zahteve sa porukama");
		User u=userService.findByUsername(p.getName());
		
		Set<ZahtevBundleDTO> bundleZahtevi = new HashSet<>();
		
		Set<Long> ids = zahtevService.getAllGroupIDs();
		System.out.println("KORISNIK "+u.getUsername());

		for(Long id : ids) {
			List<Zahtev> zahteviGrouped = this.zahtevService.getAllByAcceptedGroupID(id);
			
			ZahtevBundleDTO zbdto = new ZahtevBundleDTO();
			for(Zahtev z : zahteviGrouped) {
				zbdto.setBundleID(z.getBundle_id());
				if(u.getRoles().get(0).getName().equals("ROLE_USER")) {
				if(z.getPodnosilac().getUser().getId().equals(u.getId())) {
					Poruka mess =this.porukaService.findByZahtev(z.getId());
					
				//	if(mess != null) {
						OglasDTO oglasDTO = new OglasDTO(z.getOglas(), z.getOglas().getVozilo());
						zbdto.getBundleZahtevi().add(new ZahtevDTO(z, oglasDTO));
						bundleZahtevi.add(zbdto);
						logger.info("Dodati bundle zahtevi");
					//}	
				}
				}else {
					if(z.getOglas().getVozilo().getUser().getId().equals(u.getId())) {
						Poruka mess =this.porukaService.findByZahtev(z.getId());
						
						//if(mess != null) {
							OglasDTO oglasDTO = new OglasDTO(z.getOglas(), z.getOglas().getVozilo());
							zbdto.getBundleZahtevi().add(new ZahtevDTO(z, oglasDTO));
							bundleZahtevi.add(zbdto);
							logger.info("Dodati bundle zahtevi");
						//}	
					}
					}
			}
			
		}
		return bundleZahtevi;
		
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody PorukaDTO porukaDTO,Principal p){
		System.out.println("PORUKA "+porukaDTO.getSadrzaj()+porukaDTO.getBundle() +p.getName());
		if(porukaService.create(porukaDTO,p.getName())) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/{zahtevId}")
	public List<Poruka> sve(@PathVariable Long zahtevId){
		System.out.println("PORUKA "+ zahtevId);
		if(porukaService.findByZahtevList(zahtevId)!=null) {
			return porukaService.findByZahtevList(zahtevId);
		}else {
			return null;
		}
	}
}
