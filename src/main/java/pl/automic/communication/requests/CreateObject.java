package pl.automic.communication.requests;

import com.uc4.api.Template;
import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.IFolder;

public class CreateObject extends com.uc4.communication.requests.CreateObject {

	public CreateObject(String arg0, Template arg1, IFolder arg2) {
		this(new UC4ObjectName(arg0), arg1, arg2);
	}
	
	public CreateObject(UC4ObjectName arg0, Template arg1, IFolder arg2) {
		super(arg0, arg1, arg2);
	}
	
}
