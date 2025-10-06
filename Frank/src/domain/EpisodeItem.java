package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeItem {
	
	@JsonProperty("episode_number")
	private int EpisodeNumber;
	
	@JsonProperty("name")
	private String Name;
	
	public EpisodeItem() { }
	
	public int getEpisodeNumber() {
		return EpisodeNumber;
	}
	
	public String getName() {
		return Name;
	}
}
