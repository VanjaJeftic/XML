package com.search.SearchService.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.SearchService.connections.UserConnection;
import com.search.SearchService.connections.VoziloConnection;
import com.search.SearchService.dto.OglasDTO;
import com.search.SearchService.dto.SearchDTO;
import com.search.SearchService.dto.UserDTO;
import com.search.SearchService.dto.VoziloDTO;
import com.search.SearchService.model.Search;
import com.search.SearchService.service.SearchService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private VoziloConnection voziloConnection;

	@Autowired
	private UserConnection userConnection;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Search>> allOglasi(){
		List<Search> oglasi = this.searchService.getAllSearch();
		return new ResponseEntity<List<Search>>(oglasi, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<OglasDTO> create(@RequestBody OglasDTO oglas) {
		VoziloDTO vozilo = voziloConnection.getVoziloById(oglas.getVozilo_id());
		UserDTO user = this.userConnection.getUser(oglas.getUser_id());
		Search search = this.searchService.createSearch(new Search(oglas,vozilo,user));
		if(search!=null) {
			return new ResponseEntity<OglasDTO>(oglas, HttpStatus.OK);
		}
		return new ResponseEntity<OglasDTO>(oglas, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/getSearched")
	public ResponseEntity<List<Search>> search(@RequestBody SearchDTO search) {
		System.out.println('\n'+search.getMarka() + '\n');
		System.out.println('\n'+search.getMarka() + '\n');
		System.out.println('\n'+search.getMarka() + '\n');
		
		List<Search> pretrazeniOglasi = new ArrayList<>();
		List<Search> sviOglasi = searchService.getAllSearch();
		for(Search s : sviOglasi) {
			if(isMestoValid(s,search.getMesto()) && isDatumValid(s,search.getDatumi()) && isMarkaValid(s,search.getMarka()) && isModelValid(s,search.getModel()) && isCenaValid(s,search.getMinimalnaCena(),search.getMaksimalnaCena())) {
				pretrazeniOglasi.add(s);
			}
			
		}
		return new ResponseEntity<>(pretrazeniOglasi, HttpStatus.OK);
	}
	
	private boolean isMestoValid(Search s, String mesto) {
		if(!isNullOrEmpty(mesto)) {
        if(s.getMesto().toLowerCase().contains(mesto.toLowerCase()))
            return true;
        return false;
		}
		return true;
    }
	
	private boolean isModelValid(Search s , String model) {
		if(!isNullOrEmpty(model)) {
	        if(s.getModelVozila().contains(model))
	            return true;
	        return false;
			}
			return true;
    }
	
	private boolean isMarkaValid(Search s, String marka) {
		if(!isNullOrEmpty(marka)) {
	        if(s.getMarkaVozila().contains(marka))
	            return true;
	        return false;
			}
			return true;
    }
	
	private boolean isCenaValid(Search s, String minCena, String maxCena) {
		if(!isNullOrEmpty(maxCena) && !isNullOrEmpty(minCena)) {
	        if(s.getCena() >Double.parseDouble(minCena) && s.getCena() <Double.parseDouble(maxCena))
	            return true;
	        return false;
		}
		else if(!isNullOrEmpty(maxCena) || !isNullOrEmpty(minCena)) {
			if(!isNullOrEmpty(maxCena)) {
				if(s.getCena()<Double.parseDouble(maxCena))
					return true;
				return false;
			}else if(!isNullOrEmpty(maxCena)) {
				if(s.getCena()>Double.parseDouble(minCena))
					return true;
				return false;
			}
		}
		return true;
    }
	
	private boolean isDatumValid(Search s, String datum) {
		if(!isNullOrEmpty(datum)) {
			String[] datumi = datum.split(" ~ ",2);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
			LocalDateTime datumOd = LocalDateTime.parse(datumi[0], formatter);
			LocalDateTime datumDo = LocalDateTime.parse(datumi[1], formatter);
	        if(s.getSlobodanOd().isBefore(datumOd) && s.getSlobodanDo().isAfter(datumDo))
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
