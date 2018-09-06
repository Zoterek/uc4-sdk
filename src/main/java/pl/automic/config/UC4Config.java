package pl.automic.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UC4Config {
	@JsonProperty(value="connection")
	private ConnectionConfig connection;
	@JsonProperty(value="searchFilter")
	private SearchFilterConfig searchFilter;
	@JsonProperty(value="options")
	private OptionsConfig options;
	

	public ConnectionConfig getConnectionConfig() {
		return this.connection;
	}
	
	public SearchFilterConfig getSearchFilter() {
		return this.searchFilter;
	}

	public OptionsConfig getOptionsConfig() {
		return this.options;
	}
}
