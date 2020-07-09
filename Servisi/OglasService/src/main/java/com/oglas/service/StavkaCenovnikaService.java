package com.oglas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oglas.dto.StavkaCenovnikaDTO;
import com.oglas.model.Oglas;
import com.oglas.model.Cenovnik;
import com.oglas.model.StavkaCenovnika;
import com.oglas.repository.CenovnikRepository;
import com.oglas.repository.OglasRepository;
import com.oglas.repository.StavkaCenovnikaRepository;


@Service
public class StavkaCenovnikaService {

	@Autowired
	CenovnikRepository cenovnikRepository;
	
	@Autowired
	StavkaCenovnikaRepository stavkaRepository;
	
	@Autowired
	private OglasRepository oglasRepository;
	
	protected final static Logger logger = LoggerFactory.getLogger(StavkaCenovnikaService.class);
	
	
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
		
		StavkaCenovnika stavka=this.stavkaRepository.findById(stavkaDTO.getId()).get();
		
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
