package com.agentApp.app.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.models.Oglas;
import com.agentApp.app.models.TerminZauzeca;
import com.agentApp.app.models.Vozilo;
import com.agentApp.app.repository.TerminZauzecaRepository;

@Service
public class TerminZauzecaService {

	@Autowired
	private TerminZauzecaRepository terminZauzecaRepository;
	@Autowired
	private VoziloService voziloService;
	@Autowired
	private ZahtevService zahtevService;
	@Autowired
	private OglasService oglasService;
	
	public TerminZauzeca save(TerminZauzeca termin) {
		return this.terminZauzecaRepository.save(termin);
	}
	
	public boolean zauzmiTermin(TerminZauzeca termin) {
		System.out.println("USAO U TERMIN ZAUZECA SERVICE Zauzmi Termin");
		
		int imaPodudaranja = this.provjeraZauzetostiVozila(termin);
		if(imaPodudaranja == 0) {
			List<Oglas> oglasiZaVozilo = this.oglasService.findOglasiByVoziloID(termin.getVozilo().getId());
			for(Oglas o : oglasiZaVozilo) {
				boolean ok = this.zahtevService.provjeraZauzeca(termin, o.getId());
				if(ok) {
					this.save(termin);
				}
			}
			return true;
		}
		return false;
	}
	public boolean zauzmiBundleTermin(TerminZauzeca termin, Long bundleID) {
		List<Oglas> oglasiZaVozilo = this.oglasService.findOglasiByVoziloID(termin.getVozilo().getId());
		for(Oglas o : oglasiZaVozilo) {
			boolean ok = this.zahtevService.zauzmiBundle(termin, o.getId(), bundleID);
			if(ok) {
				this.save(termin);
			}
		}
		return true;
	}
	
	public int provjeraZauzetostiVozila(TerminZauzeca termin) {
		System.out.println("Usao u TERMIN ZAUZECA SERVICE - ProvjeraZauzetostiVozila");
		Vozilo v = this.voziloService.getVozilo(termin.getVozilo().getId());
		
		Set<Integer> counter = new HashSet<>();
		int imaPodudaranja = 0;
		
		if(v.getZauzeti().size() < 1) {
			return 0;
		}
		for(TerminZauzeca t : v.getZauzeti()) {
			imaPodudaranja = this.provjeriPodudaranje(t, termin);
			if(imaPodudaranja == 1) {
				System.out.println("Nasao zauzetost");
				counter.add(1);
			}else {
				System.out.println("Nije nasao zauzetost");
			}
		}
		if(counter.contains(1)) {
			return 1;
		}else {
			return 0;
		}
	}
	
	
	public int provjeriPodudaranje(TerminZauzeca zauzeto, TerminZauzeca zahtevPreuzimanje) {
		 
		 System.out.println("Ulazi u if petlju da provjeri podudaranje termina");
		 
		 if( (zauzeto.getZauzetod().isAfter(zahtevPreuzimanje.getZauzetod()) && zauzeto.getZauzetdo().isBefore(zahtevPreuzimanje.getZauzetdo())
					|| (zauzeto.getZauzetod().isBefore(zahtevPreuzimanje.getZauzetod()) && zauzeto.getZauzetdo().isAfter(zahtevPreuzimanje.getZauzetod()))) 
					|| (zauzeto.getZauzetod().isBefore(zahtevPreuzimanje.getZauzetdo()) && zauzeto.getZauzetdo().isAfter(zahtevPreuzimanje.getZauzetdo()))
					|| (zauzeto.getZauzetod().isBefore(zahtevPreuzimanje.getZauzetod()) && zauzeto.getZauzetdo().isAfter(zahtevPreuzimanje.getZauzetdo())) ) {
			 System.out.println("********************************************************");
			 System.out.println("Pronasao je termin podudaranja!!");
			 System.out.println("********************************************************");
			 return 1;		//Nasao podudaranje
			}
		 System.out.println("Nema podudaranja u provjeriPodudaranje!");
		 return 0;
	 }
}
