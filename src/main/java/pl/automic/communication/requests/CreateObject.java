package pl.automic.communication.requests;

import java.io.IOException;

import com.uc4.api.Template;
import com.uc4.api.UC4HostName;
import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.AttributesSAP;
import com.uc4.api.objects.FileTransfer;
import com.uc4.api.objects.FileTransferSettings;
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
		} else if(Template.getTemplateFor(att.template).getType().equals("JOBF")) {
			FileTransfer fileTransfer = createFileTransfer(att, (FileTransfer) open.getUC4Object());
			automic.save(fileTransfer);
			automic.close(fileTransfer);
		}
		
	}
	
	private Job createJob(ObjectAttributes att, Job job) {
		if(att.archive1 != null) {
			job.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			job.header().setArchiveKey2(att.archive2);
		}
		if(att.description != null) {
			// TODO
		}
		if(att.host != null) {
			job.attributes().setHost(new UC4HostName(att.host));
		}
		if(job.hostAttributes() instanceof AttributesSAP) {
			// TODO
		}
		if(att.login != null) {
			job.attributes().setLogin(new UC4ObjectName(att.login));
		}
		if(att.preProcess != null) {
			job.setPreProcess(att.preProcess);
		}
		if(att.process != null) {
			job.setProcess(att.process);
			
			if(att.template.equals("JOBS.WIN")) {
				job.setProcess(job.getProcess()
						+ "\n\n"
						+ "\n@set retcode=%errorlevel%"
						+ "\n@if NOT %ERRORLEVEL% == 0 goto :retcode"
						+ "\n");
			}
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
	
	private FileTransfer createFileTransfer(ObjectAttributes att, FileTransfer obj) {
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.description != null) {
			// TODO
		}
		if(att.host != null) {
			obj.settings().setSourceHost(new UC4HostName(att.host));
		}
		if(att.fileTransfer.eraseSource) {
			obj.settings().setEraseSource(att.fileTransfer.eraseSource);
		}
		if(att.fileTransfer.destinationHost != null) {
			obj.settings().setDestinationHost(new UC4HostName(att.fileTransfer.destinationHost));
		}
		if(att.fileTransfer.destinationLogin != null) {
			obj.settings().setDestinationLogin(new UC4ObjectName(att.fileTransfer.destinationLogin));
		}
		if(att.fileTransfer.fileExistsAction != null) {
			switch (att.fileTransfer.fileExistsAction.toUpperCase()) {
			case "CANCEL":
				obj.settings().setFileExistsAction(FileTransferSettings.FILE_EXISTS_CANCEL);
				break;
			case "OVERWRITE":
				obj.settings().setFileExistsAction(FileTransferSettings.FILE_EXISTS_OVERWRITE);
				break;
			case "APPEND":
				obj.settings().setFileExistsAction(FileTransferSettings.FILE_EXISTS_APPEND);
				break;
			}
		}
		// TEXT = true, BINARY = false
		if(!att.fileTransfer.format) {
			obj.settings().setFormatBinary();
		}
		if(att.fileTransfer.source != null) {
			obj.settings().setSourceFile(att.fileTransfer.source);
		}
		if(att.fileTransfer.target != null) {
			obj.settings().setDestinationFile(att.fileTransfer.target);
		}
		if(att.fileTransfer.useWildcards) {
			obj.settings().setWildcards(att.fileTransfer.useWildcards);
		}
		if(att.login != null) {
			obj.settings().setSourceLogin(new UC4ObjectName(att.login));
		}
		if(att.process != null) {
			obj.setProcess(att.process);
		}
		if(att.postProcess != null) {
			obj.setPostProcess(att.postProcess);
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				obj.values().addValue(k, v, true);
			});
		}
		
		return obj;
	}
	
}
