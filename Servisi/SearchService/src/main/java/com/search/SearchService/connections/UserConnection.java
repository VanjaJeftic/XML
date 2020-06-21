package com.search.SearchService.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.search.SearchService.dto.UserDTO;


@FeignClient(name = "eureka-authorization-service")
public interface UserConnection {
	@GetMapping("korisnik/{id}")
    UserDTO getUser(@PathVariable("id") Long id);
}
