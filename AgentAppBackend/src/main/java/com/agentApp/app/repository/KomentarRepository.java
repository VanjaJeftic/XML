package com.agentApp.app.repository;

import com.agentApp.app.models.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KomentarRepository extends JpaRepository <Komentar, Long> {
    List<Komentar> findByOdobren(boolean b);
    List<Komentar> findByOdbijen(boolean b);

}

