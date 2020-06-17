package com.admin.adminServis.controller;

import com.admin.adminServis.model.User;
import com.admin.adminServis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    protected final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    //@PreAuthorize("@authService.hasProtectedAccess()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> obrisiUsera(@PathVariable Long id){

        if (userService.ukloniUsera(id)) {
            logger.info("Uklonjen je user");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.info("User ne moze da se ukloni, los zahtev");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //@PreAuthorize("@authService.hasProtectedAccess()")
    @GetMapping("/blokirajUsera/{id}")
    public ResponseEntity<User> blokirajUsera(@PathVariable Long id){

        if (userService.blokirajUsera(id)) {
        	logger.info("Blokiran user");
            return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
        } else {
        	logger.info("User nije blokiran");
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

    }

    //@PreAuthorize("@authService.hasProtectedAccess()")
    @GetMapping("/odblokirajUsera/{id}")
    public ResponseEntity<User> odblokirajUsera(@PathVariable Long id){

        if (userService.odblokirajUsera(id)) {
            logger.info("User je odblokiran");
            return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
        } else {
            logger.info("User nije odblokiran");
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

    }


}
