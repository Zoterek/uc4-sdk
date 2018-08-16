package pl.automic.communication.requests;

import com.uc4.api.UC4ObjectName;

public class GetObjectProperties extends com.uc4.communication.requests.GetObjectProperties{

	public GetObjectProperties(String arg0) {
		this(new UC4ObjectName(arg0));
	}
	
	public GetObjectProperties(int arg0) {
		super(arg0);
	}

	public GetObjectProperties(UC4ObjectName arg0) {
		super(arg0);
	}

}
