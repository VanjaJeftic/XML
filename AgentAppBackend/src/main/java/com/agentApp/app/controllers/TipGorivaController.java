package com.agentApp.app.controllers;

import com.agentApp.app.dto.TipGorivaDTO;
import com.agentApp.app.models.TipGoriva;
import com.agentApp.app.repository.TipGorivaRepository;
import com.agentApp.app.services.TipGorivaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/gorivo")
public class TipGorivaController {

    protected final static Logger logger = LoggerFactory.getLogger(TipGorivaController.class);

    @Autowired
    private TipGorivaRepository tipGorivaRepository;
    @Autowired
    private TipGorivaService tipGorivaService;

    @Autowired
    public TipGorivaController(TipGorivaService tipGorivaService){
        this.tipGorivaService=tipGorivaService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_sifrarnik')")
    public ResponseEntity<?> sacuvajTipGoriva(@RequestBody TipGorivaDTO tipGorivaDTO) {

        TipGoriva tipGoriva = this.tipGorivaService.createTipGoriva(tipGorivaDTO);
logger.info("kreiran je tip goriva");
        return new ResponseEntity<>(tipGoriva, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update_sifrarnik')")
    public ResponseEntity<?> izmenaGoriva(@RequestBody TipGorivaDTO tipGorivaDTO) {
        Optional<TipGoriva> tipGorivadata = tipGorivaRepository.findById(tipGorivaDTO.getId());
        if(tipGorivadata.isPresent()){
            this.tipGorivaService.updateTipGoriva(tipGorivaDTO);
            logger.info("Azuriran je tip goriva");
            return new ResponseEntity<>("Successful updated tip goriva vozila", HttpStatus.OK);
        }else{
            logger.info("Tip goriva nije pronadjen");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    List<TipGoriva> svitipoviGorivaVozila() {
        logger.info("Lista svih tipova goriva");
        return tipGorivaRepository.findAll();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    @PreAuthorize("hasAuthority('delete_sifrarnik')")
    public ResponseEntity<HttpStatus> brisanjeGoriva(@PathVariable("id") Long id) {
        try {
            tipGorivaService.delete(id);
            logger.info("Tip goriva je uspesno obrisan");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Tip goriva ili ne moze da se pronadje ili ne moze da se obrise");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}
