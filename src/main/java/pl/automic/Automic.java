package pl.automic;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uc4.api.objects.UC4Object;
import com.uc4.communication.Connection;
import com.uc4.communication.requests.CloseObject;
import com.uc4.communication.requests.CreateSession;
import com.uc4.communication.requests.SaveObject;
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
	 */
	public Automic(ConnectionConfig config) throws IOException {
		connect(config);
	}
	
	public void send(XMLRequest req) throws IOException {
		uc4.sendRequestAndWait(req);
		if (req.getMessageBox() != null) throw new RuntimeException(req.getMessageBox().getText());	
	}
	
	public SaveObject save(UC4Object obj) {
		try {
			SaveObject save = new SaveObject(obj);
			this.send(save);
			
			return save;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CloseObject close(UC4Object obj) {
		try {
			CloseObject close = new CloseObject(obj);
			this.send(close);
			
			return close;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void exit() throws IOException {
		uc4.close();
		System.exit(0);
	}
	
	private void connect(ConnectionConfig cc) throws IOException {
		uc4 = Connection.open(cc.host, cc.port);
		CreateSession login = uc4.login(cc.client, cc.user, cc.department, cc.password, 'E');
		if (!login.isLoginSuccessful()) throw new IllegalArgumentException(login.getMessageBox().getText());
	}
}
