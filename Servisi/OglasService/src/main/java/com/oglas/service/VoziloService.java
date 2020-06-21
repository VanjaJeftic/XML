package com.oglas.service;


import com.oglas.connections.UserConnection;
import com.oglas.dto.VoziloDTO;
import com.oglas.exceptions.NotFoundException;
import com.oglas.model.Vozilo;
import com.oglas.repository.VoziloRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoziloService {

    private VoziloRepository voziloRepository;
    private UserConnection userConnection;

    @Autowired
    public VoziloService(
            VoziloRepository voziloRepository,
            UserConnection userConnection
    ) {
        this.voziloRepository = voziloRepository;
        this.userConnection=userConnection;
    }

    public Vozilo createVozilo(VoziloDTO vozilodto) {

       // this.userConnection.verify(vozilodto.getUser_id());

        Vozilo vozilo = this.voziloRepository.save(new Vozilo(vozilodto));

        return vozilo;
    }

    public Vozilo update(VoziloDTO vozilodto) {
        Vozilo vozilo = this.voziloRepository.findById(vozilodto.getId())
                .orElseThrow(() -> new NotFoundException("Vozilo with that id does not exist!"));

       vozilo.setUser_id(vozilodto.getUser_id());
       vozilo.setBrsedistadeca(vozilodto.getBrsedistadeca());
       vozilo.setCdw(vozilodto.isCdw());
       vozilo.setId(vozilodto.getId());
       vozilo.setKlasaVozila(vozilodto.getKlasaVozila());
       vozilo.setMarkaVozila(vozilodto.getMarkaVozila());
       vozilo.setModelVozila(vozilodto.getModelVozila());
       vozilo.setPredjeniKm(vozilodto.getPredjeniKm());
       vozilo.setTipGoriva(vozilodto.getTipGoriva());
       vozilo.setVrstaMenjaca(vozilodto.getVrstaMenjaca());

        return this.voziloRepository.save(vozilo);
    }

    public void delete(Long id) {
        voziloRepository.deleteById(id);
        return;
    }
    
    public Vozilo getVozilo(Long id) {
    	return this.voziloRepository.findById(id).get();
    }
    
 
    public List<Vozilo> getVozila(Long id){
    	List<Vozilo> agenta = new ArrayList<>();
    	List<Vozilo> vozila = (List<Vozilo>) this.voziloRepository.findAll();
    	for(Vozilo v : vozila) {
    		if(v.getUser_id() == id) {
    			agenta.add(v);
    		}
    	}
    	return agenta;
    }
    
    

}
