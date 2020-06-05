package com.oglas.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oglas.dto.TerminZauzecaZahtevDTO;

@FeignClient(name = "eureka-zahtev-service")
public interface ZahtevConnection {

	@PostMapping("/zauzece")
	boolean zauzece(@RequestBody TerminZauzecaZahtevDTO terminZahtev);
}
