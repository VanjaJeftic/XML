package com.oglas.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oglas.dto.TerminZauzecaZahtevDTO;
import com.oglas.dto.ZahtevDTO;

@FeignClient(name = "eureka-zahtev-service")
public interface ZahtevConnection {

	@PostMapping("/zauzece")
	boolean zauzece(@RequestBody TerminZauzecaZahtevDTO terminZahtev);
	
	@GetMapping("/zahtev/{id}")
	ZahtevDTO getOneZahtev(@PathVariable("id") Long id);
}
