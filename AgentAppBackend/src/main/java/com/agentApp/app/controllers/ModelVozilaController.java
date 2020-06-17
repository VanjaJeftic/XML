package com.agentApp.app.controllers;

import com.agentApp.app.dto.ModelVozilaDTO;
import com.agentApp.app.models.ModelVozila;
import com.agentApp.app.repository.ModelVozilaRepository;
import com.agentApp.app.services.ModelVozilaService;
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
@RequestMapping(value = "/model")
public class ModelVozilaController {

    @GetMapping("/hello-worlds")
    public String helloWorld() {
        return "Hello World ";
    }

    protected final static Logger logger = LoggerFactory.getLogger(ModelVozilaController.class);

    @Autowired
    private ModelVozilaService modelVozilaService;
    @Autowired
    private ModelVozilaRepository modelVozilaRepository;

    @Autowired
    public ModelVozilaController(ModelVozilaService modelVozilaService){
        this.modelVozilaService=modelVozilaService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_sifrarnik')")
    public ResponseEntity<?> sacuvajModel(@RequestBody ModelVozilaDTO modelVozilaDTO) {

        ModelVozila modelVozila = this.modelVozilaService.createModelVozila(modelVozilaDTO);
        logger.info("Model vozila je sacuvan");
        return new ResponseEntity<>(modelVozila, HttpStatus.OK);
    }

    @GetMapping
    List<ModelVozila> sviModeli() {
        logger.info("Lista svih modela vozila");
        return modelVozilaRepository.findAll();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update_sifrarnik')")
    public ResponseEntity<?> izmenaModela(@RequestBody ModelVozilaDTO modelVozilaDTO) {
        Optional<ModelVozila> modelVoziladata = modelVozilaRepository.findById(modelVozilaDTO.getId());
        if(modelVoziladata.isPresent()){
            this.modelVozilaService.updateModelVozila(modelVozilaDTO);
            logger.info("Model vozila je uspesno izmenjen");
            return new ResponseEntity<>("Successful updated model vozila", HttpStatus.OK);
        }else{
            logger.info("Model vozila nije pronadjen");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    @PreAuthorize("hasAuthority('delete_sifrarnik')")
    public ResponseEntity<HttpStatus> brisanjeModela(@PathVariable("id") Long id) {
        try {
            modelVozilaService.delete(id);
            logger.info("Model vozila je uspeno obrisan");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Model vozila ili nije pronadjen ili ne moze da se obrise");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }




}

