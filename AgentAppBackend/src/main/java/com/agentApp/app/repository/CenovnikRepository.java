package com.agentApp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.agentApp.app.models.Cenovnik;
import com.agentApp.app.models.User;

public interface CenovnikRepository extends JpaRepository<Cenovnik, Long>{

	Cenovnik findByNaziv(String cenovnik_naziv);

	List<Cenovnik> findByUser(User u);
	
	@Modifying
	@Query("delete from Cenovnik t where t.id = ?1")
	void delete(Long entityId);

}
