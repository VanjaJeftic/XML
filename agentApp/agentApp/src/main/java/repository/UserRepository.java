package repository;

import java.util.List;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findAllByUloga(String uloga);

    User findByUsername(String username);
}
