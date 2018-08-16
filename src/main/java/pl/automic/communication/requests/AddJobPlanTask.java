package pl.automic.communication.requests;

import com.uc4.api.UC4Alias;
import com.uc4.api.UC4ObjectName;

public class AddJobPlanTask extends com.uc4.communication.requests.AddJobPlanTask {

	public AddJobPlanTask(String arg0) {
		this(new UC4ObjectName(arg0));
	}

	public AddJobPlanTask(UC4ObjectName arg0, UC4Alias arg1) {
		super(arg0, arg1);
	}

	public AddJobPlanTask(UC4ObjectName arg0) {
		super(arg0);
	}

}
