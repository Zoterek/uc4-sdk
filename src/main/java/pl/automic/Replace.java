package pl.automic;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uc4.api.objects.FileTransfer;
import com.uc4.api.objects.Include;
import com.uc4.api.objects.Job;
import com.uc4.api.objects.JobPlan;
import com.uc4.api.objects.Script;
import com.uc4.api.objects.UC4Object;
import com.uc4.api.objects.User;
import com.uc4.api.objects.UserGroup;
import com.uc4.api.objects.UserRight;
import com.uc4.api.objects.WorkflowIF;
import com.uc4.api.objects.WorkflowLoop;

import pl.automic.config.SearchFilterConfig;

/*
 * Custom class to replace given data using regex 
 */

public class Replace {
	private SearchFilterConfig filter;
	private Pattern pattern;
	private String replacement;
	// TODO Temp patch for replaceUserRight
	private State state;
	
	public Replace(File filter) throws IOException {
		this.filter = new ObjectMapper().readValue(filter, SearchFilterConfig.class);
		this.pattern = Pattern.compile(this.filter.replace.pattern);
		this.replacement = this.filter.replace.replacement;
	}

	
	public State process(UC4Object obj) {
		String name = obj.getName();
		
		if(obj instanceof FileTransfer) {
			FileTransfer job = (FileTransfer) obj;
			
			// Replace process
			String process = job.getProcess();
			job.setProcess(replace(process));
			
			// Replace post process
			String postProcess = job.getPostProcess();
			job.setPostProcess(replace(postProcess));
			
			if(process.equals(job.getProcess()) &&
					postProcess.equals(job.getPostProcess())) {
				return State.UNCHANGED;
			} else {
				return State.CHANGED;
			}
		} else if(obj instanceof Include) {
			Include job = (Include) obj;
			
			// Replace process
			String process = job.getProcess();
			job.setProcess(replace(process));
			
			if(process.equals(job.getProcess())) {
				return State.UNCHANGED;
			} else {
				return State.CHANGED;
			}
			
		} else if(obj instanceof Job) {
			Job job = (Job) obj;
			
			// Replace pre process
			String preProcess = job.getPreProcess();
			job.setPreProcess(replace(preProcess));
			
			
			// Replace process
			String process = job.getProcess();
			job.setProcess(replace(process));
			
			// Replace post process
			String postProcess = job.getPostProcess();
			job.setPostProcess(replace(postProcess));
			
			if(preProcess.equals(job.getPreProcess()) &&
					process.equals(job.getProcess()) &&
					postProcess.equals(job.getPostProcess())) {
				return State.UNCHANGED;
			} else {
				return State.CHANGED;
			}
		} else if(obj instanceof JobPlan) {
			try {
				JobPlan jp = (JobPlan) obj;
				
				
				// Replace process
				String process = jp.getProcess();
				jp.setProcess(replace(process));
				
				if(process.equals(jp.getProcess())) {
					return State.UNCHANGED;
				} else {
					return State.CHANGED;
				}
			} catch (ClassCastException e) { }
			
			try {
				WorkflowIF jp = (WorkflowIF) obj;
				
				
				// Replace process
				String process = jp.getProcess();
				jp.setProcess(replace(process));
				
				if(process.equals(jp.getProcess())) {
					return State.UNCHANGED;
				} else {
					return State.CHANGED;
				}
			} catch (ClassCastException e) { }
			
			try {
				WorkflowLoop jp = (WorkflowLoop) obj;
				
				// Replace process
				String process = jp.getProcess();
				jp.setProcess(replace(process));
				
				if(process.equals(jp.getProcess())) {
					return State.UNCHANGED;
				} else {
					return State.CHANGED;
				}
			} catch (ClassCastException e) { }
			
			return State.ERROR;
		} else if(obj instanceof Script) {
			Script scri = (Script) obj;
			
			// Replace process
			String process = scri.getProcess();
			scri.setProcess(replace(process));
			
			if(process.equals(scri.getProcess())) {
				return State.UNCHANGED;
			} else {
				return State.CHANGED;
			}
		} else {
			out("Skipped", name, "of type", obj.getType());
			return State.SKIPPED;
		}
	}
	
	public State userAuthorization(UC4Object obj) {
		
		if(obj instanceof User) {
			User user = (User) obj;
			
			// replace authorizations
			user.authorizations().iterator().forEachRemaining(e -> this.state = replaceUserRight(e));
		} else if(obj instanceof UserGroup) {
			UserGroup userg = (UserGroup) obj;
			
			// replace authorizations
			userg.authorizations().iterator().forEachRemaining(e -> this.state = replaceUserRight(e));
		}
		
		return this.state;
		
	}
			
	private String replace(String str) {
		// TODO pattern.
		return pattern.matcher(str).replaceAll(this.replacement);
	}
	
	private State replaceUserRight(UserRight e) {
		State state = State.UNCHANGED;
		String value;
		// TODO Create iterator for UserRight object (extend class)
		
		
		// File Name Destination
		value = e.getFileNameDestination();
		if(!value.equals(this.replacement)) {
			state = State.CHANGED;
		}
		e.setFileNameDestination(replace(value));
		
		// File Name Source
		value = e.getFileNameSource();
		if(!value.equals(this.replacement)) {
			state = State.CHANGED;
		}
		e.setFileNameSource(replace(value));
		
		// Host
		value = e.getHost();
		if(!value.equals(this.replacement)) {
			state = State.CHANGED;
		}
		e.setHost(replace(value));

		// Host Destination
		value = e.getHostDestination();
		if(!value.equals(this.replacement)) {
			state = State.CHANGED;
		}
		e.setHostDestination(replace(value));
		
		// Login
		value = e.getLogin();
		if(!value.equals(this.replacement)) {
			state = State.CHANGED;
		}
		e.setLogin(replace(value));
		
		// Login Destination
		value = e.getLoginDestination();
		if(!value.equals(this.replacement)) {
			state = State.CHANGED;
		}
		e.setLoginDestination(replace(value));
		
		// Name
		value = e.getName();
		if(!value.equals(this.replacement)) {
			state = State.CHANGED;
		}
		e.setName(replace(value));
		
		return state;
	}
	
	/*
	 * TODO MOVE THIS TO A HELPER CLASS AND IMPLEMENT WITHIN THIS CLASS
	 */
	private void out(String... args) {
		for(String arg : args) {
			System.out.print(arg + " ");
		}
		System.out.println();
	}
}
