package com.agentApp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentApp.app.models.Vozilo;

public interface VoziloRepository extends JpaRepository<Vozilo, Long> {

}
