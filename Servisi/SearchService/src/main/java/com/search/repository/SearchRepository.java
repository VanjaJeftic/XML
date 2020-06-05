package com.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.search.model.Search;


public interface SearchRepository extends JpaRepository<Search, Long> {

}
