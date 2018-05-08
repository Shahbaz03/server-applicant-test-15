package com.mytaxi.service.search;

import java.io.IOException;
import java.util.List;

import com.mytaxi.datatransferobject.SearchResultsDTO;

/**
 * The Search Service Interface
 * 
 * @author Shahbaz.Alam
 *
 */
public interface SearchService {
	List<SearchResultsDTO> search(String query) throws IOException;

}
