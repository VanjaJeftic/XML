package com.admin.adminServis.controller;
import com.admin.adminServis.dto.MarkaVozilaDTO;
import com.admin.adminServis.model.MarkaVozila;
import com.admin.adminServis.repository.MarkaVozilaRepository;
import com.admin.adminServis.service.MarkaVozilaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/marka")
public class MarkaVozilaController {

    protected final static Logger logger = LoggerFactory.getLogger(MarkaVozilaController.class);

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
        logger.info("Nova marka vozila");
        MarkaVozila markaVozila = this.markaVozilaService.createMarkaVozila(markaVozilaDTO);

        return new ResponseEntity<>(markaVozila, HttpStatus.OK);
    }


    @PutMapping
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaMarka(@RequestBody MarkaVozilaDTO markaVozilaDTO) {

        Optional<MarkaVozila> markaVoziladata = markaVozilaRepository.findById(markaVozilaDTO.getId());
        if(markaVoziladata.isPresent()){
            this.markaVozilaService.updateMarkaVozila(markaVozilaDTO);
            logger.info("Izmenjena je marka vozila");
            return new ResponseEntity<>("Successful updated marka vozila", HttpStatus.OK);
        }else{
            logger.info("Marka vozila nije pronadjena");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeMarke(@PathVariable("id") Long id) {

        try {
            markaVozilaService.delete(id);
            logger.info("Marka vozila je obrisana");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Marka vozila ili nije pronadjena ili ne moze da se obrise");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    List<MarkaVozila> sveMarke() {
        logger.info("Lista marki vozila");
        return markaVozilaRepository.findAll();
    }
/*
    @GetMapping("/verify/{markavozila_id}")
    public boolean verify(@PathVariable("markavozila_id") Long markavozila_id){
        return markaVozilaService.verify(markavozila_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

*/
}
