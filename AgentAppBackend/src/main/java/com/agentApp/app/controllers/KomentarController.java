package com.agentApp.app.controllers;


import com.agentApp.app.dto.KomentarDTO;
import com.agentApp.app.dto.ModelVozilaDTO;
import com.agentApp.app.models.Komentar;
import com.agentApp.app.models.ModelVozila;
import com.agentApp.app.repository.KomentarRepository;
import com.agentApp.app.repository.ModelVozilaRepository;
import com.agentApp.app.services.KomentarService;
import com.agentApp.app.services.ModelVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/komentar")
public class KomentarController {

    @Autowired
    private KomentarService komentarService;
    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    public KomentarController(KomentarService komentarService){
        this.komentarService=komentarService;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> sacuvajKomentar(@RequestBody KomentarDTO dto) {

        Komentar komentar = this.komentarService.kreirajKomentar(dto);

        return new ResponseEntity<>(komentar, HttpStatus.OK);
    }

    @GetMapping
    List<Komentar> sviKomentari() {
        System.out.println("komentari");
        return komentarRepository.findAll();
    }

    @PutMapping
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaKomentara(@RequestBody KomentarDTO komentarDTO) {
        Optional<Komentar> komentardata = komentarRepository.findById(komentarDTO.getId());
        if(komentardata.isPresent()){
            this.komentarService.izmenaKomentara(komentarDTO);
            return new ResponseEntity<>("Successful updated komentar", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeKomentara(@PathVariable("id") Long id) {
        try {
            komentarService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }



}
