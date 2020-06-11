package com.agentApp.app.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.agentApp.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /*
     * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
     */
    @Autowired
    private Environment env;

    /*
     * Anotacija za oznacavanje asinhronog zadatka
     * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
     */



    public void sendNotificaitionZaRegistraciju(User k) throws  MailException, InterruptedException, MessagingException{

        System.out.println("Slanje emaila na register...");
        String htmlView = "<html> <a href='http://localhost:4200/activateAccount/" + k.getId() + "'> Potvrdite link za aktivaciju naloga. </a> </html>";
        //SimpleMailMessage mail = new SimpleMailMessage();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(k.getEmail());
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Uspesno ste se registrovali");
        helper.setText(htmlView, true);

        javaMailSender.send(message);


        System.out.println("Email poslat!");
    }




    public void sendNotificaitionOdobrenaRegistracija(User k) throws MailException, InterruptedException {

        System.out.println("Slanje email potvrde registracije");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(k.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Administrator KC");
        mail.setText("Pozdrav " + k.getFirstname() + ",\n\nVasa registracija je potvrdjena");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    public void sendNotificaitionOdbijenaRegistracija(User k) throws MailException, InterruptedException {

        System.out.println("Slanje email potvrde registracije");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(k.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Administrator KC");
        mail.setText("Pozdrav " + k.getFirstname() + ",\n\nVasa registracija je odbijena");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }


    public void sendNotificaitionRazlogOdbijanja(User k, String s) {
        // TODO Auto-generated method stub
        System.out.println("Slanje email odbijanje registacije" + k.getFirstname() + s);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(k.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Odbijanje registracije");
        mail.setText("Pozdrav " + k.getFirstname() + "  "+ s);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");

    }



}