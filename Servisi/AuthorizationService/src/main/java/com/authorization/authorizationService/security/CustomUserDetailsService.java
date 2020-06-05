package com.authorization.authorizationService.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authorization.authorizationService.model.Permission;
import com.authorization.authorizationService.model.Role;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.repository.UserDetailRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDetailRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUsername(arg0);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", arg0));
		} else {
			return user;
		}
		
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Role role){
		return getGrantedAuthorities(getPrivileges(role));
	}
	
	
	private Collection<Permission> getPrivileges(Role role) {
        return role.getPermissions();
    }
	
	private List<GrantedAuthority> getGrantedAuthorities(Collection<Permission> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission p : privileges) {
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        }
        return authorities;
    }

	

}