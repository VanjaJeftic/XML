package com.agentApp.app.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agentApp.app.dto.UserDTO;
import com.agentApp.app.dto.VoziloDTO;
import com.agentApp.app.models.ImageModel;
import com.agentApp.app.models.User;
import com.agentApp.app.models.Vozilo;
import com.agentApp.app.repository.ImageModelRepository;
import com.agentApp.app.repository.VoziloRepository;
import com.agentApp.app.services.UserService;
import com.agentApp.app.services.VoziloService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/vozilo")
public class VoziloController {

	 @Autowired
	    private VoziloService voziloService;

	protected final static Logger logger = LoggerFactory.getLogger(VoziloController.class);

	    @Autowired
	    private ImageModelRepository imageModelRepository;

	    @Autowired
	    private VoziloRepository voziloRepository;
	    
		@Autowired
		private UserService userService;
		
	    @PostMapping
	    @PreAuthorize("hasAuthority('create_vozilo')")
	    public BodyBuilder uplaodImage(@RequestParam("vozilomarka") String markaVozila,
	                                   @RequestParam("image") MultipartFile file,
	                                   @RequestParam("vozilomodel") String modelVozila,
	                                   @RequestParam("voziloklasa") String klasaVozila,
	                                   @RequestParam("vozilomenjac") String vrstaMenjaca,
	                                   @RequestParam("vozilogorivo") String tipGoriva,
	                                   @RequestParam("vozilokm") String predjeniKm,
	                                   @RequestParam("vozilosedista") String brsedistadeca, Principal p
	                                 
	                                   ) throws IOException {
	        VoziloDTO ovDTO=new VoziloDTO();
	        System.out.println("Username"+p.getName());
	        ovDTO.setBrsedistadeca(brsedistadeca);
	        ovDTO.setKlasaVozila(klasaVozila);
	        ovDTO.setMarkaVozila(markaVozila);
	        ovDTO.setModelVozila(modelVozila);
	        ovDTO.setVrstaMenjaca(vrstaMenjaca);
	        ovDTO.setPredjeniKm(predjeniKm);
	        ovDTO.setTipGoriva(tipGoriva);
	        User u=userService.findByUsername(p.getName());
	        UserDTO udto=new UserDTO(u);
	        ovDTO.setUser(udto);
	        
	        
	       
	        
	        Vozilo vozilo=this.voziloService.createVozilo(ovDTO);
	        logger.info("uspesno je kreirano vozilo");
	        System.out.println("Original Image Byte Size - " + file.getBytes().length);

	        //System.out.println("vozilo - " + ovDTO.getKlasaVozila());
	        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
	                compressBytes(file.getBytes()),vozilo.getId()); //kreirana slika

	        imageModelRepository.save(img);
	        logger.info("Uspesno je sacuvana slika");
	        return ResponseEntity.status(HttpStatus.OK);
	    }
	  

	    public static byte[] compressBytes(byte[] data) {
	        Deflater deflater = new Deflater();
	        deflater.setInput(data);
	        deflater.finish();

	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	        byte[] buffer = new byte[1024];

	        while (!deflater.finished()) {
	            int count = deflater.deflate(buffer);
	            outputStream.write(buffer, 0, count);
	        }
	        try {
	            outputStream.close();
	        } catch (IOException e) {
	        }
	        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
	        return outputStream.toByteArray();
	    }
	        // uncompress the image bytes before returning it to the angular application
	    public static byte[] decompressBytes(byte[] data) {
	        Inflater inflater = new Inflater();
	        inflater.setInput(data);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	        byte[] buffer = new byte[1024];
	        try {
	            while (!inflater.finished()) {
	                int count = inflater.inflate(buffer);
	                outputStream.write(buffer, 0, count);
	            }
	            outputStream.close();
	        } catch (IOException ioe) {
	        } catch (DataFormatException e) {
	        }
	        return outputStream.toByteArray();
	    }
	    
	    @GetMapping("/vozila")
	    List<Vozilo> mojaVozila(Principal p){
	    	User user=userService.findByUsername(p.getName());
			logger.info("Uspesno je pronadjen korisnik koji je kreirao vozilo");
	    	return voziloService.getVozilaBezOglasa(user);
	    	
	    }
	    
	    @GetMapping
	    public ResponseEntity<?> vozila(Principal p){
	    	User user=userService.findByUsername(p.getName());
	    	return new ResponseEntity<List<Vozilo>>(voziloService.getVozila(user), HttpStatus.OK);
	    		    	
	    }
	    
//	    @GetMapping
//	    public ResponseEntity<?> vozila(Principal p){
//	    	User user=userService.findByUsername(p.getName());
//	    	return new ResponseEntity<List<Vozilo>>(voziloService.getVozilaForAgent(user.getId()), HttpStatus.OK);
//	    	
//	    }
	    
	    @DeleteMapping("/{id}")
		public ResponseEntity<HttpStatus> deleteVozilo(@PathVariable("id") Long id){
			
			try {
				voziloService.delete(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
		}
	    
	    @PutMapping
		public ResponseEntity<?> updateVozilo(@RequestBody VoziloDTO voziloDTO){
			
			Optional<Vozilo> vozilo=voziloRepository.findById(voziloDTO.getId());
			
			if(vozilo.isPresent()) {
				this.voziloService.updateVozilo(voziloDTO);
				
				return new ResponseEntity<>("Succesful updated cenovnik",HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
}
