package domain;

public class SelectableItem {
	
	public boolean Selected;
	public String Value;
	
	public SelectableItem(String value) {
		this(false, value);
	}
	
	public SelectableItem(boolean selected, String value) {
		this.Selected = selected;
		this.Value = value;
	}

}
