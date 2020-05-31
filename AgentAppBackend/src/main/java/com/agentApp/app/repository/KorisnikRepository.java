package com.agentApp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentApp.app.models.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	Korisnik findByUserId(Long id);
}
