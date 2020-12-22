package pl.automic.helpers;

import java.lang.reflect.Field;

import pl.automic.helpers.lists.PromptList;
import pl.automic.helpers.lists.SyncList;
import pl.automic.helpers.lists.properties.SyncProperties;

public class ObjectAttributes {
	// Note: not sure if action belongs here
	public String action;
	public String secondaryAction;
	
	public String active;
	public String archive1;
	public String archive2;
	public String host;
	public String id;
	public String login;
	public String name;
	public PromptList prompt;
	public String preProcess;
	public String process;
	public String postProcess;
	public String template;
	public String title;
	public CalendarAttributes calendar;
	public FileTransferAttributes fileTransfer;
	public JobPlanAttributes jobPlan;
	public SyncList syncProperty;
	// Might need to change class name to VariableAttributes
	public AttributeGroup variable;
	public SapAttributes sap;
	// public String variableName;
	// public String variableValue;
	
	public ObjectAttributes() {
		fileTransfer = new FileTransferAttributes();
	}
	
	
	
	public void setAction(String value) {
		if(value != null && !value.equals("")) {
			this.action = value.trim();
		}
	}
	
	public void setSecondaryAction(String value) {
		if(value != null && !value.equals("")) {
			this.secondaryAction = value.trim();
		}
	}
	
	
	public void setActive(String value) {
		if(value != null && !value.equals("")) {
			this.active = value.trim();
		}
	}
	
	public void setArchive1(String value) {
		if(value != null && !value.equals("")) {
			this.archive1 = value.trim();
		}
	}
	
	public void setArchive2(String value) {
		if(value != null && !value.equals("")) {
			this.archive2 = value.trim();
		}
	}
	
	public void setHost(String value) {
		if(value != null && !value.equals("")) {
			this.host = value.trim();
		}
	}
	
	public void setId(String value) {
		if(value != null && !value.equals("")) {
			this.id = value.trim();
		}
	}
	
	public void setLogin(String value) {
		if(value != null && !value.equals("")) {
			this.login = value.trim();
		}
	}
	
	public void setName(String value) {
		if(value != null && !value.equals("")) {
			this.name = value.trim();
		}
	}
	
	/*
	 * START Calendar
	 */
	private String setCalendarValue(String value) {
		if(this.calendar == null) {
			this.calendar = new CalendarAttributes();
		}
		
		if(value != null && !value.trim().equals("")) {
			return value.trim();
		} else {
			return null;
		}
	}
	
	public void setCalendarName(String value, int index) {
		value = setCalendarValue(value);
		if(value != null) {
			CalendarKeywordAttributes ck;
			if(this.calendar.index == index) {
				ck = this.calendar.keywords.remove(this.calendar.keywords.size() - 1);
				ck.setName(value);
			} else {
				ck = new CalendarKeywordAttributes();
				ck.setName(value);
			}
			calendar.keywords.add(ck);
		}
		
		this.calendar.index = index;
	}
	
	public void setCalendarDate(String value, int index) {
		value = setCalendarValue(value);
		if(value != null) {
			CalendarKeywordAttributes ck;
			if(this.calendar.index == index) {
				ck = this.calendar.keywords.remove(this.calendar.keywords.size() - 1);
				ck.setDate(value);
			} else {
				ck = new CalendarKeywordAttributes();
				ck.setDate(value);
			}
			calendar.keywords.add(ck);
		}
		
		this.calendar.index = index;
	}
	
	public void setCalendarRemove(String value, int index) {
		value = setCalendarValue(value);
		if(value != null) {
			CalendarKeywordAttributes ck;
			if(this.calendar.index == index) {
				ck = this.calendar.keywords.remove(this.calendar.keywords.size() - 1);
				ck.setRemove(value);
			} else {
				ck = new CalendarKeywordAttributes();
				ck.setRemove(value);
			}
			calendar.keywords.add(ck);
		}
		
		this.calendar.index = index;
	}
	
	/*
	 * START Prompt
	 */
	private String setPromptValue(String value) {
		if(this.prompt == null) {
			this.prompt = new PromptList();
		}
		
		if(value != null && !value.trim().equals("")) {
			return value.trim();
		} else {
			return null;
		}
	}
	public void setPrompt(String value, int index) {
		value = setPromptValue(value);
		if(value != null) {
			String name;
			if(this.prompt.index == index) {
				name = this.prompt.list.remove(this.prompt.list.size() - 1);
				name = value;
			} else {
				name = value;
			}
			this.prompt.list.add(name);
		}
		
		this.prompt.index = index;
	}
	
