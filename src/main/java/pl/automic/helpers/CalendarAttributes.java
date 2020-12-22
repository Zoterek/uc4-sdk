package pl.automic.helpers;

import java.util.ArrayList;
import java.util.List;

public class CalendarAttributes {
	public int index;
	public List<CalendarKeywordAttributes> keywords;
	public String name;
	
	public CalendarAttributes() {
		this.keywords = new ArrayList<>();
	}
}
