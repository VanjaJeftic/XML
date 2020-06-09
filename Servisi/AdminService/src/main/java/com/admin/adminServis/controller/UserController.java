package com.admin.adminServis.controller;

import com.admin.adminServis.model.User;
import com.admin.adminServis.service.UserService;
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
            return new ResponseEntity<>(HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //@PreAuthorize("@authService.hasProtectedAccess()")
    @GetMapping("/blokirajUsera/{id}")
    public ResponseEntity<User> blokirajUsera(@PathVariable Long id){
    	System.out.println("metoda blokiraj usera u auth");
        if (userService.blokirajUsera(id)) {
        	System.out.println("Odoh u servis da blokiram");
            return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
        } else {
        	System.out.println("Nisam otisao u servis");
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

    }

    //@PreAuthorize("@authService.hasProtectedAccess()")
    @GetMapping("/odblokirajUsera/{id}")
    public ResponseEntity<User> odblokirajUsera(@PathVariable Long id){

        if (userService.odblokirajUsera(id)) {
            return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

    }


}
