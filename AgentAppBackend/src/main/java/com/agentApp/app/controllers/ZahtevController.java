package com.agentApp.app.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.ShopCartItemDTO;
import com.agentApp.app.models.Korisnik;
import com.agentApp.app.models.Oglas;
import com.agentApp.app.models.User;
import com.agentApp.app.models.Zahtev;
import com.agentApp.app.services.KorisnikService;
import com.agentApp.app.services.OglasService;
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
	
	@GetMapping
	public List<Zahtev> getAllZahtevi(){
		
		Oglas o = oglasService.findOneOglas(1L);
		Korisnik k = korisnikService.findOneKorisnik(1L);
		Long groupID = zahtevService.getLastGroupID() + 1;
		
		Zahtev z = new Zahtev();
		z.setOglas(o);
		z.setBundle(false);
		z.setPodnosilac(k);
		Zahtev z2 = new Zahtev();
		z2.setOglas(o);
		z2.setBundle(true);
		z2.setPodnosilac(k);
		Zahtev z3 = new Zahtev();
		z3.setOglas(o);
		z3.setBundle(true);
		z3.setPodnosilac(k);
		
		ShopCartItemDTO cart = new ShopCartItemDTO();
		cart.getZahtevi().add(z);
		cart.getZahtevi().add(z2);
		cart.getZahtevi().add(z3);
		
		Set<User> vlasnici = new HashSet<>();
		Set<Zahtev> forBundle = new HashSet<Zahtev>();
		
		for(Zahtev zahtev : cart.getZahtevi()) {
			vlasnici.add(zahtev.getOglas().getVozilo().getUser());
			if(zahtev.isBundle()) {
				forBundle.add(zahtev);
			}else {
				zahtev.setBundle_id(0L);
				zahtevService.saveZahtev(zahtev);
			}
		}
		for(User vlasnik : vlasnici) {
			for(Zahtev zahtev : forBundle) {
				if(zahtev.getOglas().getVozilo().getUser().equals(vlasnik)) {
					zahtev.setBundle_id(groupID);
					zahtevService.saveZahtev(zahtev);
				}
			}
			groupID++;
		}
		
		
//		for(Zahtev zahtev : cart.getZahtevi()) {
//			
//			for(User user : vlasnici) {
//				if(zahtev.getOglas().getVozilo().getUser().equals(user) && zahtev.isBundle()) {
//					z.setBundle_id(groupID);
//					zahtevService.saveZahtev(z);
//				}
//			}
//		}
		
		//zahtevService.saveZahtev(z);
		return zahtevService.getAllZahtevi();
	}

}
