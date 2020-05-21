package com.agent_vehicle.AgentVehicleService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AgentVehicleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentVehicleServiceApplication.class, args);
	}

}
