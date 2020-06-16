package com.agentApp.app.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agentApp.app.models.User;
import com.agentApp.app.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")
public class LoginController {
	protected final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/getUserInfo")
	//@PreAuthorize("hasRole('ROLE_USER')")
	public User loggenInUser(Principal principal) {

		User u = userService.findByUsername(principal.getName());
		logger.info("Pronadjen korisnik po username-u");
		return u;
	}



}
