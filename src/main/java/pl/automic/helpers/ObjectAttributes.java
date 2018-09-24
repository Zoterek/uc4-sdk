package pl.automic.helpers;

import java.lang.reflect.Field;

public class ObjectAttributes {
	public String archive1;
	public String archive2;
	public String description;
	public String host;
	public String id;
	public String login;
	public String name;
	public String preProcess;
	public String process;
	public String postProcess;
	public String template;
	public String title;
	public FileTransferAttributes fileTransfer;
	// Might need to change class name to VariableAttribute
	public AttributeGroup variable;
	// public String variableName;
	// public String variableValue;
	
	
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
			this.fileTransfer.eraseSource = stringToBoolean(value);
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
			this.fileTransfer.useWildcards = stringToBoolean(value);
		}
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
	
	private boolean stringToBoolean(String str) {
		if(str == null) {
			return false;
		}
		
		str = str.toUpperCase();
		switch (str) {
		case "Y":
		case "YES":
		case "TRUE":
			return true;
		default:
			return false;
		}
	}
}
