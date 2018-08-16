package pl.automic.communication.requests.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Filter {
	public boolean searchForUse;
	
	public String dateSelection;
	public String from;
	public String name;
	public String to;
	
	public TextSearch textSearch;
	public Replacement replace;
}
