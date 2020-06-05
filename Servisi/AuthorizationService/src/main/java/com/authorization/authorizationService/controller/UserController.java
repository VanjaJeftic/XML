package com.authorization.authorizationService.controller;

import com.authorization.authorizationService.dto.JwtAuthenticationResponse;
import com.authorization.authorizationService.dto.ProfileDto;
import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.model.Role;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.model.UserTokenState;
import com.authorization.authorizationService.repository.RoleRepository;
import com.authorization.authorizationService.repository.UserDetailRepository;
import com.authorization.authorizationService.security.TokenUtils;
import com.authorization.authorizationService.security.auth.JwtTokenProvider;
import com.authorization.authorizationService.security.auth.LoginRequest;
import com.authorization.authorizationService.service.UserDetailServiceImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class UserController {

	@Autowired
    private UserDetailRepository userRepo;
    @Autowired
	private UserDetailServiceImpl userService;
    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
	TokenUtils tokenUtilis;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired 
	JwtTokenProvider jwtProvider;
	
	@Autowired 
	HttpSession session;
	
	@Autowired
	private AuthenticationManager authenticationManager;
    
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    public UserController(UserDetailServiceImpl userService){
        this.userService=userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        User user = this.userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        Optional<User> userdata = userRepo.findById(userDTO.getId());
        if(userdata.isPresent()){
            this.userService.update(userDTO);
            return new ResponseEntity<>("Successful updated user", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
//=======================mozda treba Get umesto Put ??????
    @GetMapping("/blokirajUsera/{id}")
    public ResponseEntity<HttpStatus> blokirajUsera(@PathVariable("id") Long id) {
        try {
            userService.blokirajUsera(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/odblokirajUsera/{id}")
    public ResponseEntity<HttpStatus> odblokirajUsera(@PathVariable("id") Long id) {
        try {
            userService.odblokirajUsera(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/verify/{id_usera}")
    public boolean verify(@PathVariable("id_usera") Long id_usera){
        return userService.verify(id_usera);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
//**************************************************************************************
    //Dobavljanje podataka o Useru
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
    	return userService.getUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin( @RequestBody LoginRequest loginRequest) {
    	InetAddress localhost = null;
		try {
			System.out.println("Localhost"+InetAddress.getLocalHost());
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("Exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Ip");
		String ip = (localhost.getHostAddress()).trim();
    
		System.out.println("Token"+loginRequest.getEmail()+
                loginRequest.getPassword());
    		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	                loginRequest.getEmail(),
	                loginRequest.getPassword()
	         );
    		
    		System.out.println("Auth");
	         
    		Authentication authentication = authenticationManager.authenticate(
	            token
	        );
    		
    		System.out.println("Email");
    		String email = authentication.getName();
    		List<String> authorities = authentication.getAuthorities().stream()
    				.map(GrantedAuthority::getAuthority)
    				.collect(Collectors.toList());
    		System.out.println("Jwt");
    		String jwt = jwtProvider.generateToken(authentication);
    		ProfileDto profile = new ProfileDto(email, authorities, true);
    		User us = userRepo.findByEmail(loginRequest.getEmail());
    		logger.info("ID: {} | PRN4SI | success", us.getId() );
	        return ResponseEntity.ok(new JwtAuthenticationResponse(profile, jwt));
		
    }
}


