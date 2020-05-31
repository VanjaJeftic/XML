package com.admin.adminServis.controller;

import com.admin.adminServis.dto.MarkaVozilaDTO;
import com.admin.adminServis.dto.TipGorivaDTO;
import com.admin.adminServis.model.MarkaVozila;
import com.admin.adminServis.model.TipGoriva;
import com.admin.adminServis.repository.TipGorivaRepository;
import com.admin.adminServis.service.TipGorivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TipGorivaController {

    @GetMapping("/hello-worlds")
    public String helloWorld() {
        return "Hello World ";
    }

    private TipGorivaRepository tipGorivaRepository;
    private TipGorivaService tipGorivaService;

    @Autowired
    public TipGorivaController(TipGorivaService tipGorivaService){
        this.tipGorivaService=tipGorivaService;
    }

    @PostMapping("/createGorivo")
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> createGorivo(@RequestBody TipGorivaDTO tipGorivaDTO) {

        TipGoriva tipGoriva = this.tipGorivaService.createTipGoriva(tipGorivaDTO);

        return new ResponseEntity<>(tipGoriva, HttpStatus.OK);
    }

    @PutMapping("/updateGorivo")
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> updateGorivo(@RequestBody TipGorivaDTO tipGorivaDTO) {
        Optional<TipGoriva> tipGorivadata = tipGorivaRepository.findById(tipGorivaDTO.getId());
        if(tipGorivadata.isPresent()){
            this.tipGorivaService.updateTipGoriva(tipGorivaDTO);
            return new ResponseEntity<>("Successful updated tip goriva vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteGorivo/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> deleteGorivo(@PathVariable("id") Long id) {
        try {
            tipGorivaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/verify/{tipgoriva_id}")
    public boolean verify(@PathVariable("tipgoriva_id") Long tipgoriva_id){
        return tipGorivaService.verify(tipgoriva_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
