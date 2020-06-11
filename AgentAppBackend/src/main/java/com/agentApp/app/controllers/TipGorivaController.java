package com.agentApp.app.controllers;

import com.agentApp.app.dto.TipGorivaDTO;
import com.agentApp.app.models.TipGoriva;
import com.agentApp.app.repository.TipGorivaRepository;
import com.agentApp.app.services.TipGorivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/gorivo")
public class TipGorivaController {

    @Autowired
    private TipGorivaRepository tipGorivaRepository;
    @Autowired
    private TipGorivaService tipGorivaService;

    @Autowired
    public TipGorivaController(TipGorivaService tipGorivaService){
        this.tipGorivaService=tipGorivaService;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> sacuvajTipGoriva(@RequestBody TipGorivaDTO tipGorivaDTO) {

        TipGoriva tipGoriva = this.tipGorivaService.createTipGoriva(tipGorivaDTO);

        return new ResponseEntity<>(tipGoriva, HttpStatus.OK);
    }

    @PutMapping
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaGoriva(@RequestBody TipGorivaDTO tipGorivaDTO) {
        Optional<TipGoriva> tipGorivadata = tipGorivaRepository.findById(tipGorivaDTO.getId());
        if(tipGorivadata.isPresent()){
            this.tipGorivaService.updateTipGoriva(tipGorivaDTO);
            return new ResponseEntity<>("Successful updated tip goriva vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    List<TipGoriva> svitipoviGorivaVozila() {
        System.out.println("tipoviGorivaVozila svi");
        return tipGorivaRepository.findAll();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeGoriva(@PathVariable("id") Long id) {
        try {
            tipGorivaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}
