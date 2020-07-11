package com.agentApp.app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentApp.app.dto.IzvestajDTO;
import com.agentApp.app.dto.OglasDTO;
import com.agentApp.app.dto.UserDTO;
import com.agentApp.app.dto.VoziloDTO;
import com.agentApp.app.dto.ZahtevBundleDTO;
import com.agentApp.app.dto.ZahtevDTO;
import com.agentApp.app.models.Izvestaj;
import com.agentApp.app.models.Vozilo;
import com.agentApp.app.models.Zahtev;
import com.agentApp.app.repository.IzvestajRepository;
import com.agentApp.app.soap.BundleRequests;
import com.agentApp.app.soap.GetIzvestajResponse;
import com.agentApp.app.soap.GetZahteviResponse;
import com.agentApp.app.soap.Oglas;
import com.agentApp.app.soap.User;
import com.agentApp.app.zahtevClient.ZahtevClient;

@Service
public class IzvestajService {

	@Autowired
	private IzvestajRepository izvestajRepository;
	@Autowired
	private VoziloService voziloService;
	@Autowired
	private ZahtevService zahtevService;
	@Autowired
	private TerminZauzecaService terminService;
	@Autowired
	private ZahtevClient zahtevClient;
	
	public Izvestaj getOneIzvestaj(Long zahtevID, Long voziloID) {
		List<Izvestaj> izvestaji = this.izvestajRepository.findAll();
		
		for(Izvestaj i : izvestaji) {
			if(i.getZahtev() == null) {
				System.out.println("Izvestaj je null");
				//return null;
			}else {
				System.out.println("Ulazi u if petlju izvestaj Servisa:");
				if(i.getZahtev().getId().equals(zahtevID) && i.getVozilo().getId().equals(voziloID)) {
					System.out.println("Izvestaj u If je: " + i.getPredjeniKm() + ", ostalo: " + i);
					return i;
				}
			}
		}
		return null;
	}
	
	public Zahtev getOneIzvestaj(Long id) {
		List<Izvestaj> izvestaji = this.izvestajRepository.findAll();
		for(Izvestaj i : izvestaji) {
			if(i.getZahtev().getId().equals(id)) {
				return i.getZahtev();
			}
		}
		return null;
	}
	
	public void ukloniTerminZauzeca(Izvestaj i) {
		Vozilo v = i.getVozilo();
		Zahtev z = i.getZahtev();
		this.terminService.obrisiTermin(v, z);
	}
	
	public Izvestaj save(Izvestaj i) {
		return this.izvestajRepository.save(i);
	}
	
	public Izvestaj sacuvajIzvestaj(IzvestajDTO izvestajDTO) {
		Vozilo v = this.voziloService.getVozilo(izvestajDTO.getVozilo());
		Zahtev z = this.zahtevService.getOneZahtev(izvestajDTO.getZahtev());
		
		Izvestaj newIzvestaj = new Izvestaj(izvestajDTO, v, z);
		
		return this.save(newIzvestaj);
	}
	
	public Set<ZahtevBundleDTO> getIzvestajiFromServisi(Long agent){
		GetIzvestajResponse res = this.zahtevClient.getIzvestajiResponse(agent);
		return this.getZahteviBundleIzvestajDTOServisi(res);
	}
	
	private Set<ZahtevBundleDTO> getZahteviBundleIzvestajDTOServisi(GetIzvestajResponse res){
		Set<ZahtevBundleDTO> zahtevi = new HashSet<>();
		for(BundleRequests br : res.getBundleZahtevi()) {
			ZahtevBundleDTO zahtevBundleDTO = new ZahtevBundleDTO();
			zahtevBundleDTO.setBundleID(br.getBundleID());
			UserDTO podnosilac = this.createUserDTOfromSOAP(br.getPodnosilac());
			zahtevBundleDTO.setUser(podnosilac);
			
			for(com.agentApp.app.soap.Zahtev z : br.getZahtev()) {
				ZahtevDTO newZahtev = this.createZahtevDTOfromSOAP(z);
				zahtevBundleDTO.getBundleZahtevi().add(newZahtev);
			}
			zahtevi.add(zahtevBundleDTO);
		}
		return zahtevi;
	}
	
	public void sendIzvestajNaServis(IzvestajDTO i) {
		this.zahtevClient.newIzvestajResponse(i);
	}
	
	public boolean isVoziloHere(Long agent, Long id) {
		List<Vozilo> vozila = this.voziloService.getVozilaForAgent(agent);
		for(Vozilo v : vozila) {
			if(v.getIdVoziloServis() != null) {
				if(v.getIdVoziloServis().equals(id)) {
					return true;
				}
			}
		}
		return false;
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
	private OglasDTO createOglasDTOfromSOAP(com.agentApp.app.soap.Oglas o) {
		OglasDTO newOglas = new OglasDTO();
		newOglas.setId(o.getId());
		newOglas.setSlobodanod(this.getDateTime(o.getSlobodanOd()));
		newOglas.setSlobodando(this.getDateTime(o.getSlobodanDo()));
		newOglas.setMesto(o.getMesto());
		VoziloDTO newVozilo = this.createVoziloDTOfromSOAP(o.getVozilo());
		newOglas.setVozilo(newVozilo);
		return newOglas;
	}
	private VoziloDTO createVoziloDTOfromSOAP(com.agentApp.app.soap.Vozilo v) {
		VoziloDTO newVozilo = new VoziloDTO();
		newVozilo.setId(v.getId());
		newVozilo.setKlasaVozila(v.getKlasaVozila());
		newVozilo.setMarkaVozila(v.getMarkaVozila());
		newVozilo.setModelVozila(v.getModelVozila());
		newVozilo.setVrstaMenjaca(v.getVrstaMenjaca());
		newVozilo.setTipGoriva(v.getTipGoriva());
		newVozilo.setPredjeniKm(v.getPredjeniKM());
		newVozilo.setBrsedistadeca(v.getBrsedistadeca());
		newVozilo.setIdVoziloServis(v.getId());
		UserDTO  newUser = this.createUserDTOfromSOAP(v.getUser());
		newVozilo.setUser(newUser);
		return newVozilo;
	}
	private UserDTO createUserDTOfromSOAP(com.agentApp.app.soap.User u) {
		UserDTO newUser = new UserDTO();
		newUser.setId(u.getId());
		newUser.setUsername(u.getUsername());
		newUser.setFirstname(u.getFirstname());
		newUser.setEmail(u.getEmail());
		return newUser;
	}
	
	private IzvestajDTO createIzvestajDTOfromSOAP(com.agentApp.app.soap.Izvestaj i) {
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
}
