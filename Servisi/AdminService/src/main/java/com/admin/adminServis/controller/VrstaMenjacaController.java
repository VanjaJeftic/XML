package com.admin.adminServis.controller;

import com.admin.adminServis.dto.VrstaMenjacaDTO;
import com.admin.adminServis.model.VrstaMenjaca;
import com.admin.adminServis.repository.VrstaMenjacaRepository;
import com.admin.adminServis.service.VrstaMenjacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/menjac")
public class VrstaMenjacaController {

    private VrstaMenjacaService vrstaMenjacaService;
    private VrstaMenjacaRepository vrstaMenjacaRepository;

    @Autowired
    public VrstaMenjacaController(VrstaMenjacaService vrstaMenjacaService){
        this.vrstaMenjacaService=vrstaMenjacaService;
    }

    @PostMapping("/novaVrstaMenjaca")
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> createMenjac(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        VrstaMenjaca vrstaMenjaca = this.vrstaMenjacaService.createVrstaMenjaca(vrstaMenjacaDTO);
        return new ResponseEntity<>(vrstaMenjaca, HttpStatus.OK);
    }

    @PutMapping("/updateMenjac")
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> updateMenjac(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        Optional<VrstaMenjaca> vrstaMenjacadata = vrstaMenjacaRepository.findById(vrstaMenjacaDTO.getId());
        if(vrstaMenjacadata.isPresent()){
            this.vrstaMenjacaService.updateVrstaMenjaca(vrstaMenjacaDTO);
            return new ResponseEntity<>("Successful updated vrsta menjaca vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteMenjac/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> deleteMenjac(@PathVariable("id") Long id) {
        try {
            vrstaMenjacaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/verify/{vrstamenjaca_id}")
    public boolean verify(@PathVariable("vrstamenjaca_id") Long vrstamenjaca_id){
        return vrstaMenjacaService.verify(vrstamenjaca_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
