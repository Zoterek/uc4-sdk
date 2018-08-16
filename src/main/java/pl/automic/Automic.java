package pl.automic;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uc4.communication.Connection;
import com.uc4.communication.requests.CreateSession;
import com.uc4.communication.requests.XMLRequest;

public class Automic {
	private Connection uc4;
	private Config config;
	
	public Automic(String arg0) throws IOException {
		config = new ObjectMapper().readValue(arg0, Config.class);
		connect();
	}
	
	public Automic(File arg0) throws IOException {
		config = new ObjectMapper().readValue(arg0, Config.class);
		connect();
	}
	
	public void send(XMLRequest req) throws IOException {
		uc4.sendRequestAndWait(req);
		if (req.getMessageBox() != null) throw new RuntimeException(req.getMessageBox().getText());	
	}
	
	public void exit() throws IOException {
		uc4.close();
	}
	
	private void connect() throws IOException {
		uc4 = Connection.open(config.host, config.port);
		CreateSession login = uc4.login(config.client, config.user, config.department, config.password, 'E');
		if (!login.isLoginSuccessful()) throw new IllegalArgumentException(login.getMessageBox().getText());
	}
}
