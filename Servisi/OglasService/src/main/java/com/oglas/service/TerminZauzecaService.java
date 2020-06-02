package com.oglas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oglas.repository.TerminZauzecaRepository;
import com.oglas.connections.ZahtevConnection;
import com.oglas.dto.TerminZauzecaDTO;
import com.oglas.dto.TerminZauzecaZahtevDTO;
import com.oglas.exceptions.NotFoundException;
import com.oglas.model.TerminZauzeca;
@Service
public class TerminZauzecaService {

	private TerminZauzecaRepository terminRepository;
	@Autowired
	private ZahtevConnection zahtevConnection;

	@Autowired
	public TerminZauzecaService(TerminZauzecaRepository terminRepository) {
		this.terminRepository = terminRepository;
	}
	
	public TerminZauzeca createTermin(TerminZauzecaDTO terminDTO) {
		TerminZauzeca termin=this.terminRepository.save(new TerminZauzeca(terminDTO));
	
		return termin;
	}
	
	public TerminZauzeca update(TerminZauzecaDTO terminDTO) {
		
		TerminZauzeca termin=this.terminRepository.findById(terminDTO.getId())
				.orElseThrow(() -> new NotFoundException("Termin zauzeca with that id does not exist!"));
		
		termin.setVozilo_id(terminDTO.getVozilo_id());
		termin.setZauzetod(terminDTO.getZauzetod());
		termin.setZauzetdo(terminDTO.getZauzetdo());
		
		return this.terminRepository.save(termin);

	}
	
	 public void delete(Long id) {
	        terminRepository.deleteById(id);
	        return;
	 }
	 
	 public boolean zauzece(TerminZauzecaZahtevDTO tzz) {
		 return this.zahtevConnection.zauzece(tzz);
	 }
}
