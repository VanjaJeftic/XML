package com.agentApp.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.agentApp.app.zahtevClient.ZahtevClient;

@Configuration
public class ZahtevClientConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.agentApp.app.soap");
		return marshaller;
	}
	
	@Bean
	public ZahtevClient zahtevClient(Jaxb2Marshaller marshaller) {
		ZahtevClient client = new ZahtevClient();
		client.setDefaultUri("http://localhost:8093/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
