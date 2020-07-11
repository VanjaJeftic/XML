package com.agentApp.app.controllers;


import com.agentApp.app.dto.KomentarDTO;
import com.agentApp.app.dto.ModelVozilaDTO;
import com.agentApp.app.models.Komentar;
import com.agentApp.app.models.ModelVozila;
import com.agentApp.app.repository.KomentarRepository;
import com.agentApp.app.repository.ModelVozilaRepository;
import com.agentApp.app.services.KomentarService;
import com.agentApp.app.services.ModelVozilaService;
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
@CrossOrigin(origins = "*")
@RequestMapping(value = "/komentar")
public class KomentarController {

    protected final static Logger logger = LoggerFactory.getLogger(KomentarController.class);

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
        logger.info("Komentar je uspesno kreiran");
        return new ResponseEntity<>(komentar, HttpStatus.OK);
    }

    @GetMapping
    List<Komentar> sviKomentari() {
        logger.info("Lista komentara");
        List<Komentar> komentarx = new ArrayList<Komentar>();
        for (Komentar komentar : komentarRepository.findByOdobren(false)) {
                 if(komentar.isOdbijen()==false){
                komentarx.add(komentar);
            }
            System.out.println("Komentari svi " + komentarx.size());
        }
        return komentarx;
    }


    @GetMapping("/odobreniKomentariZaOglas/{idoglas}")
    List<Komentar> odobreniKomentariZaOglas(@PathVariable("idoglas") Long idoglas) {
        logger.info("Lista odobreniKomentariZaOglas");
        System.out.println("Oglas id za komentar je "+ idoglas);
        List<Komentar> komentarx = new ArrayList<Komentar>();
        for (Komentar komentar : komentarRepository.findByOdobren(true)) {
            if(komentar.isOdbijen()==false && komentar.getOglas_id()==idoglas){
                komentarx.add(komentar);
            }
            System.out.println("Komentari svi " + komentarx.size());
        }
        return komentarx;
    }




    @GetMapping("/sviOdbijeni")
    List<Komentar> sviOdbijeniKomentari() {
        logger.info("Lista komentara odbijenih");
        List<Komentar> komentarx = new ArrayList<Komentar>();
        for (Komentar komentar : komentarRepository.findByOdbijen(true)) {
            if(komentar.isOdobren()==false){
                komentarx.add(komentar);
            }
            System.out.println("Komentari svi odbijeni" + komentarx.size());
        }
        return komentarx;
    }


    @GetMapping("/sviOdobreni")
    List<Komentar> sviOdobreniKomentari() {
        logger.info("Lista komentara odobrenih");
        List<Komentar> komentarx = new ArrayList<Komentar>();
        for (Komentar komentar : komentarRepository.findByOdobren(true)) {
            if(komentar.isOdbijen()==false){
                komentarx.add(komentar);
            }
            System.out.println("Komentari svi odobreni" + komentarx.size());
        }
        return komentarx;
    }


/*
    @GetMapping
    List<Komentar> sviKomentari() {
        logger.info("Lista komentara");
        return komentarRepository.findAll();
    }*/

    @PutMapping("/odobren/{id}")
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaPoljaOdobren(@PathVariable("id") Long id) {
        Komentar k=komentarService.findOne(id);
        Optional<Komentar> komentardata = komentarRepository.findById(id);
        if(komentardata.isPresent()){
            this.komentarService.izmenaPoljaOdobren(k);
            logger.info("Izmena komentara");
            return new ResponseEntity<>("Successful updated komentar", HttpStatus.OK);
        }else{
            logger.info("Komentar nije pronadjen");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/odbijen/{id}")
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaPoljaOdbijen(@PathVariable("id") Long id) {
        Komentar k=komentarService.findOne(id);
        Optional<Komentar> komentardata = komentarRepository.findById(id);
        if(komentardata.isPresent()){
            this.komentarService.izmenaPoljaOdbijen(k);
            logger.info("Izmena komentara");
            return new ResponseEntity<>("Successful updated komentar", HttpStatus.OK);
        }else{
            logger.info("Komentar nije pronadjen");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaKomentara(@RequestBody KomentarDTO komentarDTO) {
        Optional<Komentar> komentardata = komentarRepository.findById(komentarDTO.getId());
        if(komentardata.isPresent()){
            this.komentarService.izmenaKomentara(komentarDTO);
            logger.info("Izmena komentara");
            return new ResponseEntity<>("Successful updated komentar", HttpStatus.OK);
        }else{
            logger.info("Komentar nije pronadjen");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeKomentara(@PathVariable("id") Long id) {
        try {
            komentarService.delete(id);
            logger.info("Komentar je obrisan");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Komentar nije pronadjen");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }



}
