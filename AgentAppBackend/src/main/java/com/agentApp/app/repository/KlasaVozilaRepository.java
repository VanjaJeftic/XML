package com.agentApp.app.repository;

import com.agentApp.app.models.KlasaVozila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlasaVozilaRepository extends JpaRepository<KlasaVozila, Long> {

	KlasaVozila findByNaziv(String klasaVozila);
}
