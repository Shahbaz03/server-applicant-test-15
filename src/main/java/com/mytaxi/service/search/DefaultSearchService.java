package com.mytaxi.service.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mytaxi.datatransferobject.SearchResultsDTO;
import com.mytaxi.domainvalue.BookingStatus;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.SearchException;

/**
 * The deafult search service implementation
 * actually implements the search method for all
 * the request queries.
 * Going forward it can be extended with more
 * searching capabilities.
 * 
 * @author Shahbaz.Alam
 *
 */
@Service
public class DefaultSearchService implements SearchService{

	/**
	 * The method does a flat file search on the basis of 
	 * the query provided by the user. Currently it just checks
	 * all the flat files in the project directory to find documents
	 * which contains the given query and populates the searchresultdto
	 * to be sent back to the caller.
	 * The implementation is just to show the searching mechanism and it
	 * is not optimized and scalable. 
	 * The idea is to build the search capability using enterprise search
	 * platforms like apache solr, to search and index the documents and
	 * provide optimize solution.
	 * However as of now, with limited data set, this serves the purpose.
	 */
	@Override
	public List<SearchResultsDTO> search(String query) {
		Set<String> matchedDoc = getMatchedDocuments(query);
		if(!matchedDoc.isEmpty())
			return matchedDoc.stream().map(doc -> makeSearchResultsDTO(doc)).collect(Collectors.toList());
		
		throw new SearchException("No results Found for query=" + query);
	}
	
	private SearchResultsDTO makeSearchResultsDTO(String document){
		Map<String, String> map = Pattern.compile("\\s*,\\s*")
			    .splitAsStream(document.trim())
			    .map(s -> s.split("="))
			    .collect(Collectors.toMap(a -> a[0], a -> a[1]));
		return makeSearchResultsDTO(map);
	}
	
	private SearchResultsDTO makeSearchResultsDTO(Map<String, String> map){
		SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
		searchResultsDTO.setVehicleId(Long.parseLong(map.get("id")));
		searchResultsDTO.setLicensePlate(map.get("licensePlate"));
		searchResultsDTO.setRating(map.get("rating").equalsIgnoreCase("null") ? null : Integer.parseInt(map.get("rating")));
		searchResultsDTO.setColor(map.get("color"));
		searchResultsDTO.setName(map.get("name"));
		searchResultsDTO.setSeatCount(Integer.parseInt(map.get("seatCount")));
		searchResultsDTO.setConvertible(map.get("convertible").equalsIgnoreCase("true") ? true : false);
		searchResultsDTO.setEngineType(EngineType.valueOf(map.get("engineType")));
		searchResultsDTO.setManufacturerName(map.get("manufacturer"));
		searchResultsDTO.setBookingStatus(BookingStatus.valueOf(map.get("bookingStatus")));
		searchResultsDTO.setDriverLink(map.get("driverId").equalsIgnoreCase("null") ? null : "http://localhost:8080/vi/drivers/"+ map.get("driverId"));
		return searchResultsDTO;
	}
	
	
	/**
	 * the method checks for all the files
	 * in the path and matches with the query 
	 * provided.
	 * 
	 * @param query
	 * @return
	 */
	private Set<String> getMatchedDocuments(String query) {
		Set<String> matchedDocName = new HashSet<>();
		try{
			Files.newDirectoryStream(Paths.get("."), path -> path.toString().endsWith(".txt")).forEach(f -> {
				try {
					Files.lines(f).forEach(line -> {
						String matchedID = getMatchedId(line, query);
						if (matchedID != null) {
							matchedDocName.add(matchedID);
						}
					});
				} catch (IOException e) {
					throw new SearchException(e.getMessage());
				}
			});
		}catch(IOException ex){
			throw new SearchException(ex.getMessage());
		}
		return matchedDocName;
	}

	private String getMatchedId(String line, String query) {

		boolean match = true;
		String words[] = query.split(" ");
		for (String word : words) {
			if (!line.contains(word.toUpperCase())) {
				match = false;
				break;
			}
		}
		return (match) ? line : null;
	}

}
