package com.admin.adminServis.controller;

import com.admin.adminServis.dto.VrstaMenjacaDTO;
import com.admin.adminServis.model.ModelVozila;
import com.admin.adminServis.model.VrstaMenjaca;
import com.admin.adminServis.repository.VrstaMenjacaRepository;
import com.admin.adminServis.service.VrstaMenjacaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/menjac")
public class VrstaMenjacaController {

    protected final static Logger logger = LoggerFactory.getLogger(VrstaMenjacaController.class);

    @Autowired
    private VrstaMenjacaService vrstaMenjacaService;
    @Autowired
    private VrstaMenjacaRepository vrstaMenjacaRepository;

    @Autowired
    public VrstaMenjacaController(VrstaMenjacaService vrstaMenjacaService){
        this.vrstaMenjacaService=vrstaMenjacaService;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> sacuvajVrstuMenjaca(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        VrstaMenjaca vrstaMenjaca = this.vrstaMenjacaService.createVrstaMenjaca(vrstaMenjacaDTO);
        logger.info("Kreirana je vrsta menjaca");
        return new ResponseEntity<>(vrstaMenjaca, HttpStatus.OK);
    }

    @PutMapping
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaVrsteMenjaca(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        Optional<VrstaMenjaca> vrstaMenjacadata = vrstaMenjacaRepository.findById(vrstaMenjacaDTO.getId());
        if(vrstaMenjacadata.isPresent()){
            this.vrstaMenjacaService.updateVrstaMenjaca(vrstaMenjacaDTO);
            logger.info("Izmenjena je vrsta menjaca");
            return new ResponseEntity<>("Successful updated vrsta menjaca vozila", HttpStatus.OK);
        }else{
            logger.info("Vrsta menjaca nije pronadjena");
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
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeVrsteMenjaca(@PathVariable("id") Long id) {
        try {
            vrstaMenjacaService.delete(id);
            logger.info("Obrisana je vrsta menjaca");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Vrsta menjaca ne moze da se obrise ili nije pronadjena");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
/*
    @GetMapping("/verify/{vrstamenjaca_id}")
    public boolean verify(@PathVariable("vrstamenjaca_id") Long vrstamenjaca_id){
        return vrstaMenjacaService.verify(vrstamenjaca_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
*/

}
