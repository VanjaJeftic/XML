package com.zahtev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zahtev.connections.OglasConnection;
import com.zahtev.connections.UserConnection;
import com.zahtev.dto.IzvestajDTO;
import com.zahtev.dto.OglasDTO;
import com.zahtev.dto.ShopCartItemsDTO;
import com.zahtev.dto.TerminZauzecaDTO;
import com.zahtev.dto.TerminZauzecaZahtevDTO;
import com.zahtev.dto.UserDTO;
import com.zahtev.dto.ZahtevDTO;
import com.zahtev.dto.ZahtevViewDTO;
import com.zahtev.model.Zahtev;
import com.zahtev.repository.ZahtevRepository;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;
	@Autowired
	private OglasConnection oglasConnection;
	@Autowired
	private UserConnection userConnetcion;
	
	public Zahtev save(Zahtev z) {
		return this.zahtevRepository.save(z);
	}
	
	//Dobavlja poslednji upisani BundleID
	public Long getLastGroupID() {
		List<Zahtev> zahtevi = zahtevRepository.findSortedId();
		return (zahtevi.get(0)).getBundle_id();
	}
	
	public List<Zahtev> getAllZahtevi(){
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		return zahtevi;
	}
	
	public ZahtevDTO getOneZahtev(Long id) {
		List<Zahtev> zahtevi = this.getAllZahtevi();
		for(Zahtev z : zahtevi) {
			if(z.getId().equals(id)) {
				return new ZahtevDTO(z);
			}
		}
		return null;
	}
	
	//Dobavlja sve BundleID's
	public Set<Long> getAllGroupIDs(){
		Set<Long> ids = new HashSet<>();
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		
		for(Zahtev z : zahtevi) {
			ids.add(z.getBundle_id());
		}
		return ids;
	}
	
	public List<Zahtev> getPendingZahtevi(){
		List<Zahtev> zahtevi = zahtevRepository.findAll();
		List<Zahtev> pending = new ArrayList<>();
		for(Zahtev z : zahtevi) {
			if(z.getStatus().equals("PENDING")) {
				pending.add(z);
			}
		}
		return pending;
	}
	
	//Dobavlja sve zahteve koji imaju isti BundleID
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
		System.out.println("Pocinje provjeda da odbije ostale bundle zahteve");
		for(Zahtev z : zahtevi) {
			if(z.getStatus().equals("PENDING") && z.getOglas_id().equals(oglasID) ) {
				if( (preuzimanje.isAfter(z.getPreuzimanje()) && povratak.isBefore(z.getPovratak()) )
					|| (preuzimanje.isBefore(z.getPreuzimanje()) && povratak.isAfter(z.getPreuzimanje()) )
					|| (preuzimanje.isBefore(z.getPovratak()) && povratak.isAfter(z.getPovratak()) ) 
					|| (preuzimanje.isBefore(z.getPreuzimanje()) && povratak.isAfter(z.getPovratak()))
					|| (preuzimanje.isEqual(z.getPreuzimanje()) && povratak.isEqual(z.getPovratak())) ) {
					
					System.out.println("Prepravio jedan zahtev na CANCELED");
					z.setStatus("CANCELED");
					this.zahtevRepository.save(z);
					
					if(z.isBundle()) {
						this.odbijOstaleZahteveZaBundle(oglasID, z.getBundle_id());
					}
				}
			}
		}
	}
	
	public void odbijOstaleZahteveZaBundle(Long oglasID, Long bundleID) {
		List<Zahtev> zahtevi = this.zahtevRepository.findAll();
		System.out.println("Usao da odbije ostale bundle zahteve");
		for(Zahtev z : zahtevi) {
			if(z.getStatus().equals("PENDING") && z.getOglas_id().equals(oglasID) && (z.getBundle_id() == bundleID) ) {
					System.out.println("Prepavio jedan bundle zahtev na CANCELED");
					z.setStatus("CANCELED");
					this.zahtevRepository.save(z);
			}
		}
	}
	
	public Set<ZahtevViewDTO> sviBundleZahtevi(Long agent){
		Set<ZahtevViewDTO> bundleZahtevi = new HashSet<>();
		
		Set<Long> ids = this.getAllGroupIDs();
		
		this.odbijStareZahteve();
		
		//Grupise sve bundle zahteve
		for(Long id : ids) {
			List<Zahtev> zahteviGrouped = this.getAllByGroupID(id);
			
			ZahtevViewDTO zvdto = new ZahtevViewDTO();
			if(zahteviGrouped != null) {
				if(zahteviGrouped.size() > 0) {
					UserDTO userDTO = this.userConnetcion.getUser(zahteviGrouped.get(0).getPodnosilac_id());
					zvdto.setUser(userDTO);
				}
			}
			
			for(Zahtev z : zahteviGrouped) {
				zvdto.setBundleID(z.getBundle_id());
				OglasDTO oglas = this.oglasConnection.getOneOglas(z.getOglas_id());
				
				if(oglas.getVozilo().getUser().getId().equals(agent)) {
					zvdto.getBundleZahtevi().add(new ZahtevDTO(z, oglas));
					bundleZahtevi.add(zvdto);
				}
			}
		}
		return bundleZahtevi;
	}
	
	public boolean zauzece(TerminZauzecaZahtevDTO terminZahtev) {
		List<Zahtev> zahtevi = this.getAllZahtevi();
		if(zahtevi == null || zahtevi.size() < 1) {
			System.out.println("Broj zahteva: " + zahtevi.size());
			return false;
		}
		
		LocalDateTime preuzimanje = LocalDateTime.of(terminZahtev.getPreuzimanje().getYear(), terminZahtev.getPreuzimanje().getMonthValue(), terminZahtev.getPreuzimanje().getDayOfMonth(), terminZahtev.getPreuzimanje().getHour(), terminZahtev.getPreuzimanje().getMinute());
		LocalDateTime povratak = LocalDateTime.of(terminZahtev.getPovratak().getYear(), terminZahtev.getPovratak().getMonthValue(), terminZahtev.getPovratak().getDayOfMonth(), terminZahtev.getPovratak().getHour(), terminZahtev.getPovratak().getMinute());
		
		for(Long id : terminZahtev.getOglasi()) {
			for(Zahtev z : zahtevi) {
				if(z.getOglas_id() == id && (z.getStatus().equals("PENDING")) ) {
					LocalDateTime zahtevPreuzimanje = LocalDateTime.of(z.getPreuzimanje().getYear(), z.getPreuzimanje().getMonthValue(), z.getPreuzimanje().getDayOfMonth(), z.getPreuzimanje().getHour(), z.getPreuzimanje().getMinute());
					LocalDateTime zahtevPovratak = LocalDateTime.of(z.getPovratak().getYear(), z.getPovratak().getMonthValue(), z.getPovratak().getDayOfMonth(), z.getPovratak().getHour(), z.getPovratak().getMinute());
					
					
					if( (preuzimanje.isAfter(zahtevPreuzimanje) && povratak.isBefore(zahtevPovratak)
							|| (preuzimanje.isBefore(zahtevPreuzimanje) && povratak.isAfter(zahtevPreuzimanje))) 
							|| (preuzimanje.isBefore(zahtevPovratak) && povratak.isAfter(zahtevPovratak))
							|| (preuzimanje.isBefore(zahtevPreuzimanje) && povratak.isAfter(zahtevPovratak))
							|| (preuzimanje.isEqual(zahtevPreuzimanje) && povratak.isEqual(zahtevPovratak)) ) {
					
						z.setStatus("CANCELED");
						this.save(z);
						
						if(z.isBundle()) {
							
							this.odbijOstaleZahteveZaBundle(id, z.getBundle_id());
						}
					}
				}
			}
		}
		return true;
	}
	
	public Set<ZahtevViewDTO> sviBundleZahteviIzvestaj(Long agent){
		
		Set<ZahtevViewDTO> bundleZahtevi = new HashSet<>();
		
		Set<Long> ids = this.getAllGroupIDs();
		
		//Grupise sve bundle zahteve
		for(Long id : ids) {
			List<Zahtev> zahteviGrouped = this.getAllByAcceptedGroupID(id);
			
			ZahtevViewDTO zvdto = new ZahtevViewDTO();
			
			if(zahteviGrouped != null) {
				if(zahteviGrouped.size() > 0) {
					UserDTO userDTO = this.userConnetcion.getUser(zahteviGrouped.get(0).getPodnosilac_id());
					zvdto.setUser(userDTO);
				}
			}
			
			for(Zahtev z : zahteviGrouped) {
				zvdto.setBundleID(z.getBundle_id());
				OglasDTO oglas = this.oglasConnection.getOneOglas(z.getOglas_id());
				
				if(oglas.getVozilo().getUser().getId().equals(agent)) {
					IzvestajDTO izvestaj = this.oglasConnection.getIzvestaj(oglas.getVozilo().getId(), z.getId());
					if(izvestaj == null) {
						zvdto.getBundleZahtevi().add(new ZahtevDTO(z, oglas));
						bundleZahtevi.add(zvdto);
					}else {
						zvdto.getBundleZahtevi().add(new ZahtevDTO(z, oglas, izvestaj));
						bundleZahtevi.add(zvdto);
					}
				}
			}
		}
		return bundleZahtevi;
	}
	
	public boolean create(ShopCartItemsDTO listaZahteva) {
		Set<Long> vlasnici = new HashSet<>();
		Set<ZahtevDTO> forBundle = new HashSet<>();
		Long groupID = this.getLastGroupID() + 1;
		Long podnosilac = listaZahteva.getPodnosilac();
		
		
		for(ZahtevDTO z : listaZahteva.getZahtevi()) {
			vlasnici.add(z.getOglas().getVozilo().getUser().getId());
		}

		for(ZahtevDTO z : listaZahteva.getZahtevi()) {
			boolean postoji = oglasConnection.verify(z.getOglas().getId());
			if(postoji) {
				Zahtev newZahtev = new Zahtev(z);
				newZahtev.setKreiran(LocalDateTime.now());
				newZahtev.setPodnosilac_id(podnosilac);
				newZahtev.setStatus("PENDING");
				if(z.isBundle()) {
					forBundle.add(z);
				}else {
					System.out.println("Nije bundle zahtev, id: " + z.getOglas().getId());
					newZahtev.setBundle_id(groupID);
					this.save(newZahtev);
				}
			}
			groupID++;
		}
		//BUNDLE Zahtevi
		for(Long vlasnik : vlasnici) {
			for(ZahtevDTO zahtev : forBundle) {
				if(zahtev.getOglas().getVozilo().getUser().getId().equals(vlasnik)) {
					Zahtev newZahtev = new Zahtev(zahtev);
					newZahtev.setKreiran(LocalDateTime.now());
					newZahtev.setBundle_id(groupID);
					newZahtev.setStatus("PENDING");
					newZahtev.setPodnosilac_id(podnosilac);
					this.save(newZahtev);
				}
			}
			groupID++;
		}
		return true;
	}
	
	public boolean acceptRequest(Long id, Long agent) {
		System.out.println("Usao u acceptRequest servisa da izmeni stanje zahteva");
		List<Zahtev> zahtevi = this.getAllByGroupID(id);
		
		if(zahtevi.get(0).isBundle()) {
			for(Zahtev z : zahtevi) {
				
				//Pronalazi sve zahteve koji su kreirani za oglas kome je AgentID "agent" i menja status
				OglasDTO oglas = this.oglasConnection.getOneOglas(z.getOglas_id());
				
				TerminZauzecaDTO terminZauzecaDTO = new TerminZauzecaDTO(oglas.getVozilo().getId(), z.getPreuzimanje(), z.getPovratak());
				
				int imaPodudaranja = this.oglasConnection.provjeraZauzetostiVozila(terminZauzecaDTO);
				
				if(oglas.getVozilo().getUser().getId().equals(agent)) {
					if(imaPodudaranja == 0) {
						System.out.println("Izmenio stanje zahteva na accepted");
						z.setStatus("ACCEPTED");
						
						//Kreira termin zauzeca iz ovog zahteva i povezuje ga sa vozilom u OglasService
						ResponseEntity<?> res = this.oglasConnection.zauzece(terminZauzecaDTO);
						
						if(res.status(HttpStatus.CREATED) != null) {
							this.save(z);
							
							//Odbija sve ostale zahteve vezane za taj oglas ovog agenta
							this.odbijOstaleZahteve(z.getPreuzimanje(), z.getPovratak(), z.getOglas_id());
						}else {
							return false;
						}
						
					}else {
						return false;
					}
				}
			}
		}else {
			System.out.println("Nije bundle! ZahtevController");
			Zahtev z = zahtevi.get(0);
			System.out.println("Zahtev: " + z.getBundle_id() + ", od:" + z.getPreuzimanje() + ", do:" +z.getPovratak() + ", podn" + z.getPodnosilac_id());
			//Pronalazi sve zahteve koji su kreirani za oglas kome je AgentID "agent" i menja status
			if(zahtevi.size() > 0) {
				System.out.println("Size: " + zahtevi.size());
			}
			OglasDTO oglas = this.oglasConnection.getOneOglas(z.getOglas_id());
			
			TerminZauzecaDTO terminZauzecaDTO = new TerminZauzecaDTO(oglas.getVozilo().getId(), z.getPreuzimanje(), z.getPovratak());
			int imaPodudaranja = this.oglasConnection.provjeraZauzetostiVozila(terminZauzecaDTO);
			
			if(oglas.getVozilo().getUser().getId().equals(agent)) {
				if(imaPodudaranja == 0) {
					System.out.println("Nema podudaranja! ZahtevController");
					z.setStatus("ACCEPTED");
					//Kreira termin zauzeca iz ovog zahteva i povezuje ga sa vozilom u OglasService
					ResponseEntity<?> res = this.oglasConnection.zauzece(terminZauzecaDTO);
					
					if(res.status(HttpStatus.CREATED) != null) {
						this.save(z);
						System.out.println("Izmenio status na ACCEPTED u ZahtevService");
						//Odbija sve ostale zahteve vezane za taj oglas ovog agenta
						this.odbijOstaleZahteve(z.getPreuzimanje(), z.getPovratak(), z.getOglas_id());
					}else {
						System.out.println("Nije sacuvao zahtev kao ACCEPTED");
						return false;
					}
					
				}else {
					System.out.println("Ima podudaranja! ZahtevController");
					return false;
				}
			}
		}
		
		
		
		
		return true;
	}
	
	private void odbijStareZahteve() {
		List<Zahtev> zahtevi = this.getPendingZahtevi();
		LocalDateTime now = LocalDateTime.now();
		
		for(Zahtev z : zahtevi) {
			if(z.getKreiran().isBefore( (now.minusDays(2)) )) {
				z.setStatus("CANCELED");
				this.zahtevRepository.save(z);
				if(z.isBundle()) {
					this.odbijOstaleZahteveZaBundle(z.getOglas_id(), z.getBundle_id());
				}
			}
		}
	}
	
	public Set<ZahtevViewDTO> sviBundleZahteviPoruke(Long agent){
		
		Set<ZahtevViewDTO> bundleZahtevi = new HashSet<>();
		
		Set<Long> ids = this.getAllGroupIDs();
		
		//Grupise sve bundle zahteve
		for(Long id : ids) {
			List<Zahtev> zahteviGrouped = this.getAllByAcceptedGroupID(id);
			
			ZahtevViewDTO zvdto = new ZahtevViewDTO();
			
			if(zahteviGrouped != null) {
				if(zahteviGrouped.size() > 0) {
					UserDTO userDTO = this.userConnetcion.getUser(zahteviGrouped.get(0).getPodnosilac_id());
					zvdto.setUser(userDTO);
				}
			}
			
			for(Zahtev z : zahteviGrouped) {
				zvdto.setBundleID(z.getBundle_id());
				OglasDTO oglas = this.oglasConnection.getOneOglas(z.getOglas_id());
				
				if(oglas.getVozilo().getUser().getId().equals(agent)) {
					
						zvdto.getBundleZahtevi().add(new ZahtevDTO(z, oglas));
						bundleZahtevi.add(zvdto);
					
				}
			}
		}
		return bundleZahtevi;
	}
	
}