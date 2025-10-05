package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonItem {

	private int id;
	private String name;
	
	public SeasonItem() { }
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}