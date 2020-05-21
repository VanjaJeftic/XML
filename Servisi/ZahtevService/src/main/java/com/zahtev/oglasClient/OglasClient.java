package com.zahtev.oglasClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-oglas-service")
public interface OglasClient {

	@GetMapping("/hello-worlds")
	String helloWorld();
}
