package com.agentApp.app.repository;

import com.agentApp.app.models.TipGoriva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipGorivaRepository extends JpaRepository<TipGoriva, Long> {

	TipGoriva findByNaziv(String tipGoriva);
}