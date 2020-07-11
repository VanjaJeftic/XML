package com.zahtev.endpoint;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.zahtev.connections.OglasConnection;
import com.zahtev.dto.IzvestajDTO;
import com.zahtev.dto.OglasDTO;
import com.zahtev.dto.UserDTO;
import com.zahtev.dto.VoziloDTO;
import com.zahtev.dto.ZahtevDTO;
import com.zahtev.dto.ZahtevViewDTO;
import com.zahtev.service.ZahtevService;
import com.zahtev.soap.BundleRequests;
import com.zahtev.soap.GetAcceptRequest;
import com.zahtev.soap.GetAcceptResponse;
import com.zahtev.soap.GetBundleIDResponse;
import com.zahtev.soap.GetBundleIdRequest;
import com.zahtev.soap.GetIzvestajIDResponse;
import com.zahtev.soap.GetIzvestajIdRequest;
import com.zahtev.soap.GetIzvestajRequest;
import com.zahtev.soap.GetIzvestajResponse;
import com.zahtev.soap.GetZahteviRequest;
import com.zahtev.soap.GetZahteviResponse;
import com.zahtev.soap.Izvestaj;
import com.zahtev.soap.NewIzvestajRequest;
import com.zahtev.soap.NewIzvestajResponse;
import com.zahtev.soap.ObjectFactory;
import com.zahtev.soap.Oglas;
import com.zahtev.soap.User;
import com.zahtev.soap.Vozilo;
import com.zahtev.soap.Zahtev;

@Endpoint
public class ZahtevEndpoint {

	private static final String NAMESPACE_URI = "http://zahtev.com/soap";

