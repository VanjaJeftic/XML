package com.oglas.controllers;

import com.oglas.dto.OglasDTO;
import com.oglas.dto.OglasVoziloDTO;
import com.oglas.dto.VoziloDTO;
import com.oglas.model.Oglas;
import com.oglas.model.Vozilo;
import com.oglas.repository.VoziloRepository;
import com.oglas.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VoziloController {

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private VoziloRepository voziloRepository;

    @PostMapping("/novoVozilo")


    // @PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> create(@RequestBody VoziloDTO ovDTO) {

        System.out.println("Usao pravim vozilo "+ ovDTO.getPredjeniKm()+ovDTO.getTipGoriva()+ovDTO.getVrstaMenjaca());
        Vozilo vozilo=this.voziloService.createVozilo(ovDTO);
        return new ResponseEntity<>(vozilo, HttpStatus.OK);
    }
}
