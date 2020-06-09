package com.admin.adminServis.controller;

import com.admin.adminServis.dto.KlasaVozilaDTO;
import com.admin.adminServis.model.KlasaVozila;
import com.admin.adminServis.repository.KlasaVozilaRepository;
import com.admin.adminServis.service.KlasaVozilaService;
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

    @Autowired
    private KlasaVozilaService klasaVozilaService;
    @Autowired
    private KlasaVozilaRepository klasaVozilaRepository;

    @Autowired
    public KlasaVozilaController(KlasaVozilaService klasaVozilaService){
        this.klasaVozilaService=klasaVozilaService;
    }

    @PostMapping("/sacuvajKlasu")
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> kreirajKlasu(@RequestBody KlasaVozilaDTO klasaVozilaDTO) {

        KlasaVozila klasaVozila = this.klasaVozilaService.createKlasaVozila(klasaVozilaDTO);

        return new ResponseEntity<>(klasaVozila, HttpStatus.OK);
    }

    @GetMapping("/klaseVozila")
    List<KlasaVozila> sveKlase() {
        System.out.println("klase vozila");
        return klasaVozilaRepository.findAll();
    }

    @PutMapping("/izmenaKlase")
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaKlase(@RequestBody KlasaVozilaDTO klasaVozilaDTO) {
        Optional<KlasaVozila> klasaVoziladata = klasaVozilaRepository.findById(klasaVozilaDTO.getId());
        if(klasaVoziladata.isPresent()){
            this.klasaVozilaService.updateKlasaVozila(klasaVozilaDTO);
            return new ResponseEntity<>("Successful updated klasa vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/brisanjeKlase/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeKlase(@PathVariable("id") Long id) {
        try {
            klasaVozilaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/verify/{klasavozila_id}")
    public boolean verify(@PathVariable("klasavozila_id") Long klasavozila_id){
        return klasaVozilaService.verify(klasavozila_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
