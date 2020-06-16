package com.agentApp.app.controllers;

import com.agentApp.app.dto.VrstaMenjacaDTO;
import com.agentApp.app.models.VrstaMenjaca;
import com.agentApp.app.repository.VrstaMenjacaRepository;
import com.agentApp.app.services.VrstaMenjacaService;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/menjac")
public class VrstaMenjacaController {

    @Autowired
    private VrstaMenjacaService vrstaMenjacaService;
    @Autowired
    private VrstaMenjacaRepository vrstaMenjacaRepository;

    protected final static Logger logger = LoggerFactory.getLogger(VrstaMenjacaController.class);

    @Autowired
    public VrstaMenjacaController(VrstaMenjacaService vrstaMenjacaService){
        this.vrstaMenjacaService=vrstaMenjacaService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_sifrarnik')")
    public ResponseEntity<?> sacuvajVrstuMenjaca(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        VrstaMenjaca vrstaMenjaca = this.vrstaMenjacaService.createVrstaMenjaca(vrstaMenjacaDTO);
        logger.info("Uspesno je kreirana vrsta menjaca");
        return new ResponseEntity<>(vrstaMenjaca, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update_sifrarnik')")
    public ResponseEntity<?> izmenaVrsteMenjaca(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        Optional<VrstaMenjaca> vrstaMenjacadata = vrstaMenjacaRepository.findById(vrstaMenjacaDTO.getId());
        if(vrstaMenjacadata.isPresent()){
            this.vrstaMenjacaService.updateVrstaMenjaca(vrstaMenjacaDTO);
            logger.info("Uspesno je azurirana vrsta menjaca");
            return new ResponseEntity<>("Successful updated vrsta menjaca vozila", HttpStatus.OK);
        }else{
            logger.info("Vrsta menjaca nije update-ovana");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    List<VrstaMenjaca> svevrsteMenjacaVozila() {
        logger.info("Lista vrsta menjaca");
        return vrstaMenjacaRepository.findAll();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    @PreAuthorize("hasAuthority('delete_sifrarnik')")
    public ResponseEntity<HttpStatus> brisanjeVrsteMenjaca(@PathVariable("id") Long id) {
        try {
            vrstaMenjacaService.delete(id);
            logger.info("Uspesno je obrisana vrsta menjaca");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Vrsta menjaca ili nije pronadjena ili ne moze da se obrise");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }



}
