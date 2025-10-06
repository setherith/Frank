package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonItem {

	private int id;
	private String name;
	
	@JsonProperty("season_number")
	private int number;
	
	public SeasonItem() { }
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSeasonNumber() {
		return number;
	}
	
}