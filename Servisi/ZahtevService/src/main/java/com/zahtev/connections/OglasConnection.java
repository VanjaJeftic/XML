package com.zahtev.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zahtev.dto.OglasDTO;

@FeignClient(name = "eureka-oglas-service")
public interface OglasConnection {

	@GetMapping("/verify/{oglas_id}")
	boolean verify(@PathVariable("oglas_id") Long oglas_id);
	
	@GetMapping("/{id}")
	OglasDTO getOneOglas(@PathVariable("id") Long id);
}
