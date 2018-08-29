package pl.automic.communication.requests;


import com.uc4.api.UC4ObjectName;
import com.uc4.api.systemoverview.UserListItem;

import pl.automic.helpers.ObjectHelper;

public class OpenObject extends com.uc4.communication.requests.OpenObject {
	
	public OpenObject(String name) {	
		this(new UC4ObjectName(name));
	}
	
	// TODO change type from string to template in version 0.0.2
	public OpenObject(String name, String type) {
		this(ObjectHelper.getUC4Name(name, type));
	}

	public OpenObject(String name, boolean readOnly, boolean fullObject) {
		this(new UC4ObjectName(name), readOnly, fullObject);
	}
	
	public OpenObject(UC4ObjectName arg0, boolean arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	public OpenObject(UC4ObjectName arg0) {
		super(arg0);
	}

	public OpenObject(UserListItem user) {
		super(user);
	}
	
}
