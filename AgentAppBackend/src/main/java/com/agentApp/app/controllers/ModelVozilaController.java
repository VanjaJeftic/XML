package com.agentApp.app.controllers;

import com.agentApp.app.dto.ModelVozilaDTO;
import com.agentApp.app.models.ModelVozila;
import com.agentApp.app.repository.ModelVozilaRepository;
import com.agentApp.app.services.ModelVozilaService;
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

        return new ResponseEntity<>(modelVozila, HttpStatus.OK);
    }

    @GetMapping
    List<ModelVozila> sviModeli() {
        System.out.println("modeli");
        return modelVozilaRepository.findAll();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update_sifrarnik')")
    public ResponseEntity<?> izmenaModela(@RequestBody ModelVozilaDTO modelVozilaDTO) {
        Optional<ModelVozila> modelVoziladata = modelVozilaRepository.findById(modelVozilaDTO.getId());
        if(modelVoziladata.isPresent()){
            this.modelVozilaService.updateModelVozila(modelVozilaDTO);
            return new ResponseEntity<>("Successful updated model vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    @PreAuthorize("hasAuthority('delete_sifrarnik')")
    public ResponseEntity<HttpStatus> brisanjeModela(@PathVariable("id") Long id) {
        try {
            modelVozilaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }




}

