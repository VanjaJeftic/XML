package com.admin.adminServis.repository;

import com.admin.adminServis.model.MarkaVozila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkaVozilaRepository extends JpaRepository<MarkaVozila, Long> {
}
