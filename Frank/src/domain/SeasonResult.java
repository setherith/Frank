package domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonResult {
	
	private List<SeasonItem> seasons;
	
	public SeasonResult() { }
	
	public List<SeasonItem> getSeasons() {
		return seasons;
	}

}
