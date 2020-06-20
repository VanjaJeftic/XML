package com.zahtev.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapWebServiceConfig {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean
	public XsdSchema zahtevSchema() {
		return new SimpleXsdSchema(new ClassPathResource("zahtev.xsd"));
	}

	@Bean(name = "zahtev")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema zahtevSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setSchema(zahtevSchema);
		definition.setLocationUri("/ws");
		definition.setPortTypeName("ZahtevServicePort");
		definition.setTargetNamespace("http://zahtev.com/soap");

		return definition;
	}
}
