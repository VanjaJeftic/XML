package com.admin.adminServis.controller;

import com.admin.adminServis.dto.KlasaVozilaDTO;
import com.admin.adminServis.model.KlasaVozila;
import com.admin.adminServis.repository.KlasaVozilaRepository;
import com.admin.adminServis.service.KlasaVozilaService;
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
@RequestMapping(value = "/klasa")
public class KlasaVozilaController {

    protected final static Logger logger = LoggerFactory.getLogger(KlasaVozilaController.class);

    @Autowired
    private KlasaVozilaService klasaVozilaService;
    @Autowired
    private KlasaVozilaRepository klasaVozilaRepository;

    @Autowired
    public KlasaVozilaController(KlasaVozilaService klasaVozilaService){
        this.klasaVozilaService=klasaVozilaService;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> kreirajKlasu(@RequestBody KlasaVozilaDTO klasaVozilaDTO) {

        KlasaVozila klasaVozila = this.klasaVozilaService.createKlasaVozila(klasaVozilaDTO);
        logger.info("Kreirana je klasa vozila");
        return new ResponseEntity<>(klasaVozila, HttpStatus.OK);
    }

    @GetMapping
    List<KlasaVozila> sveKlase() {
        logger.info("Lista klasa vozila");
        return klasaVozilaRepository.findAll();
    }

    @PutMapping
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaKlase(@RequestBody KlasaVozilaDTO klasaVozilaDTO) {
        Optional<KlasaVozila> klasaVoziladata = klasaVozilaRepository.findById(klasaVozilaDTO.getId());
        if(klasaVoziladata.isPresent()){
            this.klasaVozilaService.updateKlasaVozila(klasaVozilaDTO);
            logger.info("Izmenjena je klasa vozila");
            return new ResponseEntity<>("Successful updated klasa vozila", HttpStatus.OK);
        }else{
            logger.info("Klasa vozila nije izmenjena");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeKlase(@PathVariable("id") Long id) {
        try {
            klasaVozilaService.delete(id);
            logger.info("Klasa vozila je obrisana");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Klasa vozila ili ne moze da se obrise ili nije pronadjena");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
/*
    @GetMapping("/verify/{klasavozila_id}")
    public boolean verify(@PathVariable("klasavozila_id") Long klasavozila_id){
        return klasaVozilaService.verify(klasavozila_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
*/

}
