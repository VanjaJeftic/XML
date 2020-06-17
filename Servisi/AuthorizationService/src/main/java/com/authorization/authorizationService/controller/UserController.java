package com.authorization.authorizationService.controller;

import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.repository.UserDetailRepository;
import com.authorization.authorizationService.service.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/korisnik")
public class UserController {

    @Autowired
    private UserDetailRepository userRepo;
    @Autowired
    private UserDetailServiceImpl userService;

    protected final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserDetailServiceImpl userService){
        this.userService=userService;
    }

    @PostMapping("/noviKorisnik")
    public ResponseEntity<?> noviKorisnik(@RequestBody UserDTO userDTO) {

        User user = this.userService.createUser(userDTO);
        logger.info("Kreiran je novi korisnik");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/izmenaKorisnika")
    public ResponseEntity<?> izmenaKorisnika(@RequestBody UserDTO userDTO) {
        Optional<User> userdata = userRepo.findById(userDTO.getId());
        if(userdata.isPresent()){
            this.userService.update(userDTO);
            logger.info("Izmenjen je korisnik");
            return new ResponseEntity<>("Successful updated user", HttpStatus.OK);
        }else{
            logger.info("Korisnik nije pronadjen");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> brisanjeKorisnika(@PathVariable("id") Long id) {
        try {

            userService.delete(id);
            logger.info("Obrisan je user");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("User ne moze da se obrise");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/blokirajUsera/{id}")
    public ResponseEntity<HttpStatus> blokirajUsera(@PathVariable("id") Long id) {
        try {
            userService.blokirajUsera(id);
            logger.info("Blokiran je user");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Nije uspelo blokiranje usera");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/odblokirajUsera/{id}")
    public ResponseEntity<HttpStatus> odblokirajUsera(@PathVariable("id") Long id) {
        try {
            userService.odblokirajUsera(id);
            logger.info("Odblokiran je user");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("User nije uspeo da bude odblokiran");
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

}
