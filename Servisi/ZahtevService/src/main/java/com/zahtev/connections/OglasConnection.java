package com.zahtev.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zahtev.dto.IzvestajDTO;
import com.zahtev.dto.OglasDTO;
import com.zahtev.dto.TerminZauzecaDTO;

@FeignClient(name = "eureka-oglas-service")
public interface OglasConnection {

	@GetMapping("/verify/{oglas_id}")
	boolean verify(@PathVariable("oglas_id") Long oglas_id);
	
	@GetMapping("/{id}")
	OglasDTO getOneOglas(@PathVariable("id") Long id);
	
	@PostMapping("/termin/zauzece")
	int provjeraZauzetostiVozila(@RequestBody TerminZauzecaDTO terminZauzimanjaDTO);
	
	@PostMapping("/termin")
	ResponseEntity<?> zauzece(@RequestBody TerminZauzecaDTO terminZauzimanjaDTO);
	
	@GetMapping("izvestaj/{id}/{zahtev}")
	IzvestajDTO getIzvestaj(@PathVariable("id") Long id, @PathVariable("zahtev") Long zahtev);
}
