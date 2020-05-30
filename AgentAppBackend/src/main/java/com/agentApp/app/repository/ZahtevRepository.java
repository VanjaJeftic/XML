package com.agentApp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentApp.app.models.Zahtev;

public interface ZahtevRepository extends JpaRepository<Zahtev, Long>{

}
