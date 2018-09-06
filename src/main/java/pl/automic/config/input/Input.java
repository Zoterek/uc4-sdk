package pl.automic.config.input;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Input {
	@JsonProperty(value="client")
	private int client;
	@JsonProperty(value="groups")
	private String[] groups;
	@JsonProperty(value="users")
	private String[] users;
	
	public int getClient() {
		return this.client;
	}
	
	public ArrayList<String> getGroups() {
		return new ArrayList<String>(Arrays.asList(groups));
	}
	
	public ArrayList<String> getUsers() {
		return new ArrayList<String>(Arrays.asList(users));
	}
	
}
