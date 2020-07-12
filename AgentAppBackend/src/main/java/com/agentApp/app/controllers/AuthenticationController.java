package com.agentApp.app.controllers;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.agentApp.app.dto.ModelVozilaDTO;
import com.agentApp.app.models.ModelVozila;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.agentApp.app.auth.JwtAuthenticationRequest;
import com.agentApp.app.dto.UserDTO;
import com.agentApp.app.services.EmailService;
import com.agentApp.app.tokenUtils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.agentApp.app.models.User;
import com.agentApp.app.models.UserTokenState;
import com.agentApp.app.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {


	@Autowired
	private EmailService emailService;

	protected final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationController(AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
	}
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {
		
		final Authentication authentication;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			logger.info("Token created");
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			logger.info("Nalog ne postoji");
			return new ResponseEntity<>("Nalog ne postoji", HttpStatus.BAD_REQUEST);
		}
		
		if( !((User)authentication.getPrincipal()).isNalogAktiviran() ) {
			logger.info("Nalog nije aktivan");
			return new ResponseEntity<>("Nalog nije aktiviran", HttpStatus.LOCKED);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> doRegister(@RequestBody UserDTO dto){
		User u = userService.saveUser(dto);

		if( u == null ) {
			logger.info("User sa tim kredencijalima ne postoji u bazi, stoga se korisnik moze registrovati");
			return new ResponseEntity<>(u, HttpStatus.OK);
		}

		try {
			logger.info("Mail za potvrdu registracije poslat");
			emailService.sendNotificaitionZaRegistraciju(u);
		} catch (Exception e) {
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}





	//Menjanje sifre
	@PostMapping(value = "/izmenaLozinke")
	public ResponseEntity<?> updateSifruuser(@RequestParam("username") String username,
											 @RequestParam("password") String password){

		System.out.println("User username Je : "+username + "Sifra je "+ password);

		User user = userService.findByUsername(username);

		if(user == null) {
			logger.info("Nije pronadjen user");
			return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
		}
		logger.info("Menja se sifra");
		user=userService.promeniSifru(user,password);

		return new ResponseEntity<>(user, HttpStatus.OK);

	}



	@RequestMapping(value = "/aktivirajNalog", method = RequestMethod.POST)
	public ResponseEntity<?> aktivirajNalog(@RequestBody Long id){
		
		
		User u = userService.findById(id);
		
		u.setNalogAktiviran(true);
		
		User updated = userService.updateUser(u);
		logger.info("User je uspesno azuriran");
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@GetMapping(value = "/korisnik/{idkorisnik}")
	public ResponseEntity<?> pronadjiKorisnika(@PathVariable("idkorisnik") Long id){

		System.out.println("Id korisnika je : "+id);
		User u = userService.findById(id);
		return new ResponseEntity<>(u,HttpStatus.OK);
	}


}
