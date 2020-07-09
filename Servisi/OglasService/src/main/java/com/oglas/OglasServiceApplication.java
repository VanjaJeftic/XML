package com.oglas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(scanBasePackages={
"com.oglas", "com.oglas.dto.CenovnikDTO","com.oglas.model","com.oglas.controllers","com.oglas.service"})
@EnableEurekaClient
@EnableFeignClients
public class OglasServiceApplication {

	@RequestMapping("/health")
    public String home() {
        return "Hello world";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(OglasServiceApplication.class, args);
	}

}
