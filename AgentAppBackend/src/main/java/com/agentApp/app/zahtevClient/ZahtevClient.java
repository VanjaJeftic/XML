package com.agentApp.app.zahtevClient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.agentApp.app.dto.IzvestajDTO;
import com.agentApp.app.soap.GetAcceptRequest;
import com.agentApp.app.soap.GetAcceptResponse;
import com.agentApp.app.soap.GetBundleIDResponse;
import com.agentApp.app.soap.GetBundleIdRequest;
import com.agentApp.app.soap.GetIzvestajIDResponse;
import com.agentApp.app.soap.GetIzvestajIdRequest;
import com.agentApp.app.soap.GetIzvestajRequest;
import com.agentApp.app.soap.GetIzvestajResponse;
import com.agentApp.app.soap.GetZahteviRequest;
import com.agentApp.app.soap.GetZahteviResponse;
import com.agentApp.app.soap.Izvestaj;
import com.agentApp.app.soap.NewIzvestajRequest;
import com.agentApp.app.soap.NewIzvestajResponse;
import com.agentApp.app.soap.ObjectFactory;

public class ZahtevClient extends WebServiceGatewaySupport {

	public GetBundleIDResponse getBundleIDResponse(Long id) {
		GetBundleIdRequest req = new GetBundleIdRequest();
		req.setId(id);
		GetBundleIDResponse res = (GetBundleIDResponse) getWebServiceTemplate().marshalSendAndReceive(req);
		return res;
	}
	
	public GetZahteviResponse getZahteviRequest(Long id) {
		GetZahteviRequest req = new GetZahteviRequest();
		req.setId(id);
		GetZahteviResponse res = (GetZahteviResponse) getWebServiceTemplate().marshalSendAndReceive(req);
		return res;
	}
	
	public GetAcceptResponse getAcceptResponse(Long id, Long user) {
		GetAcceptRequest req = new GetAcceptRequest();
		req.setId(id);
		req.setUser(user);
		GetAcceptResponse res = (GetAcceptResponse) getWebServiceTemplate().marshalSendAndReceive(req);
		System.out.println("******************************************************");
		System.out.println("Dobio je odgovor: " + res.getResponse());
		return res;
	}
	
	public GetIzvestajIDResponse getIzvestajIDResponse(Long id) {
		GetIzvestajIdRequest req = new GetIzvestajIdRequest();
		req.setId(id);
		
		GetIzvestajIDResponse res = (GetIzvestajIDResponse) getWebServiceTemplate().marshalSendAndReceive(req);
		return res;
	}
	
	public GetIzvestajResponse getIzvestajiResponse(Long agent) {
		GetIzvestajRequest req = new GetIzvestajRequest();
		req.setId(agent);
		
		GetIzvestajResponse res = (GetIzvestajResponse) getWebServiceTemplate().marshalSendAndReceive(req);
		return res;
	}
	
	public NewIzvestajResponse newIzvestajResponse(IzvestajDTO i) {
		NewIzvestajRequest req = new NewIzvestajRequest();
		req.setIzvestaj(this.createIzvestajSOAP(i));
		
		NewIzvestajResponse res = (NewIzvestajResponse) getWebServiceTemplate().marshalSendAndReceive(req);
		return res;
	}
	
	private Izvestaj createIzvestajSOAP(IzvestajDTO i) {
		ObjectFactory factory = new ObjectFactory();
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
}
