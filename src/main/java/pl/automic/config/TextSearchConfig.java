package pl.automic.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TextSearchConfig {
	public boolean process;
	public String text;
}
