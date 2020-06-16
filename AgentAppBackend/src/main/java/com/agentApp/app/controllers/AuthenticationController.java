package com.agentApp.app.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.models.User;
import com.agentApp.app.models.UserTokenState;
import com.agentApp.app.services.UserService;

@RestController
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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/aktivirajNalog", method = RequestMethod.POST)
	public ResponseEntity<?> aktivirajNalog(@RequestBody Long id){
		
		
		User u = userService.findById(id);
		
		u.setNalogAktiviran(true);
		
		User updated = userService.updateUser(u);
		logger.info("User je uspesno azuriran");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
