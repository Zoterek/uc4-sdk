package pl.automic.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionsConfig {
	public String inputFileName;
	// TEMP for uc4_adjust_cale
	public String vara;
	public boolean verbose;
	

}
