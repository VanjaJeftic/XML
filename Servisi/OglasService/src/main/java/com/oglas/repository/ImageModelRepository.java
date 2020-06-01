package com.oglas.repository;

import java.util.Optional;

import com.oglas.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageModelRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}