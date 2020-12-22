package pl.automic.helpers;

import java.util.stream.Stream;

import com.uc4.api.Time;
import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.CalendarCondition;
import com.uc4.api.objects.TaskState;

public class JobPlanTaskAttributes {
	public String id;
	public String name;
	public String alias;
	public int[] predecessor;
	public String[] successor;
	public TaskState dependencyStatus;
	public String onFailure;
	public Time time;
	public int daysAfterJobPlanActivation;
	public boolean external;
	public String externalParent;
	public String calendar;
	public String calendarKeyword;
	public boolean calendarCondition;
	private boolean earliest;
	

	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public void setPredecessor(String predecessor) {
		this.predecessor = Stream.of(predecessor.split(";"))
				.map(String::trim)
				.map(Integer::parseInt)
				.mapToInt(e -> e)
				.toArray();
	}
	
	public void setSuccessor(String successor) {
		this.successor = Stream.of(successor.split(";"))
				.map(String::trim)
				.toArray(String[]::new);
	}
	
	public void setDependencyStatus(String status) {
		this.dependencyStatus = new TaskState(status.toUpperCase());
	}
	
	public void setOnFailure(String str) {
		this.onFailure = str.toUpperCase();
	}
	
	public void setTime(String time) {
		if(!time.equals("")) {
			String[] times = time.split(":");
			this.time = new Time(Short.parseShort(times[0]), Short.parseShort(times[1]), Short.parseShort("0"));
		}
	}
	
	public void setDaysAfterJobPlanActivation(String value) {
		if(!value.equals("")) {
			this.earliest = true;
			this.daysAfterJobPlanActivation = Integer.parseInt(value);
		}
	}
	
	public boolean hasEarliest() {
		return this.earliest;
	}
	
	public void setCalendar(String calendar) {
		if(!calendar.equals("")) {
			this.calendarCondition = true;
			this.calendar = calendar;
		}
	}
	
	public void setCalendarKeyword(String calendarKeyword) {
		this.calendarKeyword = calendarKeyword;
	}
	
	public boolean hasCalendarCondiiton() {
		return this.calendarCondition;
	}
	
	public CalendarCondition getCalendarCondition() {
		return new CalendarCondition(new UC4ObjectName(this.calendar), new UC4ObjectName(this.calendarKeyword));
	}
	
	public void setExternal(boolean external) {
		this.external = external;
	}
	
	public boolean isExternal() {
		return this.external;
	}
	
	public void setExternalParent(String externalParent) {
		this.externalParent = externalParent;
	}
	
	public UC4ObjectName getExternalParent() {
		return new UC4ObjectName(this.externalParent);
	}
}
