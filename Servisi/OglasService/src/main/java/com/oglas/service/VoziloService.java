package com.oglas.service;

import com.oglas.connections.UserConnection;
import com.oglas.dto.VoziloDTO;
import com.oglas.exceptions.NotFoundException;
import com.oglas.model.Oglas;
import com.oglas.model.Vozilo;
import com.oglas.repository.OglasRepository;
import com.oglas.repository.VoziloRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoziloService {

    private VoziloRepository voziloRepository;
    @Autowired
    private OglasRepository oglasRepository;
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
    
    public Vozilo saveVozilo(Vozilo v) {
    	return this.voziloRepository.save(v);
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
    
    public List<Vozilo> getVozilaBezOglasa(Long id) {
		// TODO Auto-generated method stub
		List<Vozilo> agenta=new ArrayList<>();
		List<Vozilo> vozila=(List<Vozilo>) voziloRepository.findAll();
		List<Oglas> oglasi=(List<Oglas>) oglasRepository.findAll();
		System.out.println("AAAA "+oglasi.size());
		//Boolean uOglasu=false;
		
		for(Vozilo v:vozila) {
			if(v.getUser_id().equals(id)) {
				Boolean uOglasu=false;
				for(Oglas oo:oglasi) {
					if(oo.getVozilo_id()==v.getId()) {
						uOglasu=true;
						System.out.println("UOGLASU "+uOglasu);
					}else {
						System.out.println("Nije u oglasu");
					}
				}
				if(uOglasu==false) {
					System.out.println("Ubacujem vozilo, ispunjava uslov");
					agenta.add(v);
				}
				
			}
		}
		
		return agenta;
	}

	
	public Vozilo updateVozilo(VoziloDTO voziloDTO) {
		Vozilo v=this.voziloRepository.findById(voziloDTO.getId())
				.orElseThrow(() -> new NotFoundException("vozilo with that id does not exist!"));

		v.setBrsedistadeca(voziloDTO.getBrsedistadeca());
		v.setId(voziloDTO.getId());
		v.setKlasaVozila(voziloDTO.getKlasaVozila());
      	v.setMarkaVozila(voziloDTO.getMarkaVozila());
      	v.setModelVozila(voziloDTO.getModelVozila());
      	v.setTipGoriva(voziloDTO.getTipGoriva());
      	v.setVrstaMenjaca(voziloDTO.getVrstaMenjaca());
		v.setPredjeniKm(voziloDTO.getPredjeniKm());
		v.setUser_id(voziloDTO.getUser_id());
		
		return this.voziloRepository.save(v);
	}
	
}


