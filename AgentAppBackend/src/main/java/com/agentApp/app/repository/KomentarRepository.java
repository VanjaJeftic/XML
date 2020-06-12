package com.agentApp.app.repository;

import com.agentApp.app.models.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarRepository extends JpaRepository <Komentar, Long> {
}

