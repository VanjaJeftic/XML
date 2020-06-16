package com.agentApp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.models.User;
import com.agentApp.app.services.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	protected final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//@GetMapping(value = "/allUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAll(){
		List<User> users = userService.findOnlyUsers("User");
		
		return ( new ResponseEntity<>(users, HttpStatus.OK) );
	}
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.findAll();
		
		return ( new ResponseEntity<>(users, HttpStatus.OK) );
	}
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable ("id") Long id) {
		User user = userService.findById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

	
}
