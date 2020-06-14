package com.zahtev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahtev.model.Zahtev;
import com.zahtev.repository.ZahtevRepository;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	public Zahtev save(Zahtev z) {
		return this.zahtevRepository.save(z);
	}
	public Long getLastGroupID() {
		List<Zahtev> zahtevi = zahtevRepository.findSortedId();
		return (zahtevi.get(0)).getBundle_id();
	}
	public List<Zahtev> getAllZahtevi(){
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		return zahtevi;
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
	
	public void odbijOstaleZahteve(LocalDateTime preuzimanje, LocalDateTime povratak, Long oglasID) {
		List<Zahtev> zahtevi = this.zahtevRepository.findAll();
		
		for(Zahtev z : zahtevi) {
			if(z.getStatus().equals("PENDING") && z.getOglas_id().equals(oglasID) ) {
				if( (preuzimanje.isAfter(z.getPreuzimanje()) && povratak.isBefore(z.getPovratak()) )
					|| (preuzimanje.isBefore(z.getPreuzimanje()) && povratak.isAfter(z.getPreuzimanje()) )
					|| (preuzimanje.isBefore(z.getPovratak()) && povratak.isAfter(z.getPovratak()) ) 
					|| (preuzimanje.isBefore(z.getPreuzimanje()) && povratak.isAfter(z.getPovratak()))
					|| (preuzimanje.isEqual(z.getPreuzimanje()) && povratak.isEqual(z.getPovratak())) ) {
					System.out.println("Usao u izmenu statusa na PENDING!");
					z.setStatus("CANCELED");
					this.zahtevRepository.save(z);
				}
			}
		}
	}
	
	public void odbijOstaleZahteveZaBundle(LocalDateTime preuzimanje, LocalDateTime povratak, Long oglasID, Long bundleID) {
		List<Zahtev> zahtevi = this.zahtevRepository.findAll();
		System.out.println("Usao da odbije ostale bundle zahteve");
		for(Zahtev z : zahtevi) {
			if(z.getStatus().equals("PENDING") && z.getOglas_id().equals(oglasID) && (z.getBundle_id() == bundleID) ) {
//				if( (preuzimanje.isAfter(z.getPreuzimanje()) && povratak.isBefore(z.getPovratak()) )
//					|| (preuzimanje.isBefore(z.getPreuzimanje()) && povratak.isAfter(z.getPreuzimanje()) )
//					|| (preuzimanje.isBefore(z.getPovratak()) && povratak.isAfter(z.getPovratak()) ) 
//					|| (preuzimanje.isBefore(z.getPreuzimanje()) && povratak.isAfter(z.getPovratak()) ) ) {
					System.out.println("Usao u izmenu statusa na PENDING!");
					z.setStatus("CANCELED");
					this.zahtevRepository.save(z);
				//}
			}
		}
	}
}
