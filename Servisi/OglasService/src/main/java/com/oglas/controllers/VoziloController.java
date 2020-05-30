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
import org.springframework.security.access.prepost.PreAuthorize;
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

        VoziloDTO vDTO=new VoziloDTO();

        vDTO.setMarkaVozila(ovDTO.getMarkaVozila());
        vDTO.setModelVozila(ovDTO.getModelVozila());
        vDTO.setKlasaVozila(ovDTO.getKlasaVozila());
        vDTO.setTipgoriva(ovDTO.getTipgoriva());
        vDTO.setVrstamenjaca(ovDTO.getVrstamenjaca());
        vDTO.setPredjeniKm(ovDTO.getPredjeniKm());
        vDTO.setBrSedistaDeca(ovDTO.getBrSedistaDeca());
        vDTO.setUser_id(vDTO.getUser_id());
        System.out.println("Usao pravim vozilo "+ vDTO.getPredjeniKm());
        Vozilo vozilo=this.voziloService.createVozilo(vDTO);
        return new ResponseEntity<>(vozilo, HttpStatus.OK);
    }
}
