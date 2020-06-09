package com.admin.adminServis.controller;

import com.admin.adminServis.dto.ModelVozilaDTO;
import com.admin.adminServis.model.ModelVozila;
import com.admin.adminServis.repository.ModelVozilaRepository;
import com.admin.adminServis.service.ModelVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/sacuvajModel")
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> sacuvajModel(@RequestBody ModelVozilaDTO modelVozilaDTO) {

        ModelVozila modelVozila = this.modelVozilaService.createModelVozila(modelVozilaDTO);

        return new ResponseEntity<>(modelVozila, HttpStatus.OK);
    }

    @GetMapping("/modeliVozila")
    List<ModelVozila> sviModeli() {
        System.out.println("modeli");
        return modelVozilaRepository.findAll();
    }

    @PutMapping("/izmenaModela")
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaModela(@RequestBody ModelVozilaDTO modelVozilaDTO) {
        Optional<ModelVozila> modelVoziladata = modelVozilaRepository.findById(modelVozilaDTO.getId());
        if(modelVoziladata.isPresent()){
            this.modelVozilaService.updateModelVozila(modelVozilaDTO);
            return new ResponseEntity<>("Successful updated model vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/brisanjeModela/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeModela(@PathVariable("id") Long id) {
        try {
            modelVozilaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/verify/{modelvozila_id}")
    public boolean verify(@PathVariable("modelvozila_id") Long modelvozila_id){
        return modelVozilaService.verify(modelvozila_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}

