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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.IzvestajDTO;
import com.agentApp.app.dto.OglasDTO;
import com.agentApp.app.dto.ZahtevBundleDTO;
import com.agentApp.app.dto.ZahtevDTO;
import com.agentApp.app.models.Izvestaj;
import com.agentApp.app.models.User;
import com.agentApp.app.models.Zahtev;
import com.agentApp.app.services.IzvestajService;
import com.agentApp.app.services.UserService;
import com.agentApp.app.services.ZahtevService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/izvestaj")
public class IzvestajController {

	protected final static Logger logger = LoggerFactory.getLogger(IzvestajController.class);

	@Autowired
	private ZahtevService zahtevService;
	@Autowired
	private UserService userService;
	@Autowired
	private IzvestajService izvestajService;
	
	@GetMapping
	public Set<ZahtevBundleDTO> izvestaj(Principal p) {
		User u = userService.findByUsername(p.getName());
		
		Set<ZahtevBundleDTO> bundleZahtevi = new HashSet<>();
		Set<ZahtevBundleDTO> bundleZahteviIzvestaj = this.izvestajService.getIzvestajiFromServisi(u.getId());
		
		Set<Long> ids = zahtevService.getAllGroupIDs();
		
		for(Long id : ids) {
			List<Zahtev> zahteviGrouped = this.zahtevService.getAllByAcceptedGroupID(id);
			
			ZahtevBundleDTO zbdto = new ZahtevBundleDTO();
			for(Zahtev z : zahteviGrouped) {
				zbdto.setBundleID(z.getBundle_id());
				
				if(z.getOglas().getVozilo().getUser().getId().equals(u.getId())) {
					Izvestaj i = this.izvestajService.getOneIzvestaj(z.getId(), z.getOglas().getVozilo().getId());
					logger.info("Izvestaj je pronadjen");
					if(i == null) {
						OglasDTO oglasDTO = new OglasDTO(z.getOglas(), z.getOglas().getVozilo());
						zbdto.getBundleZahtevi().add(new ZahtevDTO(z, oglasDTO));
						bundleZahtevi.add(zbdto);
						logger.info("Dodati bundle zahtevi");
					}else {
						IzvestajDTO idto = new IzvestajDTO(i);
						OglasDTO oglasDTO = new OglasDTO(z.getOglas(), z.getOglas().getVozilo());
						zbdto.getBundleZahtevi().add(new ZahtevDTO(z, oglasDTO, idto));
						bundleZahtevi.add(zbdto);
						logger.info("Dodati bundle zahtevi zajedno sa id-jem izvestaja");
					}
				}
			}
		}
		
		for(ZahtevBundleDTO z : bundleZahteviIzvestaj) {
			bundleZahtevi.add(z);
		}
		
		return bundleZahtevi;
	}
	
	@PutMapping
	public ResponseEntity<?> save(@RequestBody IzvestajDTO izvestajDTO){
		System.out.println("Novi izvestaj je: " + izvestajDTO);
		Izvestaj i = null;
		if(!this.zahtevService.isZahtevHere(izvestajDTO.getZahtev())) {
			System.out.println("Zahtev je na servisu");
			this.izvestajService.sendIzvestajNaServis(izvestajDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			i = this.izvestajService.sacuvajIzvestaj(izvestajDTO);
			
			if(i != null) {
				this.izvestajService.ukloniTerminZauzeca(i);
				logger.info("Uklonjen termin zauzeca");
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			logger.info("Izvestaj nije stigao do back-a");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
