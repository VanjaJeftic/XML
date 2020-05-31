package com.admin.adminServis.repository;

import com.admin.adminServis.model.KlasaVozila;
import com.admin.adminServis.model.VrstaMenjaca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrstaMenjacaRepository extends JpaRepository<VrstaMenjaca, Long> {
}