package com.agentApp.app.services;

import com.agentApp.app.dto.MarkaVozilaDTO;
import com.agentApp.app.exception.NotFoundException;
import com.agentApp.app.models.MarkaVozila;
import com.agentApp.app.repository.MarkaVozilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkaVozilaService {

    @Autowired
    private MarkaVozilaRepository markaVozilaRepository;

    @Autowired
    public MarkaVozilaService(
            MarkaVozilaRepository markaVozilaRepository
    ) {
        this.markaVozilaRepository = markaVozilaRepository;
    }

    public MarkaVozila createMarkaVozila(MarkaVozilaDTO markaVozilaDTO) {
        // this.userConnection.verify(oglasdto.getUser_id());
        MarkaVozila markaVozila = this.markaVozilaRepository.save(new MarkaVozila(markaVozilaDTO));
        return markaVozila;
    }

    public MarkaVozila updateMarkaVozila(MarkaVozilaDTO markaVozilaDTO) {
        System.out.println("Dosli smo do update Marka servis");
        MarkaVozila markaVozila = this.markaVozilaRepository.findById(markaVozilaDTO.getId())
                .orElseThrow(() -> new NotFoundException("markaVozila with that id does not exist!"));

        markaVozila.setId(markaVozilaDTO.getId());
        markaVozila.setNaziv(markaVozilaDTO.getNaziv());

        return this.markaVozilaRepository.save(markaVozila);
    }

    public void delete(Long id) {
        markaVozilaRepository.deleteById(id);
        return;
    }

    public boolean verify(Long id) {
        try {
            MarkaVozila o = markaVozilaRepository.findById(id).get();
            if(o != null)
                return true;
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

}