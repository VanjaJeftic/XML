package com.agentApp.app.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.ShopCartItemsDTO;
import com.agentApp.app.dto.ZahtevDTO;
import com.agentApp.app.models.Korisnik;
import com.agentApp.app.models.User;
import com.agentApp.app.models.Zahtev;
import com.agentApp.app.services.KorisnikService;
import com.agentApp.app.services.OglasService;
import com.agentApp.app.services.UserService;
import com.agentApp.app.services.ZahtevService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/zahtev")
public class ZahtevController {

	@Autowired
	private ZahtevService zahtevService;
	@Autowired
	private OglasService oglasService;
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private UserService userService;
	
//	@PostMapping()
//	public ResponseEntity<?> create(@RequestBody ZahtevDTO zahtevDTO){
//		Zahtev zahtev = new Zahtev(zahtevDTO);
//		Zahtev z = zahtevService.saveZahtev(zahtev);
//		return new ResponseEntity<Zahtev>(z, HttpStatus.OK);
//	}
	
	@PostMapping
	public void create(@RequestBody ShopCartItemsDTO shopCartItemsDTO, Principal p){
		List<User> vlasnici = userService.findAll();
		List<Zahtev> forBundle = new ArrayList<Zahtev>();
		Long groupID = zahtevService.getLastGroupID();
		User u = userService.findByUsername(p.getName());
		Korisnik podnosilac = korisnikService.findByUserId(u.getId());
		
		for(ZahtevDTO zahtevDTO : shopCartItemsDTO.getZahtevi()) {
			Zahtev z = new Zahtev(zahtevDTO);
			z.setPodnosilac(podnosilac);
			if(zahtevDTO.isBundle()) {
				forBundle.add(z);
			}else {
				z.setBundle_id(0L);
				zahtevService.saveZahtev(z);
			}
		}
//		for(User vlasnik: vlasnici) {
//			for(Zahtev zahtev : forBundle) {
//				if(zahtev.getOglas().getVozilo().getUser().getId().equals(vlasnik.getId())) {
//					zahtev.setBundle_id(groupID);
//					zahtevService.saveZahtev(zahtev);
//				}
//			}
//			groupID++;
//		}
//		for(ZahtevDTO zDTO : shopCartItemsDTO.getZahtevi()) {
//			for(User user : vlasnici) {
//				if(zDTO.getOglas().getVoziloDTO().getUser().getId().equals(user.getId()) && zDTO.isBundle()) {
//					Zahtev newZ = new Zahtev(zDTO);
//					newZ.setBundle_id(groupID);
//					zahtevService.saveZahtev(newZ);
//				}
//			}
//			groupID++;
//		}
	}
}
