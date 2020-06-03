package com.authorization.authorizationService.controller;

import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.repository.OnlineUserRepository;
import com.authorization.authorizationService.repository.UserDetailRepository;
import com.authorization.authorizationService.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.Resource;

import java.util.Optional;

@RestController
public class UserController {

	@Autowired
    private UserDetailRepository userRepo;
	@Autowired
    private UserDetailServiceImpl userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	

	@Autowired
	private OnlineUserRepository onlineUserRepository;
    
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
    
 // ------------------- Create a customer
 	// --------------------------------------------------------
 	@RequestMapping(value = "/customer", method = RequestMethod.POST)
 	public ResponseEntity<Resource<String>> postCustomer(@RequestBody UserDTO customer) {

     	LOGGER.debug("Creating Customer with First Name: {}", customer.getUsername());
 		try {
 			userService.saveUser(customer);
 			Resource<String> userRes = new Resource<String>("SUCCESS");
 			return new ResponseEntity<Resource<String>>(userRes, HttpStatus.OK);
 		} catch (Exception ex) {
 			LOGGER.error(" Customer existing  with samename: " + customer.getEmail());
 			Resource<String> userRes = new Resource<String>("FAIL");
 			return new ResponseEntity<Resource<String>>(userRes, HttpStatus.OK);
 		}
 	}
 	/**
 	 * Retrieve customer information
 	 * @param authentication
 	 * @return
 	 */

 	@RequestMapping(value = "/customer", method = RequestMethod.GET)
 	public ResponseEntity<Resource<User>> retreiveUserFromCredential(Authentication authentication) {
 	//public ResponseEntity<Resource<User>> validateCredential(Authentication authentication) {

 		LOGGER.debug("Retriving customer information for  " +authentication.getPrincipal().toString());
 		User user = userService.findCustomer(authentication.getPrincipal().toString());
 		Resource<User> userRes = new Resource<User>(user);
 		return new ResponseEntity<Resource<User>>(userRes,HttpStatus.OK);
 	}
    

}
