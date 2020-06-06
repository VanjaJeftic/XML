package com.oglas.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oglas.model.Oglas;

@FeignClient(name = "eureka-zahtev-service")
public interface SearchConnection {
	@PostMapping("/search/create")
	Oglas createSearch(@RequestBody Oglas oglas);
}
