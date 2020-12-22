package pl.automic.helpers;

public class CalendarKeywordAttributes {
	public String date;
	public String name;
	public boolean remove;
	

	
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRemove(String remove) {
		this.remove = ObjectHelper.stringToBoolean(remove);
	}
}
