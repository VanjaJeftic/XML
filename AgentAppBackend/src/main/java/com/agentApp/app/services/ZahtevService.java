package com.agentApp.app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agentApp.app.dto.IzvestajDTO;
import com.agentApp.app.dto.OglasDTO;
import com.agentApp.app.dto.UserDTO;
import com.agentApp.app.dto.VoziloDTO;
import com.agentApp.app.dto.ZahtevBundleDTO;
import com.agentApp.app.dto.ZahtevDTO;
import com.agentApp.app.models.TerminZauzeca;
import com.agentApp.app.models.Zahtev;
import com.agentApp.app.repository.ZahtevRepository;
import com.agentApp.app.soap.BundleRequests;
import com.agentApp.app.soap.GetAcceptResponse;
import com.agentApp.app.soap.GetBundleIDResponse;
import com.agentApp.app.soap.GetZahteviRequest;
import com.agentApp.app.soap.GetZahteviResponse;
import com.agentApp.app.soap.Izvestaj;
import com.agentApp.app.soap.Oglas;
import com.agentApp.app.soap.User;
import com.agentApp.app.soap.Vozilo;
import com.agentApp.app.zahtevClient.ZahtevClient;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private OglasService oglasService;
	@Autowired
	private TerminZauzecaService terminService;
	@Autowired
	private ZahtevClient zahtevClient;
	
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
	
	public Long getLastGroupID(Long id) {
		GetBundleIDResponse res = this.zahtevClient.getBundleIDResponse(id);
		
		return res.getBundleID();
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
	
	public Set<ZahtevBundleDTO> getZahteviFromServisi(Long id){
		GetZahteviResponse res = this.zahtevClient.getZahteviRequest(id);
		return this.getZahteviBundleDTOServisi(res);
		
	}
	
	private Set<ZahtevBundleDTO> getZahteviBundleDTOServisi(GetZahteviResponse res){
		Set<ZahtevBundleDTO> zahtevi = new HashSet<>();
		for(BundleRequests br : res.getBundleZahtevi()) {
			ZahtevBundleDTO zahtevBundleDTO = new ZahtevBundleDTO();
			zahtevBundleDTO.setBundleID(br.getBundleID());
			
			for(com.agentApp.app.soap.Zahtev z : br.getZahtev()) {
				ZahtevDTO newZahtev = this.createZahtevDTOfromSOAP(z);
				zahtevBundleDTO.getBundleZahtevi().add(newZahtev);
			}
			zahtevi.add(zahtevBundleDTO);
		}
		return zahtevi;
	}
	private ZahtevDTO createZahtevDTOfromSOAP(com.agentApp.app.soap.Zahtev z) {
		ZahtevDTO zahtevDTO = new ZahtevDTO();
		zahtevDTO.setBundle_id(z.getBundleID());
		zahtevDTO.setId(z.getId());
		zahtevDTO.setPreuzimanje(this.getDateTime(z.getPreuzimanje()));
		zahtevDTO.setPovratak(this.getDateTime(z.getPovratak()));
		zahtevDTO.setStatus(z.getStatus());
		if(z.getIzvestaj() != null) {
			IzvestajDTO newIzvestaj = this.createIzvestajDTOfromSOAP(z.getIzvestaj());
			zahtevDTO.setIzvestaj(newIzvestaj);
		}
		OglasDTO newOglas = this.createOglasDTOfromSOAP(z.getOglas());
		zahtevDTO.setOglas(newOglas);
		
		return zahtevDTO;
	}
	private OglasDTO createOglasDTOfromSOAP(Oglas o) {
		OglasDTO newOglas = new OglasDTO();
		newOglas.setId(o.getId());
		newOglas.setMesto(o.getMesto());
		VoziloDTO newVozilo = this.createVoziloDTOfromSOAP(o.getVozilo());
		newOglas.setVozilo(newVozilo);
		return newOglas;
	}
	private VoziloDTO createVoziloDTOfromSOAP(Vozilo v) {
		VoziloDTO newVozilo = new VoziloDTO();
		newVozilo.setId(v.getId());
		newVozilo.setKlasaVozila(v.getKlasaVozila());
		newVozilo.setMarkaVozila(v.getMarkaVozila());
		newVozilo.setModelVozila(v.getModelVozila());
		newVozilo.setVrstaMenjaca(v.getVrstaMenjaca());
		newVozilo.setTipGoriva(v.getTipGoriva());
		newVozilo.setPredjeniKm(v.getPredjeniKM());
		newVozilo.setBrsedistadeca(v.getBrsedistadeca());
		UserDTO  newUser = this.createUserDTOfromSOAP(v.getUser());
		newVozilo.setUser(newUser);
		return newVozilo;
	}
	private UserDTO createUserDTOfromSOAP(User u) {
		UserDTO newUser = new UserDTO();
		newUser.setId(u.getId());
		newUser.setUsername(u.getUsername());
		newUser.setFirstname(u.getFirstname());
		newUser.setEmail(u.getEmail());
		return newUser;
	}
	
	private IzvestajDTO createIzvestajDTOfromSOAP(Izvestaj i) {
		IzvestajDTO newIzvestaj = new IzvestajDTO();
		newIzvestaj.setKomentar(i.getKomentar());
		newIzvestaj.setPredjeniKm(i.getPredjeniKm());
		newIzvestaj.setVozilo(i.getVozilo());
		newIzvestaj.setZahtev(i.getZahtev());
		return newIzvestaj;
	}
	
	private LocalDateTime getDateTime(String dateTime) {
		System.out.println("Datum je: " + dateTime);
		String[] split = dateTime.split("T");
		String[] date = split[0].split("-");
		System.out.println("Date je " + date );
		String godina = date[0];
		System.out.println("Godina je: " + godina);
		String month = date[1];
		System.out.println("Mesec je: " + month);
		String dan = date[2];
		System.out.println("Dan je: " + dan);
		String[] time = split[1].split(":");
		String hour = time[0];
		String minute = time[1];
		System.out.println("Mesec u int je: " + Integer.parseInt(month));
		
		LocalDate newDate = LocalDate.of(Integer.parseInt(godina), Integer.parseInt(month), Integer.parseInt(dan));
		LocalTime newTime = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
		LocalDateTime newDateTime = LocalDateTime.of(newDate, newTime);
		return newDateTime;
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
						|| (termin.getZauzetod().isBefore(z.getPreuzimanje()) && termin.getZauzetod().isAfter(z.getPovratak()))
						|| (termin.getZauzetod().isEqual(z.getPreuzimanje()) && termin.getZauzetod().isEqual(z.getPovratak())) ) {
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
						|| (termin.getZauzetod().isBefore(z.getPreuzimanje()) && termin.getZauzetod().isAfter(z.getPovratak()))
						|| (termin.getZauzetod().isEqual(z.getPreuzimanje()) && termin.getZauzetod().isEqual(z.getPovratak())) ) {
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
	
	public boolean acceptRequest(Long id, com.agentApp.app.models.User u) {
		boolean ok = this.checkLocalBundleID(id);
		if(ok) {
			Set<Integer> counter = new HashSet<>();
			List<Zahtev> zahtevi = this.getAllByGroupID(id);
			
			if(zahtevi.get(0).isBundle()) {
				for(Zahtev z : zahtevi) {
					com.agentApp.app.models.Oglas o = this.oglasService.findOneOglas(z.getOglas().getId());
					TerminZauzeca newTermin = new TerminZauzeca();
					newTermin.setVozilo(o.getVozilo());
					newTermin.setZauzetod(z.getPreuzimanje());
					newTermin.setZauzetdo(z.getPovratak());
					
					int imaPodudaranja = this.terminService.provjeraZauzetostiVozila(newTermin);
					if(imaPodudaranja == 1) {
						counter.add(1);
					}
				}
				if(!counter.contains(1)) {
					for(Zahtev z : zahtevi) {
						com.agentApp.app.models.Oglas o = this.oglasService.findOneOglas(z.getOglas().getId());
						TerminZauzeca newTermin = new TerminZauzeca();
						newTermin.setVozilo(o.getVozilo());
						newTermin.setZauzetod(z.getPreuzimanje());
						newTermin.setZauzetdo(z.getPovratak());
						
						if(o.getVozilo().getUser().getId().equals(u.getId())) {
							z.setStatus("ACCEPTED");
							boolean oke = this.terminService.zauzmiBundleTermin(newTermin, z.getBundle_id());
							if(oke) {
								this.saveZahtev(z);
							}else {
								return false;
							}
						}else {
							return false;
						}
					}
				}else {
					return false;
				}
			}else {
				Zahtev z = zahtevi.get(0);
				com.agentApp.app.models.Oglas o = this.oglasService.findOneOglas(z.getOglas().getId());
				TerminZauzeca newTermin = new TerminZauzeca();
				newTermin.setVozilo(o.getVozilo());
				newTermin.setZauzetod(z.getPreuzimanje());
				newTermin.setZauzetdo(z.getPovratak());
				
				int imaPodudaranja = this.terminService.provjeraZauzetostiVozila(newTermin);
				if(imaPodudaranja == 0) {
					if(o.getVozilo().getUser().getId().equals(u.getId())) {
						z.setStatus("ACCEPTED");
						boolean oke = this.terminService.zauzmiTermin(newTermin);
						if(oke) {
							this.saveZahtev(z);
						}else {
							return false;
						}
					}
				}else {
					return false;
				}
			}
		
			return true;
		}else {
			GetAcceptResponse res = this.zahtevClient.getAcceptResponse(id, u.getId());
			if(res.getResponse().equals("ACCEPTED")) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	private boolean checkLocalBundleID(Long id) {
		Set<Long> groupIDs = this.getAllGroupIDs();
		for(Long groupID : groupIDs) {
			if(groupID.equals(id)) {
				return true;
			}
		}
		return false;
	}

}
