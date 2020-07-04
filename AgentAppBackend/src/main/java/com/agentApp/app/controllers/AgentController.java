package com.agentApp.app.controllers;

import com.agentApp.app.dto.AgentDTO;
import com.agentApp.app.dto.UserDTO;
import com.agentApp.app.models.User;
import com.agentApp.app.services.EmailService;
import com.agentApp.app.services.UserService;
import com.agentApp.app.tokenUtils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/agent")
public class AgentController {


    @Autowired
    private EmailService emailService;

    protected final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AgentController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private UserService userService;



    @PostMapping
    public ResponseEntity<?> RegisterAgent(@RequestBody AgentDTO dto){
        System.out.println("Registrovanje agenta "+dto.getUsername()+dto.getIme());
        UserDTO userDTO=new UserDTO();
        userDTO.setFirstname(dto.getIme());
        userDTO.setLastname(dto.getPrezime());
        userDTO.setUsername(dto.getUsername());
        userDTO.setAdress(dto.getAdresa());
        userDTO.setUloga("ROLE_AGENT");
        userDTO.setMaticnibroj(dto.getMaticnibroj());
        userDTO.setPassword(dto.getPassword());
        userDTO.setEmail(dto.getEmail());


        try {
            emailService.registrovanjeAgenta(userDTO);
        }catch( Exception e ){
            logger.info("Greska prilikom slanja emaila: " + e.getMessage());
        }

        User u = userService.saveAgent(userDTO);

        if( u == null ) {
            logger.info("User sa tim kredencijalima ne postoji u bazi, stoga se korisnik moze registrovati");
            return new ResponseEntity<>(u, HttpStatus.OK);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






}
