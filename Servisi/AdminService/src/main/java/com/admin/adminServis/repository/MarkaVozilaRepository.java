package com.admin.adminServis.repository;

import com.admin.adminServis.model.MarkaVozila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkaVozilaRepository extends JpaRepository<MarkaVozila, Long> {
    List<MarkaVozila> findByNaziv(String naziv);
}