	public void setPreProcess(String value) {
		if(value != null && !value.equals("")) {
			if(this.preProcess == null ) {
				this.preProcess = value;
			} else {
				this.preProcess += "\n" + value;
			}
			
		}
	}
	
	public void setProcess(String value) {
		if(value != null && !value.equals("")) {
			if(this.process == null ) {
				this.process = value;
			} else {
				this.process += "\n" + value;
			}
			
		}
	}
	
	public void setPostProcess(String value) {
		if(value != null && !value.equals("")) {
			if(this.postProcess == null ) {
				this.postProcess = value;
			} else {
				this.postProcess += "\n" + value;
			}
			
		}
	}
	
	public void setTemplate(String value) {
		if(value != null && !value.equals("")) {
			this.template = value.trim();
		}
	}
	
	public void setTitle(String value) {
		if(value != null && !value.equals("")) {
			this.title = value.trim();
		}
	}
	
	/*
	 * START FileTransferAttributes
	 */
	private String setFileTransferValue(String value) {
		if(this.fileTransfer == null) {
			this.fileTransfer = new FileTransferAttributes();
		}
		
		if(value != null && !value.trim().equals("")) {
			return value.trim();
		} else {
			return null;
		}
	}
	public void setFileTransferDestinationHost(String value, int index) {
		value = setFileTransferValue(value);
		if(value != null) {
			this.fileTransfer.destinationHost = value;
		}
	}
	public void setFileTransferDestinationLogin(String value, int index) {
		value = setFileTransferValue(value);
		if(value != null) {
			this.fileTransfer.destinationLogin = value;
		}
	}
	public void setFileTransferEraseSource(String value, int index) {
		value = setFileTransferValue(value);
		if(value != null) {
			this.fileTransfer.eraseSource = ObjectHelper.stringToBoolean(value);
		}
	}
	public void setFileTransferFileExistsAction(String value, int index) {
		value = setFileTransferValue(value);
		if(value != null) {
			this.fileTransfer.fileExistsAction = value;
		}
	}
	public void setFileTransferFormat(String value, int index) {
		value = setFileTransferValue(value);
		if(value != null && value.equals("BINARY")) {
			this.fileTransfer.format = false;
		} else if(value != null) {
			this.fileTransfer.format = true;
		}
	}
	public void setFileTransferSource(String value, int index) {
		value = setFileTransferValue(value);
		if(value != null) {
			this.fileTransfer.source = value;
		}
	}
	public void setFileTransferTarget(String value, int index) {
		value = setFileTransferValue(value);
		if(value != null) {
			this.fileTransfer.target = value;
		}
	}
	public void setFileTransferUseWildcards(String value, int index) {
		value = setFileTransferValue(value);
		if(value != null) {
			this.fileTransfer.useWildcards = ObjectHelper.stringToBoolean(value);
		}
	}
	
