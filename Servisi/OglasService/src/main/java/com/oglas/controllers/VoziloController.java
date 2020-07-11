package com.oglas.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oglas.connections.UserConnection;
import com.oglas.dto.OglasDTO;
import com.oglas.dto.OglasVoziloDTO;
import com.oglas.dto.UserViewDTO;
import com.oglas.dto.VoziloDTO;
import com.oglas.dto.VoziloViewDTO;
import com.oglas.model.ImageModel;
import com.oglas.model.Oglas;
import com.oglas.model.Vozilo;
import com.oglas.repository.ImageModelRepository;
import com.oglas.repository.OglasRepository;
import com.oglas.repository.VoziloRepository;
import com.oglas.service.VoziloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/vozilo")
public class VoziloController {

    protected final static Logger logger = LoggerFactory.getLogger(VoziloController.class);

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private ImageModelRepository imageModelRepository;

    @Autowired
    private VoziloRepository voziloRepository;
    
    @Autowired 
    private OglasRepository oglasRepo;
    
    @Autowired
    private UserConnection userConnection;
    
    @GetMapping("agent/{id}")	//id - Ulogovani Agent
    public List<VoziloViewDTO> allVozila(@PathVariable("id") Long agent){	
    	List<VoziloViewDTO> agentskaVozila = new ArrayList<>();
    	UserViewDTO user = this.userConnection.getUser(agent);
		
    	List<Vozilo> vozila = voziloService.getVozila(user.getId());
    	for(Vozilo v : vozila) {
    		VoziloViewDTO agentskoVozilo = new VoziloViewDTO(v);
    		agentskoVozilo.setUser(user);
    		agentskaVozila.add(agentskoVozilo);
    	}
    	return agentskaVozila;
    }
    @GetMapping("/{id}")
    public Vozilo getVoziloById(@PathVariable("id") Long id){
    	Vozilo v = voziloService.getVozilo(id);
		return v;
    }
    
    @GetMapping("/search/getOne/{id}")
    public VoziloDTO getOneForSearch(@PathVariable("id") Long id){
    	System.out.println("VOZILO FUNKCIJA \n\n\n\n\n");
    	VoziloDTO v = new VoziloDTO(voziloService.getVozilo(id));
		return v;
    }

    @PostMapping("/novoVozilo")
    public BodyBuilder sacuvajVozilo(@RequestParam("vozilomarka") String markaVozila,
                                   @RequestParam("image") MultipartFile file,
                                   @RequestParam("vozilomodel") String modelVozila,
                                   @RequestParam("voziloklasa") String klasaVozila,
                                   @RequestParam("vozilomenjac") String vrstaMenjaca,
                                   @RequestParam("vozilogorivo") String tipGoriva,
                                   @RequestParam("vozilokm") String predjeniKm,
                                   @RequestParam("vozilosedista") String brsedistadeca,
                                   @RequestParam("username") String username,
                                   @RequestParam("userId") Long userid
                                   ) throws IOException {
        VoziloDTO ovDTO=new VoziloDTO();
        System.out.println("Username"+ username);
        ovDTO.setBrsedistadeca(brsedistadeca);
        ovDTO.setKlasaVozila(klasaVozila);
        ovDTO.setMarkaVozila(markaVozila);
        ovDTO.setModelVozila(modelVozila);
        ovDTO.setVrstaMenjaca(vrstaMenjaca);
        ovDTO.setPredjeniKm(predjeniKm);
        ovDTO.setTipGoriva(tipGoriva);
        ovDTO.setUser_id(userid);
        Vozilo vozilo=this.voziloService.createVozilo(ovDTO);
     logger.info("Vozilo je sacuvano");
        System.out.println("Original Image Byte Size - " + file.getBytes().length);

        //System.out.println("vozilo - " + ovDTO.getKlasaVozila());
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()),vozilo.getId()); //kreirana slika

        imageModelRepository.save(img);
        logger.info("Slika je sauvana");
        return ResponseEntity.status(HttpStatus.OK);
    }
    // @PreAuthorize("hasAuthority('create_oglas')")
   // public ResponseEntity<?> create(@RequestBody VoziloDTO ovDTO,@RequestParam("image") MultipartFile file) {
    //    System.out.println("dosli" + " " + ovDTO.getKlasaVozila());
    //    //ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
    //    //imageModelRepository.save(img);

     //   System.out.println("Usao pravim vozilo "+ ovDTO.getPredjeniKm()+ovDTO.getTipGoriva()+ovDTO.getVrstaMenjaca() + " ");
     //   Vozilo vozilo=this.voziloService.createVozilo(ovDTO);
     //   return new ResponseEntity<>(vozilo, HttpStatus.OK);
   // }
    
    @GetMapping("/image/{id}")
    public ImageModel getImage(@PathVariable("id") Long id) {
    	List<ImageModel> images = this.imageModelRepository.findAll();
    	ImageModel imageModel = null;
    	for(ImageModel image : images) {
    		if(image.getVozilo_id() == id) {
    			imageModel = new ImageModel(image.getName(), image.getType(), 
    					decompressBytes(image.getPicByte()), image.getVozilo_id() );
    		}
    	}
    	return imageModel;
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
    
    @JsonIgnore
    @GetMapping("/vozila/{id}")
    List<Vozilo> mojaVozila(@PathVariable("id") String id){
    	Long user=Long.parseLong(id);
		logger.info("Lista vozila");
    	return voziloService.getVozila(user);
    	
    }
    
    @GetMapping("agent/oglas/{id}")	//id - Ulogovani Agent
    public List<VoziloViewDTO> allVozilaOglas(@PathVariable("id") Long agent){	
    	List<VoziloViewDTO> agentskaVozila = new ArrayList<>();
    	List<Oglas> oglasi=(List<Oglas>) oglasRepo.findAll();
    	UserViewDTO user = this.userConnection.getUser(agent);
		
    	List<Vozilo> vozila = voziloService.getVozila(user.getId());
    	for(Vozilo v : vozila) {
    		Boolean uOglasu=false;
    		for(Oglas oo:oglasi) {
    			if(oo.getVozilo_id()==v.getId()) {
    				uOglasu=true;
    				System.out.println("U oglaasu je ");
    			}else {
    				System.out.println("Ni");
    			}
    		}
    		
    		if(uOglasu==false) {
    			VoziloViewDTO agentskoVozilo = new VoziloViewDTO(v);
        		agentskoVozilo.setUser(user);
        		agentskaVozila.add(agentskoVozilo);
    		}
    	
    	}
    	return agentskaVozila;
    }
    
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



