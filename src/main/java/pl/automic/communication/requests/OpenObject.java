package pl.automic.communication.requests;


import com.uc4.api.UC4ObjectName;
import com.uc4.api.UC4UserName;
import com.uc4.api.systemoverview.UserListItem;

public class OpenObject extends com.uc4.communication.requests.OpenObject {
	
	public OpenObject(String arg0) {		
		this(new UC4ObjectName(arg0));
	}
	
	public OpenObject(String name, String type) {
		this(getUC4Name(name, type));
	}

	public OpenObject(String arg0, boolean arg1, boolean arg2) {
		this(new UC4ObjectName(arg0), arg1, arg2);
	}
	
	public OpenObject(UC4ObjectName arg0, boolean arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	public OpenObject(UC4ObjectName arg0) {
		super(arg0);
	}

	public OpenObject(UserListItem arg0) {
		super(arg0);
	}
	
	private static UC4ObjectName getUC4Name(String name, String type) {
		if(type.equalsIgnoreCase("USER") || type.equalsIgnoreCase("USERG")) {
			return new UC4UserName(name);
		}
		
		return new UC4ObjectName(name);
	}
}
