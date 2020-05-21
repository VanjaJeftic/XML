package com.zahtev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zahtev.oglasClient.OglasClient;

@RestController
public class ZahtevController {
	
	@Autowired
	private OglasClient oglas;

	@GetMapping("/hello")
	public String callService() {
		return oglas.helloWorld();
	}
}
