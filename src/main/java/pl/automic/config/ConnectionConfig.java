package pl.automic.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionConfig {
	public String host;
	public int port;
	public int client;
	public String user;
	public String department;
	public String password;
	
}
