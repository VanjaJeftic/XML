package com.oglas.controllers;

import com.oglas.dto.OglasDTO;
import com.oglas.dto.OglasVoziloDTO;
import com.oglas.dto.UserViewDTO;
import com.oglas.dto.VoziloDTO;
import com.oglas.dto.VoziloViewDTO;
import com.oglas.model.ImageModel;
import com.oglas.model.Oglas;
import com.oglas.model.Vozilo;
import com.oglas.repository.ImageModelRepository;
import com.oglas.repository.VoziloRepository;
import com.oglas.service.VoziloService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private ImageModelRepository imageModelRepository;

    @Autowired
    private VoziloRepository voziloRepository;
    
    @GetMapping
    public List<VoziloViewDTO> allVozila(){		//Prepraviti da vraca vozila za ulogovanog korisnika
    	List<VoziloViewDTO> agentskaVozila = new ArrayList<>();
    	UserViewDTO user = new UserViewDTO();
		user.setFirstname("Goran");
		user.setId(2L);						//Prepraviti na ulogovanog agenta
		
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

    @PostMapping("/novoVozilo")
    public BodyBuilder uplaodImage(@RequestParam("vozilomarka") String markaVozila,
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
        System.out.println("vozilo" + vozilo.getId() );
        System.out.println("Original Image Byte Size - " + file.getBytes().length);

        //System.out.println("vozilo - " + ovDTO.getKlasaVozila());
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()),vozilo.getId()); //kreirana slika

        imageModelRepository.save(img);
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
    
    @GetMapping("/vozila/{id}")
    List<Vozilo> mojaVozila(@PathVariable("id") String id){
    	Long user=Long.parseLong(id);
		System.out.println("usao da dobavim "+id+" " +voziloService.getVozila(user).size());
    	return voziloService.getVozila(user);
    	
    }
}


