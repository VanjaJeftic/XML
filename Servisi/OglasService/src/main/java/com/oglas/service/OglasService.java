package com.oglas.service;

import com.oglas.connections.UserConnection;
import com.oglas.dto.OglasDTO;
import com.oglas.dto.UserViewDTO;
import com.oglas.exceptions.NotFoundException;
import com.oglas.model.Oglas;
import com.oglas.model.Vozilo;
import com.oglas.repository.OglasRepository;
import com.oglas.repository.VoziloRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

@Service
public class OglasService {

	@Autowired
    private OglasRepository oglasRepository;
	@Autowired
    private VoziloRepository voziloRepository;
	@Autowired
    private UserConnection userConnection;

    @Autowired
    public OglasService(
            OglasRepository oglasRepository,
            UserConnection userConnection
    ) {
        this.oglasRepository = oglasRepository;
        this.userConnection=userConnection;
    }

    public Oglas createOrder(OglasDTO oglasdto) {

       // this.userConnection.verify(oglasdto.getUser_id());
    	Vozilo v=new Vozilo();
    	v=voziloRepository.findById(oglasdto.getVozilo_id()).get();
		v.setUoglasu(true);
		this.voziloRepository.save(v);
        Oglas oglas = this.oglasRepository.save(new Oglas(oglasdto));

        return oglas;
    }

    public Oglas update(OglasDTO oglasdto) {
        Oglas oglas = this.oglasRepository.findById(oglasdto.getId())
                .orElseThrow(() -> new NotFoundException("Oglas with that id does not exist!"));

        oglas.setCena(oglasdto.getCena());
        oglas.setPopust(oglasdto.getPopust());
        oglas.setCenaspopust(oglasdto.getCenaspopust());
        oglas.setId(oglasdto.getId());
        oglas.setMesto(oglasdto.getMesto());
        oglas.setVozilo_id(oglas.getVozilo_id());
        oglas.setUser_id(oglas.getUser_id());
        oglas.setSlobodanOd(oglasdto.getSlobodanod());
        oglas.setSlobodando(oglasdto.getSlobodando());
        oglas.setCdw(oglasdto.getCdw());
        oglas.setDodat(oglasdto.getDodat());
        oglas.setMaxkm(oglasdto.getMaxkm());
        oglas.setOddat(oglasdto.getOddat());
        oglas.setOgranicenjekm(oglasdto.getOgranicenjekm());
        return this.oglasRepository.save(oglas);
    }

    public void delete(Long id) {
         oglasRepository.deleteById(id);
        return;
    }
    
    public boolean verify(Long id) {
    	try {
    		Oglas o = oglasRepository.findById(id).get();
        	if(o != null)
        		return true;
        	return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
    }
    
    public List<Oglas> allOglasi(){
    	return (List<Oglas>) this.oglasRepository.findAll();
    }
    
    public Oglas getOneOglas(Long id) {
    	return this.oglasRepository.findById(id).get();
    }
    
    public UserViewDTO getUser(Long id) {
    	return this.userConnection.getUser(id);
    }
    
    public List<Oglas> findOglasiByVoziloID(Long id){
    	List<Oglas> found = new ArrayList<Oglas>();
    	List<Oglas> oglasi = (List<Oglas>) oglasRepository.findAll();
    	for(Oglas o : oglasi) {
    		if(o.getVozilo_id() == id) {
    			found.add(o);
    		}
    	}
    	return found;
    }
    
    public int brOglasaKorisnika(Long u) {
		// TODO Auto-generated method stub
		int brOglasa=0;
		List<Oglas> oglasi= (List<Oglas>)oglasRepository.findAll();
    	for(Oglas vo:oglasi) {
    		//Vozilo v=voziloRepository.findById(vo.getVozilo_id());
    		if(vo.getUser_id().equals(u)) {
    			brOglasa++;
    		}
    		System.out.println(brOglasa);
    	}
    	
    	return brOglasa;
	}

	public List<Oglas> findMojiOglas(Long user_id) {
		// TODO Auto-generated method stub
		List<Oglas> oglasi=(List<Oglas>) this.oglasRepository.findAll();
		List<Oglas> trazeni=new ArrayList<Oglas>();
		for(Oglas o:oglasi) {
			if(o.getUser_id().equals(user_id)) {
				trazeni.add(o);
			}
		}
		
		return trazeni;
	}

}
