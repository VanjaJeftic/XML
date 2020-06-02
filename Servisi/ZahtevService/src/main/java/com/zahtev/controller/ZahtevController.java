package com.zahtev.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zahtev.connections.OglasConnection;
import com.zahtev.dto.ShopCartItemsDTO;
import com.zahtev.dto.ZahtevDTO;
import com.zahtev.model.Zahtev;
import com.zahtev.service.ZahtevService;

@RestController
//@RequestMapping(value = "/zahtev")
public class ZahtevController {
	
	@Autowired
	private OglasConnection oglasConnection;
	@Autowired
	private ZahtevService zahtevService;
	
	@PostMapping("/zahtev")
	public void create(@RequestBody ShopCartItemsDTO listaZahteva) {

		Set<Long> vlasnici = new HashSet<>();
		List<ZahtevDTO> forBundle = new ArrayList<>();
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
					newZahtev.setBundle_id(0L);
					zahtevService.save(newZahtev);
				}
			}
		}
		//BUNDLE Zahtevi
		for(ZahtevDTO zahtev : forBundle) {
			for(Long vlasnik : vlasnici) {
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
}
