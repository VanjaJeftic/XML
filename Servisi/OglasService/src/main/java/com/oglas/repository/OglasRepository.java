package com.oglas.repository;

import com.oglas.model.Oglas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OglasRepository extends CrudRepository<Oglas, Long> {
	
}
