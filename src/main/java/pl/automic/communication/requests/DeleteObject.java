package pl.automic.communication.requests;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.uc4.api.UC4ObjectName;

public class DeleteObject extends com.uc4.communication.requests.DeleteObject {
	
	public DeleteObject(String name) throws IOException {
		this(new UC4ObjectName(name));
	}

	
	public DeleteObject(List<String> names) throws IOException {
		this(names.stream().map(e -> {
			UC4ObjectName name = new UC4ObjectName(e);
			return name;
		}).collect(Collectors.toList()).toArray(new UC4ObjectName[0]));
		
	}
	public DeleteObject(UC4ObjectName arg0) {
		super(arg0);
	}

	public DeleteObject(UC4ObjectName[] arg0) {
		super(arg0);
	}
}
