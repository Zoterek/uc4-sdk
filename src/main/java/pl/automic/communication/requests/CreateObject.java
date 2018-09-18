package pl.automic.communication.requests;

import java.io.IOException;

import com.uc4.api.Template;
import com.uc4.api.UC4HostName;
import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.IFolder;
import com.uc4.api.objects.Job;

import pl.automic.Automic;
import pl.automic.helpers.ObjectAttributes;
import pl.automic.helpers.ObjectHelper;

public class CreateObject extends com.uc4.communication.requests.CreateObject {
	
	public CreateObject(String name, Template template, IFolder folder) {
		this(ObjectHelper.getUC4Name(name, template.getTemplateName()), template, folder);
	}
	
	public CreateObject(UC4ObjectName arg0, Template arg1, IFolder arg2) {
		super(arg0, arg1, arg2);
	}
	
	public CreateObject(ObjectAttributes att, IFolder folder, Automic automic) throws IOException {
		this(att.name, Template.getTemplateFor(att.template), folder);
		automic.send(this);
		
		OpenObject open = new OpenObject(att.name);
		automic.send(open);
		
		if(Template.getTemplateFor(att.template).getType().equals("JOBS")) {
			Job job = createJob(att, (Job) open.getUC4Object());
			automic.save(job);
			automic.close(job);
		}
		
	}
	
	private Job createJob(ObjectAttributes att, Job job) {
		if(att.archive1 != null) {
			job.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			job.header().setArchiveKey2(att.archive2);
		}
		if(att.host != null) {
			job.attributes().setHost(new UC4HostName(att.host));
		}
		if(att.login != null) {
			job.attributes().setLogin(new UC4ObjectName(att.login));
		}
		if(att.preProcess != null) {
			job.setPreProcess(att.preProcess);
		}
		if(att.process != null) {
			job.setProcess(att.process);
		}
		if(att.postProcess != null) {
			job.setPostProcess(att.postProcess);
		}
		if(att.title != null) {
			job.header().setTitle(att.title);
		}
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				job.values().addValue(k, v, true);
			});
		}
		
		return job;
	}
	
}
