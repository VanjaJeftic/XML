package com.admin.adminServis.repository;

import com.admin.adminServis.model.KlasaVozila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlasaVozilaRepository extends JpaRepository<KlasaVozila, Long> {
}
