package com.search.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.search.model.Search;
import com.search.repository.SearchRepository;

@Service
public class SearchService {
	@Autowired
	private SearchRepository searchRepository;
	
	public List<Search> getAllSearch(){
		return searchRepository.findAll();
	}
	public Search createSearch(Search search) {
		Search s = this.searchRepository.save(search);
		return s;
	}
}
