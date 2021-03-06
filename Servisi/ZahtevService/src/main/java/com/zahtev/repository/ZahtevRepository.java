package com.zahtev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zahtev.model.Zahtev;

public interface ZahtevRepository extends JpaRepository<Zahtev, Long>{

	@Query("select z from Zahtev z order by z.id desc")
	List<Zahtev> findSortedId();
}
