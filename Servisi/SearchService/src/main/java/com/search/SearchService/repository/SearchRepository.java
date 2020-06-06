package com.search.SearchService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.search.SearchService.model.Search;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {

}
