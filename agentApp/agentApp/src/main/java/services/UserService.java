package services;
import java.util.List;

import model.Authority;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    public User findByUsername(String username) {
        User u = userRepository.findByUsername(username);
        return u;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    //Za azuriranje Korisnika
    public User updateUser(User user) {

        User u = userRepository.findByUsername(user.getUsername());
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setAdress(user.getAdress());
        u.setPhoneNumber(user.getPhoneNumber());

        User changed = userRepository.save(u);
        return changed;
    }
    public User promeniSifru(User user) {
        User u = userRepository.findByUsername(user.getUsername());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(u);
    }

    public List<User> findOnlyUsers(String uloga){
        return userRepository.findAllByUloga(uloga);
    }

    //Za Registraciju
    public User saveUser(User user) {

        User u = userRepository.findByUsername(user.getUsername());		//Username => mail
        if( u == null ) {
            //u = userRepository.save(user);
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setEnabled(true);
            newUser.setAdress(user.getAdress());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setNalogAktiviran(false);
            newUser.setUloga("ROLE_USER");

            List<Authority> auth = authService.findByname("ROLE_USER");
            newUser.setAuthorities(auth);

            u = this.userRepository.save(newUser);
            return u;
        }
        return null;
    }

    public void remove(User user) {
        userRepository.delete(user);
    }
}
