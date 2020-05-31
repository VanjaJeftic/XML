package com.authorization.authorizationService.service;

import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.exceptions.NotFoundException;
import com.authorization.authorizationService.model.AuthUserDetail;
import com.authorization.authorizationService.model.User;
import com.authorization.authorizationService.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;

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

}
