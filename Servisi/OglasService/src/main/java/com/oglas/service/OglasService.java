package com.oglas.service;

import com.oglas.connections.UserConnection;
import com.oglas.dto.OglasDTO;
import com.oglas.dto.UserViewDTO;
import com.oglas.exceptions.NotFoundException;
import com.oglas.model.Oglas;
import com.oglas.repository.OglasRepository;

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
 
}
