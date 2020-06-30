package com.messages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messages.model.Poruka;

public interface PorukaRepository extends JpaRepository<Poruka, Long> {

}