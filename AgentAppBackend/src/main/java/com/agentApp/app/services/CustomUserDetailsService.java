package com.agentApp.app.services;

import com.agentApp.app.models.User;
import com.agentApp.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
//	private PasswordEncoder passwordEncoder;

//	private AuthenticationManager authenticationManager;

//	@Autowired
//	public void setPasswordEncoder(PasswordEncoder passwordEncoder){
//		this.passwordEncoder = passwordEncoder;
//	}

//	@Autowired
//	public void setAuthenticationManager(AuthenticationManager authenticationManager){
//		this.authenticationManager = authenticationManager;
//	}

	@Autowired
	public  void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
	}
	
//	public void changePassword(String oldPassword, String newPassword) {
//
//		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
//		String username = currentUser.getName();
//
//		if(authenticationManager != null) {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
//		}else {
//			return;
//		}
//
//		User user = (User) loadUserByUsername(username);
//
//		user.setPassword(passwordEncoder.encode(newPassword));
//		userRepository.save(user);
//	}
}
