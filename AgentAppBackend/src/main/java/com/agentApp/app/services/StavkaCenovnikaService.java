package com.agentApp.app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.controllers.VoziloController;
import com.agentApp.app.dto.StavkaCenovnikaDTO;
import com.agentApp.app.exception.NotFoundException;
import com.agentApp.app.models.Cenovnik;
import com.agentApp.app.models.Oglas;
import com.agentApp.app.models.StavkaCenovnika;
import com.agentApp.app.models.Vozilo;
import com.agentApp.app.repository.CenovnikRepository;
import com.agentApp.app.repository.StavkaCenovnikaRepository;
import com.agentApp.app.repository.VoziloRepository;
import com.agentApp.app.repository.OglasRepository;

@Service
public class StavkaCenovnikaService {

	@Autowired
	CenovnikRepository cenovnikRepository;
	
	@Autowired
	StavkaCenovnikaRepository stavkaRepository;
	
	@Autowired
	private VoziloRepository voziloRepository;
	
	@Autowired
	private OglasService oglasService;
	
	@Autowired
	private OglasRepository oglasRepository;
	
	protected final static Logger logger = LoggerFactory.getLogger(VoziloController.class);
	
	
	public StavkaCenovnika createStavkaCenovnika(StavkaCenovnikaDTO stavkaDTO) {
		
		StavkaCenovnika stavka=new StavkaCenovnika(stavkaDTO);
		System.out.println("Oglas id "+stavkaDTO.getOglas_id());
		logger.info("Oglas id "+stavkaDTO.getOglas_id());
		Oglas oglas=oglasRepository.findById(stavkaDTO.getOglas_id()).get();
	//	List<Oglas> o=oglasService.findOglasiByVoziloID(stavkaDTO.getOglas_id());
		//for(Oglas od:o) {
			
			oglas.setUstavci(true);
			stavka.setOglas(oglas);
			this.oglasRepository.save(oglas);
			
	//	}
		
		Cenovnik c=new Cenovnik();
		c=cenovnikRepository.findByNaziv(stavkaDTO.getCenovnik());
		stavka.setCenovnik(c);
		
		
		this.stavkaRepository.save(stavka);
		
		return stavka;
	}

	public StavkaCenovnika updateStavka(StavkaCenovnikaDTO stavkaDTO) {
		
		StavkaCenovnika stavka=this.stavkaRepository.findById(stavkaDTO.getId())
				.orElseThrow(() -> new NotFoundException("Stavka cenovnika with that id doesn't exist!"));
		
		stavka.setId(stavkaDTO.getId());
		stavka.setBr_dana_popust(stavkaDTO.getBr_dana_popust());
		stavka.setCena_po_danu(stavkaDTO.getCena_po_danu());
		stavka.setCena_po_km(stavkaDTO.getCena_po_km());
		stavka.setCena_za_cdw(stavkaDTO.getCena_za_cdw());
		stavka.setPopust(stavkaDTO.getPopust());
		
		return this.stavkaRepository.save(stavka);
	}

	public void deleteStavka(Long id) {
		
		stavkaRepository.deleteById(id);
		return;
	}

	
}
