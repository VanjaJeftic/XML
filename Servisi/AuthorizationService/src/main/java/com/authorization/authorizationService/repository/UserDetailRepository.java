package com.authorization.authorizationService.repository;

import com.authorization.authorizationService.dto.UserDTO;
import com.authorization.authorizationService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User,Integer> {

    User findByUsername(String name);
    Optional<User> findById(Long id);

    List<User> findAll();
    void deleteById(Long id);
	User findByEmail(String email);
}
