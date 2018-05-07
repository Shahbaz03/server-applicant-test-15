package com.mytaxi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.SearchResultsDTO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.search.SearchService;

@RestController
@RequestMapping("v1/search")
public class SearchController {
	
	private final SearchService searchService;

	@Autowired
	public SearchController(final SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping
	public List<SearchResultsDTO> getSearcResults(@RequestParam String query) throws EntityNotFoundException, IOException {
		return searchService.search(query);
	}
}
