package com.agentApp.app.controllers;

import com.agentApp.app.dto.VrstaMenjacaDTO;
import com.agentApp.app.models.VrstaMenjaca;
import com.agentApp.app.repository.VrstaMenjacaRepository;
import com.agentApp.app.services.VrstaMenjacaService;
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

    @Autowired
    public VrstaMenjacaController(VrstaMenjacaService vrstaMenjacaService){
        this.vrstaMenjacaService=vrstaMenjacaService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_sifrarnik')")
    public ResponseEntity<?> sacuvajVrstuMenjaca(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        VrstaMenjaca vrstaMenjaca = this.vrstaMenjacaService.createVrstaMenjaca(vrstaMenjacaDTO);
        return new ResponseEntity<>(vrstaMenjaca, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update_sifrarnik')")
    public ResponseEntity<?> izmenaVrsteMenjaca(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        Optional<VrstaMenjaca> vrstaMenjacadata = vrstaMenjacaRepository.findById(vrstaMenjacaDTO.getId());
        if(vrstaMenjacadata.isPresent()){
            this.vrstaMenjacaService.updateVrstaMenjaca(vrstaMenjacaDTO);
            return new ResponseEntity<>("Successful updated vrsta menjaca vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    List<VrstaMenjaca> svevrsteMenjacaVozila() {
        System.out.println("sve vrste Menjaca Vozila");
        return vrstaMenjacaRepository.findAll();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    @PreAuthorize("hasAuthority('delete_sifrarnik')")
    public ResponseEntity<HttpStatus> brisanjeVrsteMenjaca(@PathVariable("id") Long id) {
        try {
            vrstaMenjacaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }



}
