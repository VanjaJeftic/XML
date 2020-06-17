package com.oglas.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oglas.repository.TerminZauzecaRepository;
import com.oglas.connections.ZahtevConnection;
import com.oglas.dto.TerminZauzecaDTO;
import com.oglas.dto.TerminZauzecaZahtevDTO;
import com.oglas.dto.ZahtevDTO;
import com.oglas.exceptions.NotFoundException;
import com.oglas.model.TerminZauzeca;
import com.oglas.model.Vozilo;
@Service
public class TerminZauzecaService {

	private TerminZauzecaRepository terminRepository;
	@Autowired
	private ZahtevConnection zahtevConnection;
	@Autowired
	private VoziloService voziloService;

	@Autowired
	public TerminZauzecaService(TerminZauzecaRepository terminRepository) {
		this.terminRepository = terminRepository;
	}
	
	public TerminZauzeca createTermin(TerminZauzecaDTO terminDTO) {
		TerminZauzeca termin=this.terminRepository.save(new TerminZauzeca(terminDTO));
	
		return termin;
	}
	
	public TerminZauzeca createTermin(TerminZauzecaDTO terminDTO, Vozilo vehicle) {
		TerminZauzeca termin=this.terminRepository.save(new TerminZauzeca(terminDTO, vehicle));
	
		return termin;
	}
	
	public TerminZauzeca update(TerminZauzecaDTO terminDTO) {
		
		TerminZauzeca termin=this.terminRepository.findById(terminDTO.getId())
				.orElseThrow(() -> new NotFoundException("Termin zauzeca with that id does not exist!"));
		
		//termin.setVozilo_id(terminDTO.getVozilo_id());
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
	 
	 public int provjeraZauzetostiVozila(TerminZauzecaDTO terminZauzimanjaDTO) {
		 
		 Vozilo vozilo = this.voziloService.getVozilo(terminZauzimanjaDTO.getVozilo_id());
		 
		 Set<Integer> counter = new HashSet<>();
		 int imaPodudaranja = 0;	//Na pocetku nema podudaranja
		 
		 if(vozilo.getZauzeti().size() < 1) {
			 System.out.println("Nema termina uopste! ProvjeraZauzetostiVozila");
			 return 0;
		 }
			
		 for(TerminZauzeca termin : vozilo.getZauzeti()) {
			 imaPodudaranja = this.provjeriPodudaranje(termin, terminZauzimanjaDTO);
			 if(imaPodudaranja == 1) {
				 counter.add(1);
			 }
		 }
		 
		 if(counter.contains(1)) {
			return 1;
		 }else {
			return 0;
		 }
	 }
	 															//zahtevPreuzimanje - Provjera termina kada se zeli preuzeti
	 public int provjeriPodudaranje(TerminZauzeca zauzeto, TerminZauzecaDTO zahtevPreuzimanje) {
		 
		 
		 if( (zauzeto.getZauzetod().isAfter(zahtevPreuzimanje.getZauzetod()) && zauzeto.getZauzetdo().isBefore(zahtevPreuzimanje.getZauzetdo())
					|| (zauzeto.getZauzetod().isBefore(zahtevPreuzimanje.getZauzetod()) && zauzeto.getZauzetdo().isAfter(zahtevPreuzimanje.getZauzetod()))) 
					|| (zauzeto.getZauzetod().isBefore(zahtevPreuzimanje.getZauzetdo()) && zauzeto.getZauzetdo().isAfter(zahtevPreuzimanje.getZauzetdo()))
					|| (zauzeto.getZauzetod().isBefore(zahtevPreuzimanje.getZauzetod()) && zauzeto.getZauzetdo().isAfter(zahtevPreuzimanje.getZauzetdo()))
					|| (zauzeto.getZauzetod().isEqual(zahtevPreuzimanje.getZauzetod()) && zauzeto.getZauzetdo().isEqual(zahtevPreuzimanje.getZauzetdo()) ) ) {
			 
			 return 1;		//Nasao podudaranje
			}
		 
		 return 0;
	 }
	 
	 public void obrisiTermin(Vozilo v, ZahtevDTO z) {
		 List<TerminZauzeca> termini = v.getZauzeti();
		 
		 for(TerminZauzeca termin : termini) {
				System.out.println("Termin od: " + termin.getZauzetod() +", zahtev od: " + termin.getZauzetdo());
				System.out.println("Poredi sa: " + z.getPreuzimanje() + ", do: " + z.getPovratak());
				if(termin.getZauzetod().isEqual(z.getPreuzimanje()) && termin.getZauzetdo().isEqual(z.getPovratak()) ) {
					termin.setVehicle(null);
					//this.terminRepository.save(termin);
					this.terminRepository.delete(termin);
					System.out.println("Izmenjen termin, vozilo termini je: " + v.getZauzeti().toString());
					return;
				}
			}
	 }
}
