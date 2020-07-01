package com.oglas.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oglas.dto.UserViewDTO;

@FeignClient(name = "eureka-authorization-service") //samo proveriti da li se ovo vozilo odnosi na naziv u bazi za Vozilo service
public interface UserConnection {

    @GetMapping("korisnik/verify/{user_id}")
    boolean verify(@PathVariable("user_id") Long user_id);
    
    @GetMapping("korisnik/{id}")
    UserViewDTO getUser(@PathVariable("id") Long id);

    @GetMapping("korisnik/role/{id}")
	String getUserRole(@PathVariable("id") Long id);

}
