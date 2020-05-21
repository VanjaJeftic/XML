package com.oglas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OglasController {

	@GetMapping("/hello-worlds")
	public String helloWorld() {
		return "Hello World ";
	}
}
