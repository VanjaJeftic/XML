package com.agentApp.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.dto.VoziloDTO;
import com.agentApp.app.models.User;
import com.agentApp.app.models.Vozilo;
import com.agentApp.app.repository.KlasaVozilaRepository;
import com.agentApp.app.repository.MarkaVozilaRepository;
import com.agentApp.app.repository.ModelVozilaRepository;
import com.agentApp.app.repository.VoziloRepository;
import com.agentApp.app.repository.TipGorivaRepository;
import com.agentApp.app.repository.VrstaMenjacaRepository;


@Service
public class VoziloService {
	
	@Autowired
	private VoziloRepository voziloRepository;
	
	@Autowired
	private MarkaVozilaRepository markaRepo;
	
	@Autowired
	private ModelVozilaRepository modelRepo;
	
	@Autowired
	private KlasaVozilaRepository klasaRepo;
	
	@Autowired
	private TipGorivaRepository gorivoRepo;
	
	@Autowired
	private VrstaMenjacaRepository menjacRepo;
	
	@Autowired
	private UserService userService;
	
	 public Vozilo createVozilo(VoziloDTO vozilodto) {

	      	Vozilo v=new Vozilo();
	      	v.setBrSedistaDeca(vozilodto.getBrsedistadeca());
	      	v.setCdw(vozilodto.isCdw());
	      	v.setKlasaVozila(klasaRepo.findByNaziv(vozilodto.getKlasaVozila()));
	      	v.setMarkaVozila(markaRepo.findByNaziv(vozilodto.getMarkaVozila()).get(0));
	      	v.setModelVozila(modelRepo.findByNaziv(vozilodto.getModelVozila()));
	      	v.setTipgoriva(gorivoRepo.findByNaziv(vozilodto.getTipGoriva()));
	      	v.setVrstamenjaca(menjacRepo.findByNaziv(vozilodto.getVrstaMenjaca()));
	      	v.setPredjeniKm(vozilodto.getPredjeniKm());
	      	v.setUser(userService.findByUsername(vozilodto.getUser().getUsername()));
	        Vozilo vozilo = this.voziloRepository.save(v);

	        return vozilo;
	    }

	public List<Vozilo> getVozila(User user) {
		// TODO Auto-generated method stub
		List<Vozilo> agenta=new ArrayList<>();
		List<Vozilo> vozila=voziloRepository.findAll();
		for(Vozilo v:vozila) {
			if(v.getUser().getUsername().equals(user.getUsername())) {
				agenta.add(v);
			}
		}
		return agenta;
	}
	
	public Vozilo getVozilo(Long id) {
    	return this.voziloRepository.findById(id).get();
    }
	
	public List<Vozilo> getVozilaForAgent(Long id){
    	List<Vozilo> agenta = new ArrayList<>();
    	List<Vozilo> vozila = (List<Vozilo>) this.voziloRepository.findAll();
    	for(Vozilo v : vozila) {
    		if(v.getId() == id) {
    			agenta.add(v);
    		}
    	}
    	return agenta;
    }

}
