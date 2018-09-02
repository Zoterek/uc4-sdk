package pl.automic.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReplacementConfig {
	public String pattern;
	public String replacement;
	public String text;
}
