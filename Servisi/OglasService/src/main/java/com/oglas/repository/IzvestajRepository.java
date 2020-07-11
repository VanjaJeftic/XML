package com.oglas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oglas.model.Izvestaj;

public interface IzvestajRepository extends JpaRepository<Izvestaj, Long> {

	@Query("select i from Izvestaj i order by i.id desc")
	List<Izvestaj> findSortedIzvestaj();
}
