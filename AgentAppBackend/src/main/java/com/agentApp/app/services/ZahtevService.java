package com.agentApp.app.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.models.TerminZauzeca;
import com.agentApp.app.models.Zahtev;
import com.agentApp.app.repository.ZahtevRepository;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	public List<Zahtev> getAllZahtevi(){
		return zahtevRepository.findAll();
	}
	
	public Zahtev saveZahtev(Zahtev z) {
		return zahtevRepository.save(z);
	}
	
	public Zahtev getOneZahtev(Long id) {
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		for(Zahtev z : zahtevi) {
			if(z.getId().equals(id)) {
				return z;
			}
		}
		return null;
	}
	
	public Long getLastGroupID() {
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		int size = zahtevi.size() - 1;
		return (zahtevi.get(size)).getBundle_id();
	}
	
	public Set<Long> getAllGroupIDs(){
		Set<Long> ids = new HashSet<>();
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		
		for(Zahtev z : zahtevi) {
			ids.add(z.getBundle_id());
		}
		return ids;
	}
	
	public List<Zahtev> getAllByGroupID(Long id){
		List<Zahtev> zahteviGroup = new ArrayList<Zahtev>();
		
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		
		if(zahtevi == null)
			return null;
		
		for(Zahtev zahtev : zahtevi) {
			if(zahtev.getBundle_id().equals(id) && zahtev.getStatus().equals("PENDING")) {
				zahteviGroup.add(zahtev);
			}
		}
		return zahteviGroup;
	}
	
	//Prihvaceni zahtevi
		public List<Zahtev> getAllByAcceptedGroupID(Long id){
			List<Zahtev> zahteviGroup = new ArrayList<Zahtev>();
			
			List<Zahtev> zahtevi = zahtevRepository.findAll();
			
			if(zahtevi == null)
				return null;
			
			for(Zahtev zahtev : zahtevi) {
				if(zahtev.getBundle_id().equals(id) && zahtev.getStatus().equals("ACCEPTED")) {
					zahteviGroup.add(zahtev);
				}
			}
			return zahteviGroup;
		}
	
	//Odbija sve ostale zahteve
	public boolean provjeraZauzeca(TerminZauzeca termin, Long oglasID) {
		System.out.println("Usao u ZahtevService - provjeraZauzeca");
		List<Zahtev> zahtevi = this.getAllZahtevi();
		if(zahtevi == null || zahtevi.size() < 1) {
			System.out.println("Broj zahteva: " + zahtevi.size());
			return false;
		}
		for(Zahtev z : zahtevi) {
			if(z.getOglas().getId() == oglasID && (z.getStatus().equals("PENDING"))) {
				
				if( (termin.getZauzetod().isAfter(z.getPreuzimanje()) && termin.getZauzetod().isBefore(z.getPovratak())
						|| (termin.getZauzetod().isBefore(z.getPreuzimanje()) && termin.getZauzetod().isAfter(z.getPreuzimanje()))) 
						|| (termin.getZauzetod().isBefore(z.getPovratak()) && termin.getZauzetod().isAfter(z.getPovratak()))
						|| (termin.getZauzetod().isBefore(z.getPreuzimanje()) && termin.getZauzetod().isAfter(z.getPovratak())) ) {
					System.out.println("Usao u izmenu statusa!");
					z.setStatus("CANCELED");
					this.saveZahtev(z);
					//return true;
				}
			}
		}
		return true;
	}
	
	public boolean zauzmiBundle(TerminZauzeca termin, Long oglasID, Long zahtevBundleID) {
		System.out.println("Usao u ZahtevService - provjeraZauzeca");
		List<Zahtev> zahtevi = this.getAllZahtevi();
		if(zahtevi == null || zahtevi.size() < 1) {
			System.out.println("Broj zahteva: " + zahtevi.size());
			return false;
		}
		for(Zahtev z : zahtevi) {
			if(z.getOglas().getId() == oglasID && (z.getStatus().equals("PENDING")) && (z.getBundle_id() != zahtevBundleID)) {
				
				if( (termin.getZauzetod().isAfter(z.getPreuzimanje()) && termin.getZauzetod().isBefore(z.getPovratak())
						|| (termin.getZauzetod().isBefore(z.getPreuzimanje()) && termin.getZauzetod().isAfter(z.getPreuzimanje()))) 
						|| (termin.getZauzetod().isBefore(z.getPovratak()) && termin.getZauzetod().isAfter(z.getPovratak()))
						|| (termin.getZauzetod().isBefore(z.getPreuzimanje()) && termin.getZauzetod().isAfter(z.getPovratak())) ) {
					System.out.println("Usao u izmenu statusa!");
					z.setStatus("CANCELED");
					this.saveZahtev(z);

					if(z.isBundle()) {
						this.odbijOstaleBundleZahteve(oglasID, z.getBundle_id());
					}
				}
			}
		}
		return true;
	}
	
	private void odbijOstaleBundleZahteve(Long oglasID, Long zahtevBundleID) {
		List<Zahtev> zahtevi = this.getAllZahtevi();
		for(Zahtev z : zahtevi) {
			if(z.getOglas().getId() == oglasID && (z.getStatus().equals("PENDING")) && (z.getBundle_id() != zahtevBundleID)) {
				z.setStatus("CANCELED");
				this.saveZahtev(z);
			}
		}
	}
}
