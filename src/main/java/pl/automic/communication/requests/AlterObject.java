package pl.automic.communication.requests;

import java.io.IOException;

import com.uc4.api.DateTime;
import com.uc4.api.Template;
import com.uc4.api.UC4Alias;
import com.uc4.api.UC4HostName;
import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.AttributesSAP;
import com.uc4.api.objects.Calendar;
import com.uc4.api.objects.CalendarKeyword;
import com.uc4.api.objects.FileEvent;
import com.uc4.api.objects.FileTransfer;
import com.uc4.api.objects.FileTransferSettings;
import com.uc4.api.objects.Job;
import com.uc4.api.objects.JobPlan;
import com.uc4.api.objects.JobPlanTask;
import com.uc4.api.objects.Login;
import com.uc4.api.objects.PromptSet;
import com.uc4.api.objects.Script;
import com.uc4.api.objects.StaticCalendarKeyword;
import com.uc4.api.objects.Sync;
import com.uc4.api.objects.TaskState;
import com.uc4.api.objects.TimeEvent;
import com.uc4.api.objects.UC4Object;
import com.uc4.api.objects.Variable;

import pl.automic.Automic;
import pl.automic.communication.requests.exceptions.UnsupportedTypeException;
import pl.automic.helpers.ObjectAttributes;
import pl.automic.helpers.ObjectHelper;

public class AlterObject {
	Automic automic;
	public String currentObject;
	
	public AlterObject(ObjectAttributes att, Automic automic) throws IOException, UnsupportedTypeException {
		this.automic = automic;
		
		OpenObject open = new OpenObject(att.name);
		automic.send(open);
		UC4Object obj = open.getUC4Object();
		
		if(obj instanceof Job) { // Template.getTemplateFor(att.template).getType().equals("JOBS")
			Job job = alterJob(att, (Job) obj);
			automic.save(job);
			automic.close(job);
		} else if(obj instanceof FileTransfer) {
			FileTransfer fileTransfer = alterFileTransfer(att, (FileTransfer) obj);
			automic.save(fileTransfer);
			automic.close(fileTransfer);
		} else if(obj instanceof JobPlan) {
			JobPlan jobPlan = alterJobPlan(att, (JobPlan) obj);
			automic.save(jobPlan);
			automic.close(jobPlan);
		} else if(obj instanceof Variable) {
			Variable variable = alterVariable(att, (Variable) obj);
			automic.save(variable);
			automic.close(variable);
		} else if(obj instanceof Script) {
			Script script = alterScript(att, (Script) obj);
			automic.save(script);
			automic.close(script);
			
		} else if(obj instanceof Calendar) {
			Calendar calendar = alterCalendar(att, (Calendar) obj);
			automic.save(calendar);
			automic.close(calendar);
		} else if(obj instanceof Sync) {
			Sync sync = alterSync(att, (Sync) obj);
			automic.save(sync);
			automic.close(sync);
		} else if(obj instanceof Login) {
			Login login = alterLogin(att, (Login) obj);
			automic.save(login);
			automic.close(login);
		} else if(obj instanceof FileEvent) {
			FileEvent fileEvent = alterFileEvent(att, (FileEvent) obj);
			automic.save(fileEvent);
			automic.close(fileEvent);
		} else if(obj instanceof TimeEvent) {
			TimeEvent timeEvent = alterTimeEvent(att, (TimeEvent) obj);
			automic.save(timeEvent);
			automic.close(timeEvent);
		} else if(obj instanceof PromptSet) {
			PromptSet promptSet = alterPromptSet(att, (PromptSet) obj);
			automic.save(promptSet);
			automic.close(promptSet);
		}
		
		
		else {
			throw new UnsupportedTypeException("Object: " + att.name + " is not supported by AlterObject class.");
			
		}
		
		
	}
	
	
	private TimeEvent alterTimeEvent(ObjectAttributes att, TimeEvent obj) {
		if(att.active != null) {
			obj.header().setActive(ObjectHelper.stringToBoolean(att.active, true));
		}
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		if(att.process != null) {
			obj.setProcess(att.process);
		}

		return obj;
	}


