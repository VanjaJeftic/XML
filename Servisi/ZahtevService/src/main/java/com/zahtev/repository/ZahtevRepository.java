package com.zahtev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zahtev.model.Zahtev;

public interface ZahtevRepository extends JpaRepository<Zahtev, Long>{

}
