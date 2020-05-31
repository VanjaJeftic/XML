package com.oglas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oglas.model.TerminZauzeca;

@Repository
public interface TerminZauzecaRepository extends CrudRepository<TerminZauzeca, Long> {
}
