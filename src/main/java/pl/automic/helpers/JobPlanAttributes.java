package pl.automic.helpers;

import java.util.ArrayList;
import java.util.List;

public class JobPlanAttributes {
	public List<JobPlanTaskAttributes> tasks;
	public String name;
	public int index;
	
	public JobPlanAttributes() {
		this.tasks = new ArrayList<>();
	}
}
