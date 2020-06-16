package com.agentApp.app.services;

import java.util.List;

import com.agentApp.app.dto.UserDTO;
import com.agentApp.app.models.Authority;
import com.agentApp.app.models.User;
import com.agentApp.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
		u.setCity(user.getCity());
		u.setCountry(user.getCountry());
		u.setPhoneNumber(user.getPhoneNumber());
//		if(!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
//			u.setPassword(passwordEncoder.encode(user.getPassword()));
//		}
		
		User changed = userRepository.save(u);
		return changed;
	}
	public User promeniSifru(User user, String password) {
		User u = userRepository.findByUsername(user.getUsername());
		u.setPassword(passwordEncoder.encode(password));
		return userRepository.save(u);
	}
	

	public List<User> findOnlyUsers(String uloga){
		return userRepository.findAllByUloga(uloga);
	}
	
	//Za Registraciju
	public User saveUser(UserDTO user) {
		System.out.println("Servis user");
		User u = userRepository.findByUsername(user.getUsername());		//Username => mail
		User u2=userRepository.findByEmail(user.getEmail());
		if( u == null && u2==null) {
			//u = userRepository.save(user);
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setEmail(user.getEmail());
			newUser.setPassword(passwordEncoder.encode(user.getPassword()));
			newUser.setFirstname(user.getFirstname());
			newUser.setLastname(user.getLastname());
			newUser.setEnabled(true);
			newUser.setAdress(user.getAdress());
			newUser.setCity(user.getCity());
			newUser.setCountry(user.getCountry());
			newUser.setPhoneNumber(user.getPhoneNumber());
			newUser.setNalogAktiviran(false);
			newUser.setUloga("ROLE_USER");								//Ovo i ne treba
			
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
