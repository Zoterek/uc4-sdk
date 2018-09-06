package pl.automic.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchFilterConfig {
	public boolean searchForUse;
	
	public String dateSelection;
	public String from;
	public String name;
	public String to;
	public String[] type;
	
	public TextSearchConfig textSearch;
	// TODO
	public ReplacementConfig replace;
}
