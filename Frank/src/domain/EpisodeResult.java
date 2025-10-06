package domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeResult {
	
	@JsonProperty("episodes")
	private List<EpisodeItem> Episodes;
	
	public List<EpisodeItem> getEpisodes() {
		return Episodes;
	}
	
	public EpisodeResult() { }

}
