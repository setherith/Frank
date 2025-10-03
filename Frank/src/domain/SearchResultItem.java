package domain;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultItem {
	
	private int id;
	private String name;
	private String first_air_date;
	
	public SearchResultItem() {}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Optional<LocalDate> getFirstAired() {
		
		if (first_air_date == null) {
			return Optional.empty();
		}
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(first_air_date, df);
		return Optional.of(date);
	}
}
