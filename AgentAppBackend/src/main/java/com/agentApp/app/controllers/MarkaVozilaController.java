package com.agentApp.app.controllers;
import com.agentApp.app.dto.MarkaVozilaDTO;
import com.agentApp.app.models.MarkaVozila;
import com.agentApp.app.repository.MarkaVozilaRepository;
import com.agentApp.app.services.MarkaVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/marka")
public class MarkaVozilaController {

    @Autowired
    private MarkaVozilaService markaVozilaService;
    @Autowired
    private MarkaVozilaRepository markaVozilaRepository;

    @Autowired
    public MarkaVozilaController(MarkaVozilaService markaVozilaService){
        this.markaVozilaService=markaVozilaService;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> sacuvajMarkuVozila(@RequestBody MarkaVozilaDTO markaVozilaDTO) {
        System.out.println("Nova marka kreiranje");
        MarkaVozila markaVozila = this.markaVozilaService.createMarkaVozila(markaVozilaDTO);

        return new ResponseEntity<>(markaVozila, HttpStatus.OK);
    }


    @PutMapping
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaMarka(@RequestBody MarkaVozilaDTO markaVozilaDTO) {
        System.out.println("Izmena marke controller  "+ markaVozilaDTO.getNaziv());
        Optional<MarkaVozila> markaVoziladata = markaVozilaRepository.findById(markaVozilaDTO.getId());
        if(markaVoziladata.isPresent()){
            this.markaVozilaService.updateMarkaVozila(markaVozilaDTO);
            return new ResponseEntity<>("Successful updated marka vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeMarke(@PathVariable("id") Long id) {
        System.out.println("brisanje marke controller id marke je   "+ id);
        try {
            markaVozilaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    List<MarkaVozila> sveMarke() {
        System.out.println("marke");
        return markaVozilaRepository.findAll();
    }




}
