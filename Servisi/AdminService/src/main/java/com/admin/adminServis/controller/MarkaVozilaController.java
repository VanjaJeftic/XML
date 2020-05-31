package com.admin.adminServis.controller;


import com.admin.adminServis.dto.MarkaVozilaDTO;
import com.admin.adminServis.model.MarkaVozila;
import com.admin.adminServis.repository.MarkaVozilaRepository;
import com.admin.adminServis.service.MarkaVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MarkaVozilaController {

    @GetMapping("/hello-worlds")
    public String helloWorld() {
        return "Hello World ";
    }

    private MarkaVozilaService markaVozilaService;
    private MarkaVozilaRepository markaVozilaRepository;

    @Autowired
    public MarkaVozilaController(MarkaVozilaService markaVozilaService){
        this.markaVozilaService=markaVozilaService;
    }

    @PostMapping("/createMarka")
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> createMarka(@RequestBody MarkaVozilaDTO markaVozilaDTO) {

        MarkaVozila markaVozila = this.markaVozilaService.createMarkaVozila(markaVozilaDTO);

        return new ResponseEntity<>(markaVozila, HttpStatus.OK);
    }

    @PutMapping("/updateMarka")
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> updateMarka(@RequestBody MarkaVozilaDTO markaVozilaDTO) {
        Optional<MarkaVozila> markaVoziladata = markaVozilaRepository.findById(markaVozilaDTO.getId());
        if(markaVoziladata.isPresent()){
            this.markaVozilaService.updateMarkaVozila(markaVozilaDTO);
            return new ResponseEntity<>("Successful updated marka vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteMarka/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> deleteMarka(@PathVariable("id") Long id) {
        try {
            markaVozilaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/verify/{markavozila_id}")
    public boolean verify(@PathVariable("markavozila_id") Long markavozila_id){
        return markaVozilaService.verify(markavozila_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
