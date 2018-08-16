package pl.automic.communication.requests.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Replacement {
	public String pattern;
	public String replacement;
	public String text;
}
