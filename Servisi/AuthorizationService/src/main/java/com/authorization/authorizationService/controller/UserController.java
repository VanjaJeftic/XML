package com.authorization.authorizationService.controller;

import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.repository.UserDetailRepository;
import com.authorization.authorizationService.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private UserDetailRepository userRepo;
    private UserDetailServiceImpl userService;

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

}
