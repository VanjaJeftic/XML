package com.agentApp.app.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.dto.OglasDTO;
import com.agentApp.app.exception.NotFoundException;
import com.agentApp.app.models.Oglas;
import com.agentApp.app.models.User;
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
		v.setUoglasu(true);
		Vozilo vozilo=voziloRepository.save(v);
		Oglas o=new Oglas(oglasdto);
		o.setVozilo(vozilo);
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

	public int brOglasaKorisnika(User u) {
		// TODO Auto-generated method stub
		int brOglasa=0;
		List<Oglas> oglasi=oglasRepository.findAll();
    	for(Oglas v:oglasi) {
    		if(v.getVozilo().getUser().getId().equals(u.getId())) {
    			brOglasa++;
    		}
    		System.out.println(brOglasa);
    	}
    	
    	return brOglasa;
	}

	public List<Oglas> findMyOglas(User p) {
		// TODO Auto-generated method stub
		List<Oglas> oglasi=this.oglasRepository.findAll();
		List<Oglas> trazeni=new ArrayList<Oglas>();
		for(Oglas o:oglasi) {
			if(o.getVozilo().getUser().getId().equals(p.getId())) {
				trazeni.add(o);
			}
		}
		
		return trazeni;
		
	}
	
	public void delete(Long id) {
		oglasRepository.deleteById(id);
		return;
	}
	
	public Oglas updateOglas(OglasDTO oglasDTO) {
		
		Oglas o=this.oglasRepository.findById(oglasDTO.getId())
				.orElseThrow(() -> new NotFoundException("Oglas with that id does not exist!"));
		
		o.setCdw(oglasDTO.getCdw());
		o.setCena(oglasDTO.getCena());
		o.setDodat(oglasDTO.getDodat());
		o.setId(oglasDTO.getId());
		o.setMaxkm(oglasDTO.getMaxkm());
		o.setMesto(oglasDTO.getMesto());
		o.setOddat(oglasDTO.getOddat());
		o.setOgranicenjekm(oglasDTO.getOgranicenjekm());
		o.setSlobodanDo(oglasDTO.getSlobodanDo());
		o.setSlobodanOd(oglasDTO.getSlobodanOd());
		Vozilo v=new Vozilo();
		v=voziloRepository.findById(oglasDTO.getVozilo_id()).get();
		o.setVozilo(v);
		
		return this.oglasRepository.save(o);
	}

}
