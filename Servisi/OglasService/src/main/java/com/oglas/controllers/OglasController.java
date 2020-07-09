
package com.oglas.controllers;

import com.oglas.connections.SearchConnection;
import com.oglas.connections.UserConnection;
import com.oglas.dto.OglasDTO;
import com.oglas.dto.OglasViewDTO;
import com.oglas.dto.OglasVoziloDTO;
import com.oglas.dto.UserViewDTO;
import com.oglas.dto.VoziloDTO;
import com.oglas.dto.VoziloViewDTO;
import com.oglas.model.Oglas;
import com.oglas.model.Vozilo;
import com.oglas.repository.OglasRepository;
import com.oglas.repository.VoziloRepository;
import com.oglas.service.OglasService;
import com.oglas.service.VoziloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

public class OglasController {

	@GetMapping("/hello-worlds")
	public String helloWorld() {
		return "Hello World ";
	}

	protected final static Logger logger = LoggerFactory.getLogger(IzvestajController.class);

	@Autowired
	private OglasService oglasService;
	@Autowired
	private VoziloService voziloService;
	@Autowired
	private UserConnection userConnection;
	@Autowired
	private OglasRepository oglasRepository;
	@Autowired
	private VoziloRepository voziloRepository;
	@Autowired
	private SearchConnection searchConnection;


	@Autowired
	public OglasController(OglasService oglasService,VoziloService voziloService, UserConnection userConnection){
		this.oglasService=oglasService;
		this.voziloService=voziloService;
		this.userConnection = userConnection;
	}
	
	@GetMapping
	public List<Oglas> allOglasi(){
		List<Oglas> oglasi = this.oglasService.allOglasi();
		return oglasi;
	}
	
	@GetMapping("/{id}")
	public OglasViewDTO getOneOglas(@PathVariable("id") Long id ) {
		Oglas o = oglasService.getOneOglas(id);
		UserViewDTO userVlasnikOglasa = this.userConnection.getUser(o.getUser_id());
		
		Vozilo v = voziloService.getVozilo(o.getVozilo_id());
		VoziloViewDTO vozilo = new VoziloViewDTO(v);
		vozilo.setUser(userVlasnikOglasa);
		OglasViewDTO oglas = new OglasViewDTO(o, vozilo);
		return oglas;
	}
	

	@PostMapping("/create")
	//@PreAuthorize("hasAuthority('create_oglas')")
	public ResponseEntity<?> create(@RequestBody OglasDTO ovDTO) {

		String u=this.userConnection.getUserRole(ovDTO.getUser_id());
		
		 if(u.equals("ROLE_user")) {
		        
	        	System.out.println("Ko kreira "+u);
	        	
	        	int brAuta=oglasService.brOglasaKorisnika(ovDTO.getUser_id());
	        	if(brAuta>3) {
	        		System.out.println("Greska");
	        		return new ResponseEntity<>(null, HttpStatus.CONFLICT);
	        		
	        	}
	        }
		
		Oglas oglas = this.oglasService.createOrder(ovDTO);
		logger.info("Kreiran order");
		//Oglas search = this.searchConnection.createSearch(new Oglas(ovDTO));

		return new ResponseEntity<>(oglas, HttpStatus.OK);
	}

	@PutMapping("/oglas")
	//@PreAuthorize("hasAuthority('update_oglas')")
	public ResponseEntity<?> update(@RequestBody OglasDTO oglasDTO) {
		Optional<Oglas> oglasdata = oglasRepository.findById(oglasDTO.getId());
		if(oglasdata.isPresent()){
			this.oglasService.update(oglasDTO);
			logger.info("Uspesno je azuriran oglas");
			return new ResponseEntity<>("Successful updated oglas", HttpStatus.OK);
		}else{
			logger.info("Oglas nije pronadjen");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}


	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_operator')")
	//@PreAuthorize("hasAuthority('delete_oglas')")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		try {
			oglasService.delete(id);
			logger.info("Oglas je obrisan");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/oglas/vozilo/{id}")
	public ResponseEntity<List<Oglas>> getOglasByVoziloId(@PathVariable("id") Long id){
		List<Oglas> oglasi = oglasService.findOglasiByVoziloID(id);
		return new ResponseEntity<List<Oglas>>(oglasi, HttpStatus.OK);
	}
	
	@GetMapping("/verify/{oglas_id}")
	public boolean verify(@PathVariable("oglas_id") Long oglas_id){
		return oglasService.verify(oglas_id);
//		if(postoji)
//			return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/oglasi/{userId}")
	List<Oglas> getOglasiMoji(@PathVariable("userId") Long user_id){
		
		System.out.println("Moji oglasi");
		List<Oglas> svi=this.oglasService.findMojiOglas(user_id);
		return svi;
	}
	

}
