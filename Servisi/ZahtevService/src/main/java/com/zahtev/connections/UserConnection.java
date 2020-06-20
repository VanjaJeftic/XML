package com.zahtev.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zahtev.dto.UserDTO;

@FeignClient(name = "eureka-authorization-service")
public interface UserConnection {

	@GetMapping("korisnik/{id}")
    UserDTO getUser(@PathVariable("id") Long id);
}
