package com.agentApp.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.dto.OglasDTO;
import com.agentApp.app.models.Oglas;
import com.agentApp.app.models.Vozilo;
import com.agentApp.app.repository.OglasRepository;
import com.agentApp.app.repository.VoziloRepository;

@Service
public class OglasService {

	@Autowired
	private OglasRepository oglasRepository;
	
	@Autowired
	private VoziloRepository voziloRepository;
	
	
	public Oglas findOneOglas(Long id) {
		return oglasRepository.findById(id).get();
	}
	
	public List<Oglas> getAllOglasi(){
		return oglasRepository.findAll();
	}

	public Oglas createOrder(OglasDTO oglasdto) {
		Vozilo v=new Vozilo();
		v=voziloRepository.findById(oglasdto.getVozilo_id()).get();
		Oglas o=new Oglas(oglasdto);
		o.setVozilo(v);
	    Oglas oglas = this.oglasRepository.save(o);

	    return oglas;
	}


	public OglasDTO getOneOglasDTO(Long id) {
		Oglas o = this.findOneOglas(id);
		Vozilo v = o.getVozilo();
		  
		return new OglasDTO(o, v);
	}
	
	public List<Oglas> findOglasiByVoziloID(Long id){
    	List<Oglas> found = new ArrayList<Oglas>();
    	List<Oglas> oglasi = (List<Oglas>) oglasRepository.findAll();
    	for(Oglas o : oglasi) {
    		if(o.getVozilo().getId() == id) {
    			found.add(o);
    		}
    	}
    	return found;
    }

}
