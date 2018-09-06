package pl.automic.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TextSearchConfig {
	@JsonProperty(value="archiveKeys")
	private boolean archiveKeys;
	@JsonProperty(value="documentation")
	private boolean documentation;
	@JsonProperty(value="objectTitle")
	private boolean objectTitle;
	@JsonProperty(value="process")
	private boolean process;
	
	@JsonProperty(value="text")
	private String text;
	
	
	public boolean getArchiveKey() {
		return this.archiveKeys;
	}
	
	public boolean getDocumentation() {
		return this.documentation;
	}
	
	public boolean getObjectTitle() {
		return this.objectTitle;
	}
	
	public boolean getProcess() {
		return this.process;
	}
	
	public String getText() {
		return this.text;
	}
	
	/*
	 * Simple check if text has been defined.
	 * Can be later build upon
	 */
	public boolean hasTextSearch() {
		if(this.getText() == null) {
			return false;
		}
		
		return true;
	}
}
