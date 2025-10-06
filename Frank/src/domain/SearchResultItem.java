package domain;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultItem {
	
	private int id;
	private String name;
	
	@JsonProperty("first_air_date")
	private String firstDate;
	
	public SearchResultItem() {}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Optional<LocalDate> getFirstAired() {
		if (firstDate == null) {
			return Optional.empty();
		}
		try {
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(firstDate, df);
			return Optional.of(date);
		} catch (DateTimeParseException e) {
			return Optional.empty();
		}
	}
}
