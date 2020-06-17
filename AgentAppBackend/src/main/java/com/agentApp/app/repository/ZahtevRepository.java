package com.agentApp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agentApp.app.models.Zahtev;

public interface ZahtevRepository extends JpaRepository<Zahtev, Long>{

	@Query("select z from Zahtev z order by z.id desc")
	List<Zahtev> findSortedId();
}
