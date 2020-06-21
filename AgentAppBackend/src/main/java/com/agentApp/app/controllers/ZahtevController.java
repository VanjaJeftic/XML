package com.agentApp.app.controllers;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.OglasDTO;
import com.agentApp.app.dto.ShopCartItemsDTO;
import com.agentApp.app.dto.ZahtevBundleDTO;
import com.agentApp.app.dto.ZahtevDTO;
import com.agentApp.app.models.Korisnik;
import com.agentApp.app.models.Oglas;
import com.agentApp.app.models.TerminZauzeca;
import com.agentApp.app.models.User;
import com.agentApp.app.models.Zahtev;
import com.agentApp.app.services.KorisnikService;
import com.agentApp.app.services.OglasService;
import com.agentApp.app.services.TerminZauzecaService;
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
	@Autowired
	private TerminZauzecaService terminService;

	protected final static Logger logger = LoggerFactory.getLogger(ZahtevController.class);

	@PutMapping
	public void create(@RequestBody ShopCartItemsDTO shopCartItemsDTO, Principal p){
		List<Long> vlasnici = new ArrayList<>();
		List<Zahtev> forBundle = new ArrayList<Zahtev>();
		User u = userService.findByUsername(p.getName());
		Korisnik podnosilac = korisnikService.findOneByUserId(u.getId());
		Long groupID = zahtevService.getLastGroupID(u.getId());
		System.out.println("********************************************************");
		System.out.println("Poslednji bundleID je: " + groupID);
		System.out.println("********************************************************");
		
		for(ZahtevDTO z : shopCartItemsDTO.getZahtevi()) {
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
				z.setBundle_id(groupID);
				zahtevService.saveZahtev(z);
			}
			groupID++;
		}
		//Bundle zahtevi
		for(Zahtev zahtev : forBundle) {
			for(Long vlasnik: vlasnici) {
				if(zahtev.getOglas().getVozilo().getUser().getId().equals(vlasnik)) {
					zahtev.setBundle_id(groupID);
					zahtev.setPodnosilac(podnosilac);
					zahtev.setStatus("PENDING");
					zahtevService.saveZahtev(zahtev);
				}
			}
			groupID++;
		}
	}
	
	//Dobavljanje svih zahteva i kao bundle
	@GetMapping
	public Set<ZahtevBundleDTO> sviBundleZahtevi(Principal agent) {
		
		User u = this.userService.findByUsername(agent.getName());
		
		Set<ZahtevBundleDTO> bundleZahtevi = new HashSet<>();
		Set<ZahtevBundleDTO> bundleZahteviServisi = this.zahtevService.getZahteviFromServisi(u.getId());
		
		Set<Long> ids = this.zahtevService.getAllGroupIDs();
		
		//Grupise sve bundle zahteve
		for(Long id : ids) {
			List<Zahtev> zahteviGrouped = this.zahtevService.getAllByGroupID(id);
		
			ZahtevBundleDTO zbdto = new ZahtevBundleDTO();
			for(Zahtev z : zahteviGrouped) {
				zbdto.setBundleID(z.getBundle_id());
				OglasDTO oglasDTO = this.oglasService.getOneOglasDTO(z.getOglas().getId());
				
				if(oglasDTO.getVozilo().getUser().getId().equals(u.getId())) {
					zbdto.getBundleZahtevi().add(new ZahtevDTO(z, oglasDTO));
					bundleZahtevi.add(zbdto);
				}
			}
		}
		for(ZahtevBundleDTO zb : bundleZahteviServisi) {
			bundleZahtevi.add(zb);
		}
		return bundleZahtevi;
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> acceptRequest(@PathVariable("id") Long id, Principal user) {
		
		User u = this.userService.findByUsername(user.getName());
		
		boolean ok = this.zahtevService.acceptRequest(id, u);
		if(ok) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	
	
}
