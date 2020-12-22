package pl.automic.communication.requests;

import java.io.IOException;

import com.uc4.api.Template;
import com.uc4.api.UC4Alias;
import com.uc4.api.UC4HostName;
import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.AttributesSAP;
import com.uc4.api.objects.FileTransfer;
import com.uc4.api.objects.FileTransferSettings;
import com.uc4.api.objects.IFolder;
import com.uc4.api.objects.Job;
import com.uc4.api.objects.JobPlan;
import com.uc4.api.objects.JobPlanTask;
import com.uc4.api.objects.Variable;

import pl.automic.Automic;
import pl.automic.communication.requests.exceptions.UnsupportedTypeException;
import pl.automic.helpers.ObjectAttributes;
import pl.automic.helpers.ObjectHelper;

public class CreateObject extends com.uc4.communication.requests.CreateObject {
	Automic automic;
	
	public CreateObject(String name, Template template, IFolder folder) {
		this(ObjectHelper.getUC4Name(name, template.getTemplateName()), template, folder);
	}
	
	public CreateObject(UC4ObjectName arg0, Template arg1, IFolder arg2) {
		super(arg0, arg1, arg2);
	}
	
	public CreateObject(ObjectAttributes att, IFolder folder, Automic automic) throws IOException, UnsupportedTypeException {
		this(att.name, Template.getTemplateFor(att.template), folder);
		this.automic = automic;
		automic.send(this);

		@SuppressWarnings("unused")
		AlterObject alterObject = new AlterObject(att, automic);
		
//		Moved to AlterObject
//		
//		OpenObject open = new OpenObject(att.name);
//		automic.send(open);
//		
//		if(Template.getTemplateFor(att.template).getType().equals("JOBS")) {
//			Job job = alterJob(att, (Job) open.getUC4Object());
//			automic.save(job);
//			automic.close(job);
//		} else if(Template.getTemplateFor(att.template).getType().equals("JOBF")) {
//			FileTransfer fileTransfer = alterFileTransfer(att, (FileTransfer) open.getUC4Object());
//			automic.save(fileTransfer);
//			automic.close(fileTransfer);
//		} else if(Template.getTemplateFor(att.template).getType().equals("JOBP")) {
//			JobPlan jobPlan = alterJobPlan(att, (JobPlan) open.getUC4Object());
//			automic.save(jobPlan);
//			automic.close(jobPlan);
//		} else if(Template.getTemplateFor(att.template).getType().equals("VARA")) {
//			Variable variable = alterVariable(att, (Variable) open.getUC4Object());
//			automic.save(variable);
//			automic.close(variable);
//		}
		
	}
	
	private Job alterJob(ObjectAttributes att, Job job) {
		if(att.archive1 != null) {
			job.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			job.header().setArchiveKey2(att.archive2);
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
		if(job.hostAttributes() instanceof AttributesSAP) {
			AttributesSAP sap = (AttributesSAP) job.hostAttributes();
			
			if(att.sap != null) {
				sap.setDeleteJob(att.sap.delete);
				
			}
		}
		
		return job;
	}
	
	private JobPlan alterJobPlan(ObjectAttributes att, JobPlan obj) {
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.process != null) {
			obj.setProcess(att.process);
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				obj.values().addValue(k, v, true);
			});
		}
		// Jobplan specific attributes
		if(att.jobPlan.tasks != null) {
			att.jobPlan.tasks.forEach(task -> {
				JobPlanTask jpt;
				
				if(task.name.equals("START")) {
					jpt = obj.getStartTask();
				} else if(task.name.equals("END")) {
					jpt = obj.getEndTask();
				} else {
					AddJobPlanTask ajpt;
					if(task.alias != null) {
						ajpt = new AddJobPlanTask(new UC4ObjectName(task.name), new UC4Alias(task.alias));
					} else {
						ajpt = new AddJobPlanTask(new UC4ObjectName(task.name));
					}
						
					
					try {
						automic.send(ajpt);
					} catch (IOException e) {
						System.err.println("Error while creating task: " + task.name);
						e.printStackTrace();
					}
					
					jpt = ajpt.getJobPlanTask();
					jpt.setUserDefinedID(task.id);
					obj.addTask(jpt);
				}
				
				// Add dependencies
				for(int id : task.predecessor) {
					if(id == 0 && task.dependencyStatus != null) {
						jpt.dependencies().addDependency(obj.getStartTask(), task.dependencyStatus);
					} else if(id == 0) {
						jpt.dependencies().addDependency(obj.getStartTask());
					} else if(task.dependencyStatus != null) {
						jpt.dependencies().addDependency(obj.getTaskByUserDefinedID(String.valueOf(id)), task.dependencyStatus);
					} else {
						jpt.dependencies().addDependency(obj.getTaskByUserDefinedID(String.valueOf(id)));
					}
					
				}

				// Set else
				if(task.onFailure != null) {
					jpt = setElse(jpt, task.onFailure);
				}
				
				if(task.hasEarliest()) {
					jpt.earliest().setEarliestStart(true);
					jpt.earliest().setTime(task.time);
					jpt.earliest().setDaysAfterJobPlanActivation(task.daysAfterJobPlanActivation);
				}
				
				if(task.hasCalendarCondiiton()) {
					jpt.calendar().addCalendarCondition(task.getCalendarCondition());
				}
				
				
				obj.format();
				
			});
		}
		
		return obj;
	}
	
	public JobPlanTask setElse(JobPlanTask jpt, String onFailure) {
		switch (onFailure) {
		case "SKIP":
			jpt.dependencies().setElseSkip();
			break;
		case "BLOCK":
			jpt.dependencies().setElseBlock();
			break;
		case "ABORT":
			jpt.dependencies().setElseAbort();
			break;
		}
		return jpt;
	}
	
	private FileTransfer alterFileTransfer(ObjectAttributes att, FileTransfer obj) {
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
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
	
	private Variable alterVariable(ObjectAttributes att, Variable obj) {
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				obj.add(k, v);
			});
		}
		
		
		return obj;
	}
	
}
