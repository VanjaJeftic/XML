package com.zahtev.endpoint;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
import com.zahtev.soap.GetZahteviRequest;
import com.zahtev.soap.GetZahteviResponse;
import com.zahtev.soap.Izvestaj;
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
		izvestajSOAP.setKomentar(i.getKomentar());
		izvestajSOAP.setVozilo(i.getVozilo());
		izvestajSOAP.setZahtev(i.getZahtev());
		
		return izvestajSOAP;
	}
}
