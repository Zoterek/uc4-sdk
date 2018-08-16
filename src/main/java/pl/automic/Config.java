package pl.automic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class Config {
	public String host;
	public int port;
	public int client;
	public String user;
	public String department;
	public String password;
	
}
