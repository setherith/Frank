package domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
	
	private List<SearchResultItem> results;
	
	public SearchResult() {}
	
	public List<SearchResultItem> getResults() {
		return results;
	}
}
