package com.oglas.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oglas.dto.OglasDTO;
import com.oglas.model.Oglas;

@FeignClient(name = "eureka-search-service")
public interface SearchConnection {
	@PostMapping("/search/create")
	OglasDTO create(@RequestBody OglasDTO oglas);
}

