package com.admin.adminServis.controller;

import com.admin.adminServis.dto.VrstaMenjacaDTO;
import com.admin.adminServis.model.ModelVozila;
import com.admin.adminServis.model.VrstaMenjaca;
import com.admin.adminServis.repository.VrstaMenjacaRepository;
import com.admin.adminServis.service.VrstaMenjacaService;
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

    @Autowired
    private VrstaMenjacaService vrstaMenjacaService;
    @Autowired
    private VrstaMenjacaRepository vrstaMenjacaRepository;

    @Autowired
    public VrstaMenjacaController(VrstaMenjacaService vrstaMenjacaService){
        this.vrstaMenjacaService=vrstaMenjacaService;
    }

    @PostMapping("/sacuvajVrstuMenjaca")
    //@PreAuthorize("hasAuthority('create_oglas')")
    public ResponseEntity<?> sacuvajVrstuMenjaca(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        VrstaMenjaca vrstaMenjaca = this.vrstaMenjacaService.createVrstaMenjaca(vrstaMenjacaDTO);
        return new ResponseEntity<>(vrstaMenjaca, HttpStatus.OK);
    }

    @PutMapping("/izmenaVrsteMenjaca")
    //@PreAuthorize("hasAuthority('update_oglas')")
    public ResponseEntity<?> izmenaVrsteMenjaca(@RequestBody VrstaMenjacaDTO vrstaMenjacaDTO) {
        Optional<VrstaMenjaca> vrstaMenjacadata = vrstaMenjacaRepository.findById(vrstaMenjacaDTO.getId());
        if(vrstaMenjacadata.isPresent()){
            this.vrstaMenjacaService.updateVrstaMenjaca(vrstaMenjacaDTO);
            return new ResponseEntity<>("Successful updated vrsta menjaca vozila", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vrsteMenjacaVozila")
    List<VrstaMenjaca> svevrsteMenjacaVozila() {
        System.out.println("sve vrste Menjaca Vozila");
        return vrstaMenjacaRepository.findAll();
    }

    @DeleteMapping("/brisanjeVrsteMenjaca/{id}")
    //@PreAuthorize("hasRole('ROLE_operator')")
    //@PreAuthorize("hasAuthority('delete_oglas')")
    public ResponseEntity<HttpStatus> brisanjeVrsteMenjaca(@PathVariable("id") Long id) {
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
