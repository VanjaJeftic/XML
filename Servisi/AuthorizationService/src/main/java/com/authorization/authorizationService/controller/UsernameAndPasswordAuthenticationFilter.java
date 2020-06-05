package com.authorization.authorizationService.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.authorizationService.config.JwtConfig;
import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.model.Logovan;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.model.UserCredentials;
import com.authorization.authorizationService.repository.UserDetailRepository;
import com.authorization.authorizationService.service.UserDetailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Controller
public class UsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	@Autowired
	UserDetailServiceImpl userService;
	
	@Autowired
	UserDetailRepository userRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtConfig jwtConfig;
	
	
	
	
	public UsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		// po default-u, UsernamePasswordAuthenticationFilter prima zahtev po "/login" putanji,
		// pa se, u slucaju drugacije putanje, mora override-ovati
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/signin", "POST"));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Logovan> prijava(@RequestBody UserDTO dto){
		//String[] kredencijali = dto.split(" ");
		//LoggedUser user = signin(kredencijali[0], kredencijali[1]);
		//return user.getUsername() + " " + user.getToken();
		return new ResponseEntity<Logovan>(signin(dto.getUsername(), dto.getPassword()), HttpStatus.OK);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			
			// Uzimanje kredencijala iz zahteva
			UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			
			// Kreiranje auth objekta, koji sadrzi kredencijale i koji ce authManager koristiti za validaciju
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), Collections.emptyList());
			return authManager.authenticate(authToken);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Metoda, koja u slucaju uspesne autentifikacije, generise token
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject(auth.getName())
			.claim("authorities", auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
			.compact();

		// postavljanje tokena u header odgovora 
		response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
	}
	
	
	@PostMapping("/registerKorisnik")
	public ResponseEntity<User> saveKorisnik(@RequestBody UserDTO korisnik){
		return new ResponseEntity<User>(userRepo.save(korisnik), HttpStatus.OK);
	}
	
	
	
	private Logovan signin(String username, String password) {
		try {
			Logovan loggedUser = new Logovan();
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
			System.out.println("za proveru:" + authToken + "kraj" +authToken.getName()+authToken.getCredentials());
			Authentication authentication = authManager.authenticate(authToken);
			System.out.println("Dolazak do security context");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			Long now = System.currentTimeMillis();
			String token = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("authorities", authentication.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
				.compact();
			System.out.println("EVO ME");
			loggedUser.setToken(token);
			loggedUser.setUsername(username);
			if(!userService.loadUserByUsername(username).isEnabled())
				return null;
			return loggedUser;
		}catch (Exception e) {
			System.out.println("neuspesna autentifikacija");
			return null;
		}
		
	}
	
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	    super.setAuthenticationManager(authenticationManager);
	}
}