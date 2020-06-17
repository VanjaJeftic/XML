package com.agentApp.app.repository;

import com.agentApp.app.models.VrstaMenjaca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrstaMenjacaRepository extends JpaRepository<VrstaMenjaca, Long> {

	VrstaMenjaca findByNaziv(String vrstaMenjaca);
}