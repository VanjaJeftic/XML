package com.authorization.authorizationService.service;

import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.exceptions.InvalidUserException;
import com.authorization.authorizationService.exceptions.NotFoundException;
import com.authorization.authorizationService.model.AuthUserDetail;
import com.authorization.authorizationService.model.OnlineUser;
import com.authorization.authorizationService.model.RoleEnum;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.model.UserRole;
import com.authorization.authorizationService.repository.OnlineUserRepository;
import com.authorization.authorizationService.repository.UserDetailRepository;
import com.authorization.authorizationService.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

import javax.transaction.Transactional;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	@Autowired
	private OnlineUserRepository onlineUserRepository;

	@Autowired
	private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDetailRepository.findByUsername(name);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }

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
        user.setRoles(userdto.getRoles());
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

	@Transactional
	public void saveUser(UserDTO customer) {

		LOGGER.info("Start");
    	LOGGER.debug("Saving Customer with First Name: {}", customer.getUsername());

		OnlineUser onlineUser=new OnlineUser();
		onlineUser.setScreenName(customer.getUsername());
		onlineUser.setPassword(customer.getPassword());
		onlineUser.setActive(true);
		onlineUser.addRole(new UserRole(RoleEnum.OGLAS_READ));
		onlineUser.addRole(new UserRole(RoleEnum.OGLAS_WRITE));
		onlineUser.addRole(new UserRole(RoleEnum.ZAHTEV_READ));
		onlineUser.addRole(new UserRole(RoleEnum.ZAHTEV_WRITE));
		onlineUser.addRole(new UserRole(RoleEnum.VOZILO_READ));
		onlineUser.addRole(new UserRole(RoleEnum.VOZILO_WRITE));
		onlineUser.addRole(new UserRole(RoleEnum.MARKA_READ));
		onlineUser.addRole(new UserRole(RoleEnum.MODEL_READ));
		onlineUser.addRole(new UserRole(RoleEnum.TIPGORIVA_READ));
		onlineUser.addRole(new UserRole(RoleEnum.MENJAC_READ));
		onlineUser.addRole(new UserRole(RoleEnum.KLASA_READ));
		onlineUser.addRole(new UserRole(RoleEnum.SLIKA_READ));
		onlineUser.addRole(new UserRole(RoleEnum.SLIKA_WRITE));
		
		onlineUserRepository.save(onlineUser);

		User user=new User();
		user.setUsername(customer.getUsername());
		user.setEmail(customer.getEmail());
		userRepository.save(user);
		LOGGER.info("End");
	}


	public User findCustomer(String customerId) {

		LOGGER.info("Start");
		LOGGER.debug("Finding Customer with customerId: {}", customerId);
		User user = userRepository.findByUserId(customerId);
		if(user == null){
			throw new InvalidUserException(customerId);
		}
		LOGGER.info("Ending");
		return user;
	}

/*
	public User validateCustomer(UserCredentialDTO userCredentialDTO) {
		LOGGER.info("Start");
		LOGGER.debug("Validaing Customer with Username: {}", userCredentialDTO.getUserName());
		OnlineUser onlineUser = onlineUserRepository.findByScreenName(userCredentialDTO.getUserName());
		if(onlineUser == null || (!userCredentialDTO.getPassword().equals(onlineUser.getPassword()))){
			throw new InvalidUserException(userCredentialDTO.getUserName());
		}
		User user = userRepository.findByUserId(userCredentialDTO.getUserName());
		LOGGER.info("Ending");
		return user;
	}
*/

}
