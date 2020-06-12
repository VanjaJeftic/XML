package com.agentApp.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentApp.app.models.ImageModel;

public interface ImageModelRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}
