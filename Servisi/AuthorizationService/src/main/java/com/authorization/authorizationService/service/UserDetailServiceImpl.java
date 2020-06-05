package com.authorization.authorizationService.service;

import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.exceptions.NotFoundException;
import com.authorization.authorizationService.model.AuthUserDetail;
import com.authorization.authorizationService.model.Permission;
import com.authorization.authorizationService.model.Role;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.repository.UserDetailRepository;
import com.authorization.authorizationService.security.auth.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserDetailRepository userDetailRepository;
    
    @Autowired
	HttpSession session;

 
    public User createUser(UserDTO userdto) {
        User user = this.userDetailRepository.save(new User(userdto));
        return user;
    }

    public User update(UserDTO userdto) {
        User user = this.userDetailRepository.findById(userdto.getId())
                .orElseThrow(() -> new NotFoundException("Oglas with that id does not exist!"));

        user.setAccountNonExpired(userdto.isAccountNonExpired());
        user.setAccountNonLocked(userdto.isAccountNonLocked());
        user.setCredentialsNonExpired(userdto.isCredentialsNonExpired());
        user.setEmail(userdto.getEmail());
        user.setEnabled(userdto.isEnabled());
        user.setId(userdto.getId());
        user.setPassword(userdto.getPassword());
        user.setRoles((Set<Role>) userdto.getRoles());
        user.setUsername(userdto.getUsername());

        return this.userDetailRepository.save(user);
    }

    public void delete(Long id) {
        userDetailRepository.deleteById(id);
        return;
    }

    public boolean verify(Long userId) {
        if (!this.userDetailRepository.findById(userId).isPresent()) {
            throw new NotFoundException("Consumer with that id does not exist!");
        }

        return true;
    }

    public boolean blokirajUsera(Long id) {
        User user = userDetailRepository.findById(id).orElse(null);
        System.out.println("blokiran user je: " + id);
        if (user != null) {
            user.setNalogAktivan(false);
            userDetailRepository.save(user);
            return true;
        } else {
            return false;
        }
    }


    public boolean odblokirajUsera(Long id) {
        User user = userDetailRepository.findById(id).orElse(null);
        if (user != null) {
            user.setNalogAktivan(true);
            userDetailRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
    
    public User getUser(Long id) {
    	return userDetailRepository.findById(id).orElse(null);
    }
    
    public void promeniPassword(String pass) {
    	
    	User u=(User) session.getAttribute("user");
    	u.setPassword(pass);
    	userDetailRepository.save(u);
    }
    
    private List<String> getPermission(Collection<Role> roles) {
  	  
        List<String> privileges = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPermissions());
        }
        for (Permission item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    
    	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		
		return getGrantedAuthorities(getPermission(roles));
	
	}

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			System.out.println(username);
			User user=userDetailRepository.findByEmail(username);
			
			return getUserPrincipal(user);
		}

		private UserDetails getUserPrincipal(User user) {
			// TODO Auto-generated method stub
			Stream<String> roles = user.getRoles().stream().map(Role::getName);
					
			Stream<String> permissions = user.getRoles().stream()
					.map(Role::getPermissions)
					.flatMap(Collection::stream)
					.map(Permission::getName);

			List<GrantedAuthority> authorities = Stream.concat(roles, permissions)
					.distinct()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

			return new UserPrincipal(user.getId(), user.getPassword(), user.getEmail(), user.isEnabled(), authorities,
					user.isAccountNonLocked());
		}

}
