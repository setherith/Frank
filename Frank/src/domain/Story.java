package domain;

import java.util.ArrayList;
import java.util.List;

public class Story {

	private List<String> Names;
	
	public Story(String originalName) {
		Names = new ArrayList<String>();
		Names.add(originalName);
	}
	
	public String getOriginalName() {
		return Names.get(0);
	}
	
	public String getLatestName() {
		if (Names.size() > 0) {
			return Names.get(Names.size() - 1);
		} else {
			return getOriginalName();
		}
	}
	
	public void updateName(String name) {
		Names.add(name);
	}
	
}
