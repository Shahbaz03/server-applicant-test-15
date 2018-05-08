package com.mytaxi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.mytaxi.constants.SwaggerConstants.SEARCH_CONTROLLER;
import com.mytaxi.datatransferobject.SearchResultsDTO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.search.SearchService;

import io.swagger.annotations.Api;

/**
 * The controller is used for all search in the system.
 * The idea of making a seperate search controller
 * is to have a dedicated resource for all the search
 * requests. It just does one simple thing. SEARCH.
 * It can be used by the drivers to search for vehicles,
 * can be used by customer to search for vehicles,
 * can be used by internal emoloyess to search for
 * vehicles by attributes. 
 * 
 * @author Shahbaz.Alam
 *
 */
@RestController
@Api(tags = {SEARCH_CONTROLLER})
@RequestMapping("v1/search")
public class SearchController {
	
	private final SearchService searchService;

	@Autowired
	public SearchController(final SearchService searchService) {
		this.searchService = searchService;
	}
	/**
	 * It routes all search requests and feeds back the results.
	 * 
	 * @param query
	 * @return
	 * @throws EntityNotFoundException
	 * @throws IOException
	 */
	@GetMapping
	public List<SearchResultsDTO> getSearcResults(@RequestParam String query) throws EntityNotFoundException, IOException {
		return searchService.search(query);
	}
}
