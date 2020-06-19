package com.agentApp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agentApp.app.models.Poruka;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long> {

}