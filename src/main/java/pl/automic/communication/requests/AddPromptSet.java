package pl.automic.communication.requests;

import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.UC4Object;

public class AddPromptSet extends com.uc4.communication.requests.AddPromptSet {

	public AddPromptSet(UC4ObjectName arg0, UC4Object arg1) {
		super(arg0, arg1);
	}
	
	public AddPromptSet(String arg0, UC4Object arg1) {
		super(new UC4ObjectName(arg0), arg1);
	}

}
