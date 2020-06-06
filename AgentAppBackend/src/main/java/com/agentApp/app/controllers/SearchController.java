package com.agentApp.app.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentApp.app.dto.SearchDTO;
import com.agentApp.app.models.Oglas;
import com.agentApp.app.models.Search;
import com.agentApp.app.services.OglasService;
import com.agentApp.app.services.SearchService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Autowired 
	private OglasService oglasService;
	
	@PostMapping
	public ResponseEntity<?> search(@RequestBody SearchDTO search) {
		List<Oglas> pretrazeniOglasi = new ArrayList<>();
		List<Oglas> sviOglasi = searchService.getAllOglasi();
		for(Oglas o : sviOglasi) {
			if(isMestoValid(o,search) && isDatumValid(o,search) && isMarkaValid(o,search) && isModelValid(o,search) && isCenaValid(o,search)) {
				pretrazeniOglasi.add(o);
			}
			
		}
		return new ResponseEntity<>(pretrazeniOglasi, HttpStatus.OK);
	}
	
	private boolean isMestoValid(Oglas o , SearchDTO search) {
		if(!isNullOrEmpty(search.getMesto())) {
        if(o.getMesto().toLowerCase().contains(search.getMesto().toLowerCase()))
            return true;
        return false;
		}
		return true;
    }
	
	private boolean isModelValid(Oglas o , SearchDTO search) {
		if(!isNullOrEmpty(search.getModel())) {
	        if(o.getVozilo().getModelVozila().getNaziv().contains(search.getModel()))
	            return true;
	        return false;
			}
			return true;
    }
	
	private boolean isMarkaValid(Oglas o , SearchDTO search) {
		if(!isNullOrEmpty(search.getMarka())) {
	        if(o.getVozilo().getMarkaVozila().getNaziv().contains(search.getMarka()))
	            return true;
	        return false;
			}
			return true;
    }
	
	private boolean isCenaValid(Oglas o , SearchDTO search) {
		if(!isNullOrEmpty(search.getMaksimalnaCena()) && !isNullOrEmpty(search.getMinimalnaCena())) {
	        if(o.getCena() >Double.parseDouble(search.getMinimalnaCena()) && o.getCena() <Double.parseDouble(search.getMaksimalnaCena()))
	            return true;
	        return false;
		}
		else if(!isNullOrEmpty(search.getMaksimalnaCena()) || !isNullOrEmpty(search.getMinimalnaCena())) {
			if(!isNullOrEmpty(search.getMaksimalnaCena())) {
				if(o.getCena()<Double.parseDouble(search.getMaksimalnaCena()))
					return true;
				return false;
			}else if(!isNullOrEmpty(search.getMinimalnaCena())) {
				if(o.getCena()>Double.parseDouble(search.getMinimalnaCena()))
					return true;
				return false;
			}
		}
		return true;
    }
	
	private boolean isDatumValid(Oglas o ,SearchDTO search) {
		if(!isNullOrEmpty(search.getDatumi())) {
			String[] datumi = search.getDatumi().split(" ~ ",2);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
			LocalDateTime datumOd = LocalDateTime.parse(datumi[0], formatter);
			LocalDateTime datumDo = LocalDateTime.parse(datumi[1], formatter);
	        if(o.getSlobodanOd().isBefore(datumOd) && o.getSlobodanDo().isAfter(datumDo))
	            return true;
	        return false;
			}
			return true;
    }
	
	private boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
	
	
}
