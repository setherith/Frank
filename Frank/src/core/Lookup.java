package core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;

import domain.EpisodeItem;
import domain.EpisodeResult;
import domain.SearchResult;
import domain.SearchResultItem;
import domain.SeasonItem;
import domain.SeasonResult;


public class Lookup {
	
	private String query;
	
	private String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNDM2MGQyZTk5NjgwZGMxZTliYTRiOGE5YzBlZjkwMiIsIm5iZiI6MTc1OTMxNjIzOC4yNzksInN1YiI6IjY4ZGQwOTBlMTE3M2QzMDg1ODM4ZDAwMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cxVzzQwl1-01SDcQ3W80hLgNiByrhg-dDZ8hODpabPw";
	
	public Optional<List<EpisodeItem>> getEpisodes(int seriesId, int seasonId) {
		String url = "https://api.themoviedb.org/3/tv/" + seriesId + "/season/" + seasonId + "?language=en-US";
		Optional<EpisodeResult> responseData = responseTo(EpisodeResult.class, url);
		if (responseData.isPresent()) {
			return Optional.of(responseData.get().getEpisodes());
		}
		return Optional.empty();
	}
	
	public Optional<List<SeasonItem>> getSeasons(String id) {
		String url = "https://api.themoviedb.org/3/tv/" + id + "?language=en-US";
		Optional<SeasonResult> responseData = responseTo(SeasonResult.class, url);
		if (responseData.isPresent()) {
			return Optional.of(responseData.get().getSeasons());
		}
		return Optional.empty();
	}
	
	private <T> Optional<T> responseTo(Class<T> obj, String url) {
		Optional<HttpResponse<String>> response = fetchResponse(url);
		if (response.isPresent()) {
			HttpResponse<String> resp = response.get();
			ObjectMapper mapper = new ObjectMapper();
			T data;
			try {
				data = mapper.readValue(resp.body(), obj);
			} catch (JsonMappingException e) {
				e.printStackTrace();
				return Optional.empty();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return Optional.empty();
			}
			return Optional.of(data);
		}
		return Optional.empty();
	}
	
	public Optional<String[][]> getCandidates() {
		Optional<String> sanitisedQuery = sanitise(query);
		if (sanitisedQuery.isPresent()) {
			String url = "https://api.themoviedb.org/3/search/tv?query=" + sanitisedQuery.get() + "&include_adult=true&language=en-US&page=1";
			Optional<SearchResult> responseData = responseTo(SearchResult.class, url);
			if (responseData.isPresent()) {
				List<SearchResultItem> results = responseData.get().getResults();
				String[][] r = new String[results.size()][2];
				int count = 0;
				for (SearchResultItem item : results) {
					r[count][0] = String.valueOf(item.getId());
					if (item.getFirstAired().isPresent()) {
						r[count][1] = String.format("%s (%s)", item.getName(), item.getFirstAired().get().getYear());
					} else {
						r[count][1] = item.getName();
					}
					count++;
				}
				return Optional.of(r);
			}
			return Optional.empty();
		}
		return Optional.empty();
	}
	
	private Optional<String> sanitise(String message) {
		try {
			message = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return Optional.empty();
		}
		return Optional.of(message);
	}
	
	private Optional<HttpResponse<String>> fetchResponse(String url) {
		HttpClient httpClient = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.connectTimeout(Duration.ofSeconds(10))
				.build();
		
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
				.setHeader("User-Agent", "Java HttpClient")
				.setHeader("Authorization", token)
				.build();
		
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200) return Optional.empty();
			return Optional.of(response);
			
		} catch (IOException e) {

			e.printStackTrace();
			return Optional.empty();
		
		} catch (InterruptedException e) {
		
			e.printStackTrace();
			return Optional.empty();
		
		}
	}
	
	public Lookup(String query) {		
		this.query = query;
	}
}