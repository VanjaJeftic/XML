package com.authorization.authorizationService.service;

import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.exceptions.NotFoundException;
import com.authorization.authorizationService.model.AuthUserDetail;
import com.authorization.authorizationService.model.Permission;
import com.authorization.authorizationService.model.Role;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.repository.RoleRepository;
import com.authorization.authorizationService.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailRepository userDetailRepository;
    
    @Autowired 
    private RoleRepository roleRepo;

    private Logger logger = LogManager.getLogger();
    
    @Autowired
	private BCryptPasswordEncoder encoder;
    
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    	
    	User user = userDetailRepository.findByUsername(name);
    	
    	if (user != null) {
    		
			String authorities = user.getRoles().get(0).getName()+",";
			if (!user.getRoles().get(0).getPermissions().isEmpty()) {
				for (Permission p : user.getRoles().get(0).getPermissions()) {
					authorities += (p.getName() + ",");
				}
			}
			authorities = authorities.substring(0, authorities.length() - 1);
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
			// "User" klasu sadrzi Spring i tu klasu vraca UserDetailsService, a koristi se
			// pri verifikaciji od strane authManagera
			ThreadContext.put("user", name);
			logger.info( "Korisnik autorizovan");
			return new  org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}else {
			logger.info( "Nije pronadjen");

			throw new UsernameNotFoundException("Korisnik ili agent: " + name + " nije pronadjen");
		}
    }

    public User findByUsername(String username) {
        User u = userDetailRepository.findByUsername(username);
        return u;
    }
/*
    public User saveAgent(UserDTO user) {
        System.out.println("Servis agent");
        User u = userDetailRepository.findByUsername(user.getUsername());		//Username => mail
        User u2=userDetailRepository.findByUsername(user.getUsername());


        if( u == null && u2==null) {
            //u = userRepository.save(user);
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setIme(user.getIme());
            newUser.setPrezime(user.getPrezime());
            newUser.setEnabled(true);
            newUser.setAdresa(user.getAdresa());
            newUser.setNalogAktivan(true);
            List<Role> role=new ArrayList<Role>();
            role.add(roleRepo.findByName("ROLE_agent"));
            newUser.setRoles(role);							//Ovo i ne treba
            newUser.setEmail(user.getEmail());
            //List<Authority> auth = authService.findByname("ROLE_AGENT");
            //newUser.setAuthorities(auth);

            u = this.userDetailRepository.save(newUser);
            return u;
        }
        return null;
    }
*/
    public User createUserAgent(UserDTO userdto) {
        User user = this.userDetailRepository.save(noviagent(userdto));
        return user;
    }

    public User createUser(UserDTO userdto) {
        User user = this.userDetailRepository.save(novi(userdto));
        return user;
    }

    public User findById(Long id) {

        return userDetailRepository.findById(id).orElseGet(null);
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
        user.setRoles(userdto.getRoles());
        user.setUsername(userdto.getUsername());

        return this.userDetailRepository.save(user);
    }

    public User promeniSifru(User user, String password) {
        User u = userDetailRepository.findByUsername(user.getUsername());
        u.setPassword(passwordEncoder.encode(password));
        return userDetailRepository.save(u);
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
        System.out.println("blokiran user u auth servicu je: " + id);
        if (user != null) {
        	 System.out.println("udjoh da blokiram" + id);
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

    public User novi(UserDTO user) {
    	User u=new User();
    	u.setNalogAktivan(true);
    	u.setUsername(user.getUsername());
    	u.setPassword(encoder.encode(user.getPassword()));
    	System.out.println("NOVI USER + enkodirana sifra" + encoder.encode(user.getPassword()));
    	u.setEmail(user.getEmail());
    	u.setEnabled(true);
    	u.setAccountNonExpired(true);
    	u.setAccountNonLocked(true);
    	u.setCredentialsNonExpired(true);
    	List<Role> role=new ArrayList<Role>();
    	role.add(roleRepo.findByName("ROLE_user"));
    	u.setRoles(role);
    	u.setIme(user.getIme());
    	u.setPrezime(user.getPrezime());
    	u.setAdresa(user.getAdresa());
    	u.setMesto(user.getMesto());
    	u.setTelefon(user.getTelefon());
    	u.setPotvrdalozinke(encoder.encode(user.getPotvrdalozinke()));
    	
    	
    	return u;
    }



    public User noviagent(UserDTO user) {
        User u=new User();
        u.setNalogAktivan(true);
        u.setUsername(user.getUsername());
        u.setPassword(encoder.encode(user.getPassword()));
        System.out.println("NOVI USER + enkodirana sifra" + encoder.encode(user.getPassword()));
        u.setEmail(user.getEmail());
        u.setEnabled(true);
        u.setAccountNonExpired(true);
        u.setAccountNonLocked(true);
        u.setCredentialsNonExpired(true);
        u.setMaticnibroj(user.getMaticnibroj());
        u.setMesto(user.getMesto());
        System.out.println("Mesto je  "+user.getMesto());
        u.setNazivfirme(user.getNazivfirme());
        List<Role> role=new ArrayList<Role>();
        role.add(roleRepo.findByName("ROLE_agent"));
        u.setRoles(role);
        u.setIme(user.getIme());
        u.setPrezime(user.getPrezime());
        u.setAdresa(user.getAdresa());
        u.setMesto(user.getMesto());
        u.setTelefon(user.getTelefon());
       // u.setPotvrdalozinke(encoder.encode(user.getPotvrdalozinke()));


        return u;
    }



}
