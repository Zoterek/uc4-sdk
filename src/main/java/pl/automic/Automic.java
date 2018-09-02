package pl.automic;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uc4.communication.Connection;
import com.uc4.communication.requests.CreateSession;
import com.uc4.communication.requests.XMLRequest;

import pl.automic.config.ConnectionConfig;
import pl.automic.config.UC4Config;

public class Automic {
	private Connection uc4;
	
	public Automic(String jsonString) throws IOException {
		UC4Config config = new ObjectMapper().readValue(jsonString, UC4Config.class);
		connect(config.getConnectionConfig());
	}
	
	public Automic(File file) throws IOException {
		UC4Config config = new ObjectMapper().readValue(file, UC4Config.class);
		connect(config.getConnectionConfig());
	}
	
	/*
	 * Version 0.0.2
	 * 
	 * Proposal:
	 * Might need to create a private UC4Config object for the whole class
	 */
	public Automic(UC4Config config) throws IOException {
		connect(config.getConnectionConfig());
	}
	
	public void send(XMLRequest req) throws IOException {
		uc4.sendRequestAndWait(req);
		if (req.getMessageBox() != null) throw new RuntimeException(req.getMessageBox().getText());	
	}
	
	public void exit() throws IOException {
		uc4.close();
	}
	
	private void connect(ConnectionConfig cc) throws IOException {
		uc4 = Connection.open(cc.host, cc.port);
		CreateSession login = uc4.login(cc.client, cc.user, cc.department, cc.password, 'E');
		if (!login.isLoginSuccessful()) throw new IllegalArgumentException(login.getMessageBox().getText());
	}
}
