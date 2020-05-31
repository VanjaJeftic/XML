package com.admin.adminServis.service;

import com.admin.adminServis.connections.AuthorizationConnection;
import com.admin.adminServis.model.User;
import com.admin.adminServis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private AuthorizationConnection authorizationConnection;

    @Autowired
    public UserService(
            AuthorizationConnection authorizationConnection
    ) {
        this.authorizationConnection = authorizationConnection;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public boolean ukloniUsera(Long id) {
        User user = userRepository.findById(id).orElse(null);
        this.authorizationConnection.verify(id);//ovde verifikacija postojanja
                                                //usera iz authorization service

        if (user != null) {
            userRepository.delete(user);
            this.authorizationConnection.delete(id);//brisemo usera i u auth.servisu
            return true;
        } else {
            return false;
        }
    }

    public boolean blokirajUsera(Long id) {
        User user = userRepository.findById(id).orElse(null);
        this.authorizationConnection.verify(id);
        System.out.println("blokiran user je: " + id);
        if (user != null) {
            user.setNalogAktiviran(false);
            userRepository.save(user);
            this.authorizationConnection.blokirajUsera(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean odblokirajUsera(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setNalogAktiviran(true);
            userRepository.save(user);
            this.authorizationConnection.odblokirajUsera(id);
            return true;
        } else {
            return false;
        }
    }


}
