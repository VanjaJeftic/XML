package com.admin.adminServis.repository;

import com.admin.adminServis.model.ModelVozila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelVozilaRepository extends JpaRepository<ModelVozila, Long> {
}
