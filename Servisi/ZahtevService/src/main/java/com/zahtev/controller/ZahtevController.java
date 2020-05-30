package com.zahtev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<Zahtev> create(@RequestBody ShopCartItemsDTO listaZahteva) {

		List<Zahtev> validno = new ArrayList<>();
		for(ZahtevDTO zahtevDTO : listaZahteva.getZahtevi()) {
			boolean postoji = oglasConnection.verify(zahtevDTO.getOglas_id());
			if(postoji) {
				Zahtev saved = zahtevService.save(new Zahtev(zahtevDTO));
				validno.add(saved);
			}
		}
		return validno;
		
	}
}
