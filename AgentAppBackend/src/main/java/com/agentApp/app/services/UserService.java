package com.agentApp.app.services;

import java.util.ArrayList;
import java.util.List;

import com.agentApp.app.dto.UserDTO;
import com.agentApp.app.models.Authority;
import com.agentApp.app.models.Korisnik;
import com.agentApp.app.models.User;
import com.agentApp.app.repository.KorisnikRepository;
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
	
	@Autowired
	private AuthorityService authRepo;
	
	@Autowired
	private KorisnikRepository korisnikRepo;
	
	public User findByUsername(String username) {
		User u = userRepository.findByUsername(username);
		return u;
	}
	
	public List<User> findAll(){

		return userRepository.findAll();
	}

	public List<User> findUsers(){
		List<User> svi=userRepository.findAll();
		List<User> users=new ArrayList<User>();
		for(User u:svi) {
			if(u.getRoles().get(0).getName().equals("ROLE_USER")) {
				users.add(u);
			}
		}
		
		return users;
			
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


	public User saveAgent(UserDTO user) {
		System.out.println("Servis agent");
		User u = userRepository.findByUsername(user.getUsername());		//Username => mail
		User u2=userRepository.findByUsername(user.getUsername());


		if( u == null && u2==null) {
			//u = userRepository.save(user);
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(passwordEncoder.encode(user.getPassword()));
			newUser.setFirstname(user.getFirstname());
			newUser.setLastname(user.getLastname());
			newUser.setEnabled(true);
			newUser.setAdress(user.getAdress());
			newUser.setNalogAktiviran(true);
			newUser.setUloga("ROLE_AGENT");								//Ovo i ne treba
			newUser.setEmail(user.getEmail());
			List<Authority> auth = authService.findByname("ROLE_AGENT");
			newUser.setAuthorities(auth);

			u = this.userRepository.save(newUser);
			return u;
		}
		return null;
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
	
	public User findUserByKorisnik(Korisnik k) {
		List<User> users = this.userRepository.findAll();
		for(User u : users) {
			if(k.getUser().equals(u)) {
				return u;
			}
		}
		return null;
	}
	
	public boolean ukloniUsera(Long id) {
		if(id!=null) {
			System.out.println("Brisem korisnika");
			Korisnik k=this.korisnikRepo.findOneByUserId(id);
			this.korisnikRepo.deleteById(k.getId());
			this.userRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	
	 public boolean blokirajUsera(Long id) {

	        System.out.println("blokiran user je: " + id);
	        if (id != null) {
	            System.out.println("Usao da blokieam u auth servcice");
	            User u=this.userRepository.findById(id).get();
	            u.setNalogAktiviran(false);
	            this.userRepository.save(u);
	            return true;
	        } else {
	        	System.out.println("Nisam blokirao korisnika");
	            return false;
	        }
	    }

	    public boolean odblokirajUsera(Long id) {
	      
	        if (id != null) {
	        	System.out.println("Odblokiram u servisu");
	        	 User u=this.userRepository.findById(id).get();
		         u.setNalogAktiviran(true);
		         this.userRepository.save(u);
	            return true;
	        } else {
	        	System.out.println("Nisam odblokirao korisnika");
	            return false;
	        }
	    }
}
