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
		Long groupID = zahtevService.getLastGroupID() + 1;
		User u = userService.findByUsername(p.getName());
		Korisnik podnosilac = korisnikService.findOneByUserId(u.getId());
		
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
		return bundleZahtevi;
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> acceptRequest(@PathVariable("id") Long id, Principal user) {
		
		User u = this.userService.findByUsername(user.getName());
		Set<Integer> counter = new HashSet<>();
		List<Zahtev> zahtevi = this.zahtevService.getAllByGroupID(id);
		
		if(zahtevi.get(0).isBundle()) {
			for(Zahtev z : zahtevi) {
				Oglas o = this.oglasService.findOneOglas(z.getOglas().getId());
				TerminZauzeca newTermin = new TerminZauzeca();
				newTermin.setVozilo(o.getVozilo());
				newTermin.setZauzetod(z.getPreuzimanje());
				newTermin.setZauzetdo(z.getPovratak());
				
				logger.info("Pocetak provere ");
				int imaPodudaranja = this.terminService.provjeraZauzetostiVozila(newTermin);
				if(imaPodudaranja == 1) {
					counter.add(1);
				}
			}
			if(!counter.contains(1)) {
				for(Zahtev z : zahtevi) {
					Oglas o = this.oglasService.findOneOglas(z.getOglas().getId());
					TerminZauzeca newTermin = new TerminZauzeca();
					newTermin.setVozilo(o.getVozilo());
					newTermin.setZauzetod(z.getPreuzimanje());
					newTermin.setZauzetdo(z.getPovratak());
					
					if(o.getVozilo().getUser().getId().equals(u.getId())) {
						z.setStatus("ACCEPTED");
						boolean ok = this.terminService.zauzmiBundleTermin(newTermin, z.getBundle_id());
						if(ok) {
							logger.info("Cuvanje zahteva");
							this.zahtevService.saveZahtev(z);
						}else {
							return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
						}
					}else {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
				}
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			Zahtev z = zahtevi.get(0);
			Oglas o = this.oglasService.findOneOglas(z.getOglas().getId());
			TerminZauzeca newTermin = new TerminZauzeca();
			newTermin.setVozilo(o.getVozilo());
			newTermin.setZauzetod(z.getPreuzimanje());
			newTermin.setZauzetdo(z.getPovratak());
			
			logger.info("Otpocinjanje provere");
			int imaPodudaranja = this.terminService.provjeraZauzetostiVozila(newTermin);
			if(imaPodudaranja == 0) {
				if(o.getVozilo().getUser().getId().equals(u.getId())) {
					z.setStatus("ACCEPTED");
					logger.info("Promenjen zahtev na prihvaceno");
					boolean ok = this.terminService.zauzmiTermin(newTermin);
					if(ok) {
						logger.info("Cuvanje zahteva u bazi");
						this.zahtevService.saveZahtev(z);
					}else {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
				}
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
	
}
