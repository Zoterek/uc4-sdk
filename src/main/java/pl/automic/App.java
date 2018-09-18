package pl.automic;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.automic.config.OptionsConfig;
import pl.automic.config.UC4Config;

public class App {
	protected Automic automic;
	protected UC4Config config;
	protected OptionsConfig options;
	protected String configRoot;
	
	public App(String[] args) throws IOException {
		File configFile = new File(readArg(0, args, "../config/uc4.config.json"));
		this.config = new ObjectMapper().readValue(configFile, UC4Config.class);
		this.configRoot = configFile.getParent().replaceAll("\\\\", "/") + "/";
		this.options = config.getOptionsConfig();
		
		try {
			this.automic = new Automic(config.getConnectionConfig());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	protected void exit() {
		try {
			this.automic.exit();
		} catch (IOException e) {
			
		}
	}
	
	protected String readArg(int pos, String[] args, String defaultValue) {
		return args.length > pos ? args[pos] : defaultValue;
	}
}
