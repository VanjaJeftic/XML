package com.agentApp.app.zahtevClient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.agentApp.app.soap.GetAcceptRequest;
import com.agentApp.app.soap.GetAcceptResponse;
import com.agentApp.app.soap.GetBundleIDResponse;
import com.agentApp.app.soap.GetBundleIdRequest;
import com.agentApp.app.soap.GetZahteviRequest;
import com.agentApp.app.soap.GetZahteviResponse;

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
}
