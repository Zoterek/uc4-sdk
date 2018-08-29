package pl.automic.helpers;

import com.uc4.api.UC4ObjectName;
import com.uc4.api.UC4UserName;

public class ObjectHelper {
	
	// TODO change string type to template in version 0.0.2
	public static UC4ObjectName getUC4Name(String name, String type) {
		if(type.equalsIgnoreCase("USER") || type.equalsIgnoreCase("USERG")) {
			return new UC4UserName(name);
		}
		
		return new UC4ObjectName(name);
	}
}
