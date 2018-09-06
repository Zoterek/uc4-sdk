package pl.automic;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.automic.config.OptionsConfig;
import pl.automic.config.UC4Config;
import pl.automic.config.input.Input;

public class App {
	private OptionsConfig options;
	
	protected Automic automic;
	protected Input input;
	protected UC4Config config;
	
	public App(String[] args) throws IOException {
		File configFile = new File(readArg(0, args, "uc4.config.json"));
		this.config = new ObjectMapper().readValue(configFile, UC4Config.class);
		this.options = config.getOptionsConfig();
		
		if(options.inputFileName != null) {
			File inputFile = new File(readArg(1, args, options.inputFileName));
			this.input = new ObjectMapper().readValue(inputFile, Input.class);
		}
		
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
