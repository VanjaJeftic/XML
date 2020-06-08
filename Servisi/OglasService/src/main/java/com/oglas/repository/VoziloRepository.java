package com.oglas.repository;

import com.oglas.model.Vozilo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoziloRepository extends CrudRepository<Vozilo, Long> {

	
}