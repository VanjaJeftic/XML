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

import java.util.List;
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

    @GetMapping(value = "/korisnik/{idkorisnik}")
    public ResponseEntity<?> pronadjiKorisnika(@PathVariable("idkorisnik") Long id){

        System.out.println("Id korisnika je : "+id);
        User u = userService.findById(id);
        System.out.println("Ime i prezime je "+ u.getIme() +u.getPrezime());
        return new ResponseEntity<>(u,HttpStatus.OK);
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

    @GetMapping("/korisnici")
	public ResponseEntity<List<User>> getAllKorisnici(){
    	
    	System.out.println("Sve korisnike dobavi");
		List<User> users = userService.findUsers();
		
		return ( new ResponseEntity<>(users, HttpStatus.OK) );
	}
    

    @GetMapping("/role/{id}")
    public String getUserRole(@PathVariable("id") Long id){
    	User u=userService.getUser(id);
        return u.getRoles().get(0).getName();

    }
    
}
