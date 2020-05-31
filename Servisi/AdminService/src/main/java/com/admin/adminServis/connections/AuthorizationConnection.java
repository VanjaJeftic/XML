package com.admin.adminServis.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "eureka-authorization-service")
public interface AuthorizationConnection {

    @PostMapping("/verify/{id_usera}")
    boolean verify(@PathVariable("id_usera") Long userId);

    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable("id") Long id);

    @GetMapping("/blokirajUsera/{id}")
    String blokirajUsera(@PathVariable("id") Long id);

    @GetMapping("/odblokirajUsera/{id}")
    String odblokirajUsera(@PathVariable("id") Long id);

}
