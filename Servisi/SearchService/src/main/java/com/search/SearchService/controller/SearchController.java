package com.search.SearchService.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	protected final static Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private VoziloConnection voziloConnection;

	@Autowired
	private UserConnection userConnection;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Search>> allOglasi(){
		List<Search> oglasi = this.searchService.getAllSearch();
		logger.info("Lista oglasa");
		return new ResponseEntity<List<Search>>(oglasi, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<OglasDTO> create(@RequestBody OglasDTO oglas) {
		//System.out.println(oglas.getSlobodanOd().toString() + " IDDD \n\n\n\n\n");
		VoziloDTO vozilo = voziloConnection.getOneForSearch(oglas.getVozilo_id());
		//UserDTO user = this.userConnection.getUser(oglas.getUser_id());
		Search search = this.searchService.createSearch(new Search(oglas,vozilo));
		if(search!=null) {
			return new ResponseEntity<OglasDTO>(oglas, HttpStatus.OK);
		}
		return new ResponseEntity<OglasDTO>(oglas, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/userById")
	//public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
	public ResponseEntity<UserDTO> getUserById(@RequestBody Long id){
		UserDTO user =new UserDTO();
		user =this.userConnection.getUser(id);
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	@PostMapping("/getSearched")
	public ResponseEntity<List<Search>> search(@RequestBody SearchDTO search) {
		List<Search> pretrazeniOglasi = new ArrayList<>();
		if(!isNullOrEmpty(search.getMesto()) && !isNullOrEmpty(search.getDatumi()) ) {
			List<Search> sviOglasi = searchService.getAllSearch();
			for(Search s : sviOglasi) {
				if(isMestoValid(s,search.getMesto()) && isDatumValid(s,search.getDatumi()) && isMarkaValid(s,search.getMarka()) 
						&& isModelValid(s,search.getModel()) && isCenaValid(s,search.getMinimalnaCena(),search.getMaksimalnaCena()) 
						&& isPredjeniKilometriValid(s,search.getPredjeniKilometri()) && isPlaniraniKilometriValid(s, search.getPlaniraniKilometri())
						&& isBrSedistaValid(s,search.getBrSedistaZaDecu()) && isTipMenjacaValid(s,search.getTipMenjaca()) 
						&& isVrstaGorivaValid(s,search.getVrstaGoriva()) && isKlasaValid(s,search.getKlasa()) && isCdwValid(s,search.getCdw())) {
					pretrazeniOglasi.add(s);
				}
				
			}
			return new ResponseEntity<>(pretrazeniOglasi, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(pretrazeniOglasi, HttpStatus.BAD_REQUEST);
		
	}
	
	private boolean isKlasaValid(Search s, String klasa) {
		if(!isNullOrEmpty(klasa)) {
        if(s.getKlasaVozila().toLowerCase().equals(klasa.toLowerCase()))
            return true;
        return false;
		}
		return true;
    }
	
	private boolean isVrstaGorivaValid(Search s, String vrstaGoriva) {
		if(!isNullOrEmpty(vrstaGoriva)) {
        if(s.getTipGoriva().toLowerCase().equals(vrstaGoriva.toLowerCase()))
            return true;
        return false;
		}
		return true;
    }
	
	private boolean isBrSedistaValid(Search s, int brSedista) {
		if(brSedista != 0) {
			if(Integer.parseInt(s.getBrSedistaDeca()) == brSedista) {
				return true;
			}
			return false;
		}
		return true;
	}
	private boolean isPlaniraniKilometriValid(Search s, int planiraniKilometri) {
		if(planiraniKilometri != 0) {
			if(Integer.parseInt(s.getPlaniraniKm()) <= planiraniKilometri) {
				return true;
			}
			return false;
		}
		return true;
	}
	private boolean isPredjeniKilometriValid(Search s, int predjeniKilometri) {
		if(predjeniKilometri != 0) {
			if(Integer.parseInt(s.getPredjeniKm()) <= predjeniKilometri) {
				return true;
			}
			return false;
		}
		return true;
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
	
	private boolean isCdwValid(Search s, String cdw) {
		if(!isNullOrEmpty(cdw)) {
        if(s.getCdw().toLowerCase().equals(cdw.toLowerCase()))
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
				if(s.getCena()<=Double.parseDouble(maxCena))
					return true;
				return false;
			}else if(!isNullOrEmpty(minCena)) {
				if(s.getCena()>=Double.parseDouble(minCena))
					return true;
				return false;
			}
		}
		return true;
    }
	
	private boolean isTipMenjacaValid(Search s, String menjac) {
		if(!isNullOrEmpty(menjac)) {
        if(s.getVrstaMenjaca().toLowerCase().equals(menjac.toLowerCase())) {
            return true;
        }
        return false;
		}
		return true;
    }
	
	private boolean isDatumValid(Search s, String datum) {
		if(!isNullOrEmpty(datum)) {
			String[] datumi = datum.split(",",2);
			System.out.println(datumi[0] + "\n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss zZ (zzzz)");
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
