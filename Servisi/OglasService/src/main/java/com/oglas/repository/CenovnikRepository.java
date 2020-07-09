package com.oglas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.oglas.model.Cenovnik;

public interface CenovnikRepository extends JpaRepository<Cenovnik, Long>{

	Cenovnik findByNaziv(String cenovnik_naziv);
	
	@Modifying
	@Query("delete from Cenovnik t where t.id = ?1")
	void delete(Long entityId);

	List<Cenovnik> findByOwner(Long id);

}
