package com.agentApp.app.repository;

import com.agentApp.app.models.ModelVozila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelVozilaRepository extends JpaRepository<ModelVozila, Long> {

	ModelVozila findByNaziv(String modelVozila);
}