	@Autowired
	private ZahtevService zahtevService;
	@Autowired
	private OglasConnection oglasConnection;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBundleIdRequest")
	@ResponsePayload
	public GetBundleIDResponse getBundleIDResponse(@RequestPayload GetBundleIdRequest req) {
		Long groupID = zahtevService.getLastGroupID() + 1;
		
		System.out.println("*****************************************************");
		System.out.println("Group id je: " + groupID);
		System.out.println("*****************************************************");

		GetBundleIDResponse res = new GetBundleIDResponse();
		res.setBundleID(groupID);
		return res;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIzvestajIdRequest")
	@ResponsePayload
	public GetIzvestajIDResponse getLastSavedID(@RequestPayload GetIzvestajIdRequest req) {
		Long id = this.oglasConnection.getSavedID() + 1;
		
		System.out.println("Last saved IzvestajID is: " + id);
		
		GetIzvestajIDResponse res = new GetIzvestajIDResponse();
		res.setIzvestajID(id);
		return res;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getZahteviRequest")
	@ResponsePayload
	public GetZahteviResponse getZahteviResponse(@RequestPayload GetZahteviRequest req) {
		ObjectFactory factory = new ObjectFactory();
		GetZahteviResponse response = factory.createGetZahteviResponse();
		
		Set<ZahtevViewDTO> bundleZahtevi = this.zahtevService.sviBundleZahtevi(req.getId());
		
		for(ZahtevViewDTO zahtevi : bundleZahtevi) {
			
			BundleRequests bundleRequests = factory.createBundleRequests();
			bundleRequests.setBundleID(zahtevi.getBundleID());
			
			for(ZahtevDTO z : zahtevi.getBundleZahtevi()) {
				
				Oglas oglasSOAP = this.createOglasSOAP(z.getOglas(), factory);
				Izvestaj izvestajSOAP;
				if(z.getIzvestaj() != null) {
					izvestajSOAP = this.createIzvestajSOAP(z.getIzvestaj(), factory);
				}else {
					izvestajSOAP = null;
				}
				Zahtev zahtevSOAP = this.createZahtevSOAP(z, oglasSOAP, izvestajSOAP, factory);
				bundleRequests.getZahtev().add(zahtevSOAP);
				User podnosilac = this.createUserSOAP(zahtevi.getUser(), factory);
				bundleRequests.setPodnosilac(podnosilac);
				
			}
			response.getBundleZahtevi().add(bundleRequests);
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAcceptRequest")
	@ResponsePayload
	public GetAcceptResponse getAcceptResponse(@RequestPayload GetAcceptRequest req) {
		GetAcceptResponse res = new GetAcceptResponse();
		boolean ok = this.zahtevService.acceptRequest(req.getId(), req.getUser());
		System.out.println("***********************************************");
		System.out.println("Ulazi u if petlju iz servisa da promjeni stanje");
		if(ok) {
			res.setResponse("ACCEPTED");
			return res;
		}
		res.setResponse("REJECTED");
		return res;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "newIzvestajRequest")
	@ResponsePayload
	public NewIzvestajResponse newIzvestajResponse(@RequestPayload NewIzvestajRequest req) {
		NewIzvestajResponse res = new NewIzvestajResponse();
		
		ResponseEntity<?> response = this.oglasConnection.saveIzvestaj(this.createIzvestajDTO(req.getIzvestaj()));
		if(response.status(HttpStatus.CREATED) != null) {
			System.out.println("Kreira novi izvestaj kao SOAP");
		}else {
			System.out.println("Ups, neka greska");
		}
		
		res.setIzvestajID(1L);
		return res;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIzvestajRequest")
	@ResponsePayload
	public GetIzvestajResponse getIzvestajResponse(@RequestPayload GetIzvestajRequest req) {
		ObjectFactory factory = new ObjectFactory();
		GetIzvestajResponse res = factory.createGetIzvestajResponse();
		
		Set<ZahtevViewDTO> bundleZahtevi = this.zahtevService.sviBundleZahteviIzvestaj(req.getId());
		System.out.println("********************************************************");
		System.out.println("Ispisuje zahteve:");
		for(ZahtevViewDTO zahtev : bundleZahtevi) {
			System.out.println(zahtev);
			BundleRequests bundleRequests = factory.createBundleRequests();
			bundleRequests.setBundleID(zahtev.getBundleID());
			
			for(ZahtevDTO z : zahtev.getBundleZahtevi()) {
				System.out.println("Provjera zahteva: " + z);
				Oglas oglasSOAP = this.createOglasSOAP(z.getOglas(), factory);
				Izvestaj izvestajSOAP = null;
				Zahtev zahtevSOAP;
				if(z.getIzvestaj() != null) {
					System.out.println("Izvestaj je: " + z.getIzvestaj());
					izvestajSOAP = this.createIzvestajSOAP(z.getIzvestaj(), factory);
					System.out.println("Napravio SOAP izvestaj");
					zahtevSOAP = this.createZahtevSOAP(z, oglasSOAP, izvestajSOAP, factory);
				}else {
					izvestajSOAP = null;
					zahtevSOAP = this.createZahtevSOAP(z, oglasSOAP, factory);
				}
				
				bundleRequests.getZahtev().add(zahtevSOAP);
				
				User podnosilac = this.createUserSOAP(zahtev.getUser(), factory);
				bundleRequests.setPodnosilac(podnosilac);
				System.out.println("Zavrsio jednu petlju");
			}
			res.getBundleZahtevi().add(bundleRequests);
			System.out.println("Res: " + res.getBundleZahtevi());
		}
		System.out.println("Velicina: " + res.getBundleZahtevi().size());
		System.out.println("Response je: " + res.getBundleZahtevi());
		return res;
	}
	
	
	
	private Zahtev createZahtevSOAP(ZahtevDTO z ,Oglas o, Izvestaj i, ObjectFactory factory) {
		Zahtev zahtevSOAP = factory.createZahtev();
		zahtevSOAP.setBundleID(z.getBundle_id());
		zahtevSOAP.setId(z.getId());
		zahtevSOAP.setIzvestaj(i);
		zahtevSOAP.setOglas(o);
		zahtevSOAP.setPreuzimanje(z.getPreuzimanje().toString());
		zahtevSOAP.setPovratak(z.getPovratak().toString());
		zahtevSOAP.setStatus(z.getStatus());
		
		return zahtevSOAP;
	}
	private Zahtev createZahtevSOAP(ZahtevDTO z ,Oglas o, ObjectFactory factory) {
		Zahtev zahtevSOAP = factory.createZahtev();
		zahtevSOAP.setBundleID(z.getBundle_id());
		zahtevSOAP.setId(z.getId());
		zahtevSOAP.setOglas(o);
		zahtevSOAP.setPreuzimanje(z.getPreuzimanje().toString());
		zahtevSOAP.setPovratak(z.getPovratak().toString());
		zahtevSOAP.setStatus(z.getStatus());
		
		return zahtevSOAP;
	}
	
	private Oglas createOglasSOAP(OglasDTO o, ObjectFactory factory) {
		Oglas oglasSOAP = factory.createOglas();
		Vozilo voziloSOAP = this.createVoziloSOAP(o.getVozilo(), factory);
		
		oglasSOAP.setId(o.getId());
		oglasSOAP.setSlobodanOd(o.getSlobodanOd().toString());
		oglasSOAP.setSlobodanDo(o.getSlobodanDo().toString());
		oglasSOAP.setMesto(o.getMesto());
		oglasSOAP.setVozilo(voziloSOAP);
		
		return oglasSOAP;
	}
	
	private Vozilo createVoziloSOAP(VoziloDTO v, ObjectFactory factory) {
		Vozilo voziloSOAP = factory.createVozilo();
		
		User userSOAP = this.createUserSOAP(v.getUser(), factory);
		
		voziloSOAP.setId(v.getId());
		voziloSOAP.setKlasaVozila(v.getKlasaVozila());
		voziloSOAP.setMarkaVozila(v.getMarkaVozila());
		voziloSOAP.setModelVozila(v.getModelVozila());
		voziloSOAP.setVrstaMenjaca(v.getVrstaMenjaca());
		voziloSOAP.setTipGoriva(v.getTipGoriva());
		voziloSOAP.setBrsedistadeca(v.getBrsedistadeca());
		voziloSOAP.setPredjeniKM(v.getPredjeniKm());
		voziloSOAP.setUser(userSOAP);
		
		return voziloSOAP;
	}
	
	private User createUserSOAP(UserDTO u, ObjectFactory factory) {
		User userSOAP = factory.createUser();
		userSOAP.setId(u.getId());
		userSOAP.setUsername(u.getUsername());
		userSOAP.setFirstname(u.getIme());
		userSOAP.setEmail(u.getEmail());
		
		return userSOAP;
	}
	
	private Izvestaj createIzvestajSOAP(IzvestajDTO i, ObjectFactory factory) {
		Izvestaj izvestajSOAP = factory.createIzvestaj();
		
		izvestajSOAP.setPredjeniKm(i.getPredjeniKm());
		System.out.println("Postavio km : " + i.getPredjeniKm());
		izvestajSOAP.setKomentar(i.getKomentar());
		System.out.println("Postavio kom : " + i.getKomentar());
		izvestajSOAP.setVozilo(i.getVozilo());
		System.out.println("Postavio vozilo : " + i.getVozilo());
		izvestajSOAP.setZahtev(i.getZahtev());
		System.out.println("Postavio zahtev: " + i.getZahtev());
		
		return izvestajSOAP;
	}
	
	private IzvestajDTO createIzvestajDTO(Izvestaj i) {
		IzvestajDTO newI = new IzvestajDTO();
		newI.setPredjeniKm(i.getPredjeniKm());
		newI.setKomentar(i.getKomentar());
		newI.setVozilo(i.getVozilo());
		newI.setZahtev(i.getZahtev());
		return newI;
	}
}