	/*
	 * START JobPlanAttributes
	 */
	private String setJobPlanTaskValue(String value) {
		if(this.jobPlan == null) {
			this.jobPlan = new JobPlanAttributes();
		}
		
		if(value != null && !value.trim().equals("")) {
			return value.trim();
		} else {
			return null;
		}
	}
	public void setJobPlanTaskId(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setId(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setId(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskName(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setName(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setName(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskAlias(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setAlias(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setAlias(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskPredecessor(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setPredecessor(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setPredecessor(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskSuccessor(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setSuccessor(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setSuccessor(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskDependencyStatus(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setDependencyStatus(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setDependencyStatus(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskOnFailure(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setOnFailure(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setOnFailure(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskDaysAfterJobPlanActivation(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setDaysAfterJobPlanActivation(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setDaysAfterJobPlanActivation(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskTime(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setTime(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setTime(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskCalendar(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setCalendar(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setCalendar(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskCalendarKeyword(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setCalendarKeyword(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setCalendarKeyword(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskExternal(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setExternal(ObjectHelper.stringToBoolean(value));
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setExternal(ObjectHelper.stringToBoolean(value));
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	public void setJobPlanTaskExternalParent(String value, int index) {
		value = setJobPlanTaskValue(value);
		if(value != null) {
			JobPlanTaskAttributes jpt;
			if(this.jobPlan.index == index) {
				jpt = this.jobPlan.tasks.remove(this.jobPlan.tasks.size() - 1);
				jpt.setExternalParent(value);
			} else {
				jpt = new JobPlanTaskAttributes();
				jpt.setExternalParent(value);
			}
			jobPlan.tasks.add(jpt);
		}
		
		this.jobPlan.index = index;
	}
	
	/*
	 * START SyncProperties
	 */
	private String setSyncPropertyValue(String value) {
		if(this.syncProperty == null) {
			this.syncProperty = new SyncList();
		}
		
		if(value != null && !value.trim().equals("")) {
			return value.trim();
		} else {
			return null;
		}
	}
	public void setSyncPropertyName(String value, int index) {
		value = setSyncPropertyValue(value);
		if(value != null) {
			SyncProperties sp;
			if(this.syncProperty.index == index) {
				sp = this.syncProperty.syncList.remove(this.syncProperty.syncList.size() - 1);
				sp.setName(value);
			} else {
				sp = new SyncProperties();
				sp.setName(value);
			}
			syncProperty.syncList.add(sp);
		}
		
		this.syncProperty.index = index;
	}
	public void setSyncPropertyStartAction(String value, int index) {
		value = setSyncPropertyValue(value);
		if(value != null) {
			SyncProperties sp;
			if(this.syncProperty.index == index) {
				sp = this.syncProperty.syncList.remove(this.syncProperty.syncList.size() - 1);
				sp.setStartAction(value);
			} else {
				sp = new SyncProperties();
				sp.setStartAction(value);
			}
			syncProperty.syncList.add(sp);
		}
		
		this.syncProperty.index = index;
	}
	public void setSyncPropertyEndAction(String value, int index) {
		value = setSyncPropertyValue(value);
		if(value != null) {
			SyncProperties sp;
			if(this.syncProperty.index == index) {
				sp = this.syncProperty.syncList.remove(this.syncProperty.syncList.size() - 1);
				sp.setEndAction(value);
			} else {
				sp = new SyncProperties();
				sp.setEndAction(value);
			}
			syncProperty.syncList.add(sp);
		}
		
		this.syncProperty.index = index;
	}
	public void setSyncPropertyAbendAction(String value, int index) {
		value = setSyncPropertyValue(value);
		if(value != null) {
			SyncProperties sp;
			if(this.syncProperty.index == index) {
				sp = this.syncProperty.syncList.remove(this.syncProperty.syncList.size() - 1);
				sp.setAbendAction(value);
			} else {
				sp = new SyncProperties();
				sp.setAbendAction(value);
			}
			syncProperty.syncList.add(sp);
		}
		
		this.syncProperty.index = index;
	}
	public void setSyncPropertyElseAction(String value, int index) {
		value = setSyncPropertyValue(value);
		if(value != null) {
			SyncProperties sp;
			if(this.syncProperty.index == index) {
				sp = this.syncProperty.syncList.remove(this.syncProperty.syncList.size() - 1);
				sp.setElseAction(value);
			} else {
				sp = new SyncProperties();
				sp.setElseAction(value);
			}
			syncProperty.syncList.add(sp);
		}
		
		this.syncProperty.index = index;
	}

	/*
	 * START SapAttributes
	 */
	public void setSapDelete(String value) {
		if(this.sap == null) {
			this.sap = new SapAttributes();
		}
		
		this.sap.setDelete(value);
	}
	
	/*
	 * TODO VariableAttributes
	 */
	public void setVariableName(String key, int index) {
		if(this.variable == null) {
			this.variable = new AttributeGroup();
		}
		
		if(key != null && !key.equals("")) {
			if(this.variable.index == index) {
				String value = this.variable.map.remove("");
				
				this.variable.map.put(key, value != null ? value : "");
			} else {
				this.variable.map.put(key, "");
			}
		}
		
		this.variable.key = key;
		this.variable.index = index;
	}
	
	public void setVariableValue(String value, int index) {
		if(this.variable == null) {
			this.variable = new AttributeGroup();
		}
		
		if(value != null && !value.equals("")) {
			if(this.variable.index == index) {
				String key = this.variable.key;
				
				this.variable.map.replace(key, value);
			} else {
				this.variable.map.put("", value);
			}
		}
		
		this.variable.index = index;
	}
	
	public void reset() {
		Field[] fields = ObjectAttributes.class.getFields();
		
		for(Field field : fields) {
			try {
				field.set(this, null);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
