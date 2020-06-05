package com.search.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.search.dto.UserDTO;


@FeignClient(name = "eureka-authorization-service")
public interface UserConnection {
	@GetMapping("/{id}")
	UserDTO getUser(@PathVariable("id") Long id);
}