	private PromptSet alterPromptSet(ObjectAttributes att, PromptSet obj) {
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		
		return obj;
	}


	private FileEvent alterFileEvent(ObjectAttributes att, FileEvent obj) {
		if(att.active != null) {
			obj.header().setActive(ObjectHelper.stringToBoolean(att.active, true));
		}
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		if(att.process != null) {
			obj.setProcess(att.process);
		}
		
		return obj;
	}


	private Login alterLogin(ObjectAttributes att, Login obj) {
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		return obj;
	}


	private Sync alterSync(ObjectAttributes att, Sync obj) {
		if(att.active != null) {
			obj.header().setActive(ObjectHelper.stringToBoolean(att.active, true));
		}
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		
		return obj;
	}


	private Script alterScript(ObjectAttributes att, Script obj) throws IOException {
		if(att.active != null) {
			obj.header().setActive(ObjectHelper.stringToBoolean(att.active, true));
		}
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.process != null) {
			obj.setProcess(att.process);
		}
		if(att.syncProperty != null && att.syncProperty.syncList != null) {
			att.syncProperty.syncList.forEach(sync -> {
				obj.syncs().addSyncItem(sync.getSyncItem());
			});
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				obj.values().addValue(k, v, true);
			});
		}
		if(att.prompt != null && att.prompt.list != null) {
			for(String prompt : att.prompt.list) {
				AddPromptSet aps = new AddPromptSet(prompt, obj);
				automic.send(aps);
				obj.values().addPromptSet(aps.getPromptSet());
			}
		}
		
		
		return obj;
		
	}


	private Calendar alterCalendar(ObjectAttributes att, Calendar cale) throws IOException {
		if(att.archive1 != null) {
			cale.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			cale.header().setArchiveKey2(att.archive2);
		}
		if(att.title != null) {
			cale.header().setTitle(att.title);
		}
		if(att.calendar.keywords != null) {
			att.calendar.keywords.forEach(keyword -> {
				CalendarKeyword ck = cale.getCalendarkeyword(new UC4ObjectName(keyword.name));
				if(ck.isStatic()) {
					StaticCalendarKeyword sck = (StaticCalendarKeyword) ck;
					
					if(keyword.remove) {
						sck.remove(new DateTime(keyword.date));
					} else {
						sck.add(new DateTime(keyword.date));
					}
				} else {
					// Can't do this because it is in a lambda
					//throw new IOException(keyword.name + " is not static");
					
				}
			});
		}
		
		return cale;
	}
	
	private Job alterJob(ObjectAttributes att, Job job) throws IOException {
		if(att.active != null) {
			job.header().setActive(ObjectHelper.stringToBoolean(att.active, true));
		}
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
		if(att.syncProperty != null && att.syncProperty.syncList != null) {
			att.syncProperty.syncList.forEach(sync -> {
				job.syncs().addSyncItem(sync.getSyncItem());
			});
		}
		if(att.title != null) {
			job.header().setTitle(att.title);
		}
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				job.values().addValue(k, v, true);
			});
		}
		if(att.prompt != null && att.prompt.list != null) {
			for(String prompt : att.prompt.list) {
				AddPromptSet aps = new AddPromptSet(prompt, job);
				automic.send(aps);
				job.values().addPromptSet(aps.getPromptSet());
			}
		}
		if(job.hostAttributes() instanceof AttributesSAP) {
			AttributesSAP sap = (AttributesSAP) job.hostAttributes();
			
			if(att.sap != null) {
				sap.setDeleteJob(att.sap.delete);
				
			}
		}
		
		return job;
	}
	
	private JobPlan alterJobPlan(ObjectAttributes att, JobPlan obj) throws IOException {
		if(att.active != null) {
			obj.header().setActive(ObjectHelper.stringToBoolean(att.active, true));
		}
		if(att.archive1 != null) {
			obj.header().setArchiveKey1(att.archive1);
		}
		if(att.archive2 != null) {
			obj.header().setArchiveKey2(att.archive2);
		}
		if(att.process != null) {
			obj.setProcess(att.process);
		}
		if(att.syncProperty != null && att.syncProperty.syncList != null) {
			att.syncProperty.syncList.forEach(sync -> {
				obj.syncs().addSyncItem(sync.getSyncItem());
			});
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				obj.values().addValue(k, v, true);
			});
		}
		if(att.prompt != null && att.prompt.list != null) {
			for(String prompt : att.prompt.list) {
				AddPromptSet aps = new AddPromptSet(prompt, obj);
				automic.send(aps);
				obj.values().addPromptSet(aps.getPromptSet());
			}
		}
		// Jobplan specific attributes
		if(att.jobPlan != null && att.jobPlan.tasks != null) {
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
					
					ajpt.setExternal(task.isExternal());
					
					try {
						automic.send(ajpt);
					} catch (IOException e) {
						System.err.println("Error while creating task: " + task.name);
						e.printStackTrace();
					}
					
					jpt = ajpt.getJobPlanTask();
					jpt.setUserDefinedID(task.id);
					
					if(jpt.isExternal()) {
						if(task.externalParent != null) {
							jpt.external().setExternalParent(task.getExternalParent());
						}
						
						// TODO temporary solution for Rafal
						jpt.external().setSltAfterJobPlanStart();
						jpt.external().setExpectedStatus(TaskState.ANY_OK_OR_UNBLOCKED);
					}
					
					
					
					obj.addTask(jpt);
				}
				
				// Add dependencies
				if(task.predecessor != null) {
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
				}
				
				// Add successors
				if(task.successor != null) {
					for(String successor : task.successor) {
						if(task.dependencyStatus != null) {
							obj.getFirstTaskByName(successor).dependencies().addDependency(jpt, task.dependencyStatus);
						} else {
							obj.getFirstTaskByName(successor).dependencies().addDependency(jpt);
						}
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
	
	private FileTransfer alterFileTransfer(ObjectAttributes att, FileTransfer obj) throws IOException {
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
		if(att.syncProperty != null && att.syncProperty.syncList != null) {
			att.syncProperty.syncList.forEach(sync -> {
				obj.syncs().addSyncItem(sync.getSyncItem());
			});
		}
		if(att.title != null) {
			obj.header().setTitle(att.title);
		}
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				obj.values().addValue(k, v, true);
			});
		}
		if(att.prompt != null && att.prompt.list != null) {
			for(String prompt : att.prompt.list) {
				AddPromptSet aps = new AddPromptSet(prompt, obj);
				automic.send(aps);
				obj.values().addPromptSet(aps.getPromptSet());
			}
		}
		
		return obj;
	}
	
	private Variable alterVariable(ObjectAttributes att, Variable obj) {
		if(att.variable != null) {
			att.variable.map.forEach((k, v) -> {
				if(att.secondaryAction != null) {
					switch (att.secondaryAction) {
					case "remove":
						obj.remove(k);
						break;
					case "replace":
						replaceVariableRow(obj, k, v, obj.getRow(k));
						break;
					default:
						obj.add(k, v);
						break;
					}
				} else {
					obj.add(k, v);
				}
			});
		}
		
		
		return obj;
	}
	
	private void replaceVariableRow(Variable obj, String k, String v, String[] row) {
		String[] arr = {v, "", "", "", ""};
		if(obj.remove(k)) {
			for(int i = 1; i < row.length; i++) {
				arr[i] = row[i];
			}
			
			obj.add(k, arr[0], arr[1], arr[2], arr[3], arr[4]);
		}
		
	}
	
}
