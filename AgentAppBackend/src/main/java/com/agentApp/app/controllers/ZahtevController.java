package com.agentApp.app.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.ShopCartItemsDTO;
import com.agentApp.app.dto.ZahtevDTO;
import com.agentApp.app.models.Korisnik;
import com.agentApp.app.models.Oglas;
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
	
	@PostMapping
    @PreAuthorize("hasAuthority('create_zahtev')")
	public void create(@RequestBody ShopCartItemsDTO shopCartItemsDTO, Principal p){
		//List<User> vlasnici = userService.findAll();
				List<Long> vlasnici = new ArrayList<>();
				List<Zahtev> forBundle = new ArrayList<Zahtev>();
				Long groupID = zahtevService.getLastGroupID() + 1;
				User u = userService.findByUsername(p.getName());
				Korisnik podnosilac = korisnikService.findOneByUserId(u.getId());
				
				for(ZahtevDTO z : shopCartItemsDTO.getZahtevi()) {
					//System.out.println("Vlasnik oglasa: " + z.getOglas().getVozilo().getUser().getId());
					vlasnici.add(z.getOglas().getVozilo().getUser().getId());
				}
				
				for(ZahtevDTO zahtevDTO : shopCartItemsDTO.getZahtevi()) {
					Zahtev z = new Zahtev(zahtevDTO);
					Oglas o = oglasService.findOneOglas(zahtevDTO.getOglas().getId());
					z.setPodnosilac(podnosilac);
					z.setOglas(o);
					z.setStatus("PENDING");
					if(zahtevDTO.isBundle()) {
						forBundle.add(z);
					}else {
						z.setBundle_id(0L);
						zahtevService.saveZahtev(z);
					}
				}
//				System.out.println("Zahtevi:");
				for(Zahtev zahtev : forBundle) {
					for(Long vlasnik: vlasnici) {
						if(zahtev.getOglas().getVozilo().getUser().getId().equals(vlasnik)) {
							zahtev.setBundle_id(groupID);
							zahtevService.saveZahtev(zahtev);
						}
					}
					groupID++;
				}
	}
}
