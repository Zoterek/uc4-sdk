package pl.automic.communication.requests;

import com.uc4.api.Template;
import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.IFolder;

import pl.automic.helpers.ObjectHelper;

public class CreateObject extends com.uc4.communication.requests.CreateObject {
	
	public CreateObject(String name, Template template, IFolder folder) {
		this(ObjectHelper.getUC4Name(name, template.getTemplateName()), template, folder);
	}
	
	public CreateObject(UC4ObjectName arg0, Template arg1, IFolder arg2) {
		super(arg0, arg1, arg2);
	}
	
}
