package com.agentApp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentApp.app.models.Izvestaj;

public interface IzvestajRepository extends JpaRepository<Izvestaj, Long> {

}
