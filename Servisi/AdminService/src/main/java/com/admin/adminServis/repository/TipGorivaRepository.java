package com.admin.adminServis.repository;

import com.admin.adminServis.model.TipGoriva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipGorivaRepository extends JpaRepository<TipGoriva, Long> {
}