package com.agentApp.app.controllers;

import com.agentApp.app.dto.KlasaVozilaDTO;
import com.agentApp.app.models.KlasaVozila;
import com.agentApp.app.repository.KlasaVozilaRepository;
import com.agentApp.app.services.KlasaVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/klasa")
public class KlasaVozilaController {

    @Autowired
    private KlasaVozilaService klasaVozilaService;
    @Autowired
    private KlasaVozilaRepository klasaVozilaRepository;

    @Autowired
    public KlasaVozilaController(KlasaVozilaService klasaVozilaService){
        this.klasaVozilaService=klasaVozilaService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_sifrarnik')")
    public ResponseEntity<?> kreirajKlasu(@RequestBody KlasaVozilaDTO klasaVozilaDTO) {

        KlasaVozila klasaVozila = this.klasaVozilaService.createKlasaVozila(klasaVozilaDTO);

        return new ResponseEntity<>(klasaVozila, HttpStatus.OK);
    }

    @GetMapping
    List<KlasaVozila> sveKlase() {
        System.out.println("klase vozila");
        return klasaVozilaRepository.findAll();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update_sifrarnik')")
    public ResponseEntity<?> izmenaKlase(@RequestBody KlasaVozilaDTO klasaVozilaDTO) {
        Optional<KlasaVozila> klasaVoziladata = klasaVozilaRepository.findById(klasaVozilaDTO.getId());
        if(klasaVoziladata.isPresent()){
            this.klasaVozilaService.updateKlasaVozila(klasaVozilaDTO);
            return new ResponseEntity<>("Successful updated klasa vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    @PreAuthorize("hasAuthority('delete_sifrarnik')")
    public ResponseEntity<HttpStatus> brisanjeKlase(@PathVariable("id") Long id) {
        try {
            klasaVozilaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }




}
