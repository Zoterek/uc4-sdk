package pl.automic.helpers.lists.properties;

import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.SyncItem;

public class SyncProperties {
	public String name;
	public String startAction;
	public String endAction;
	public String abendAction;
	public String elseAction;
	
	public void setName(String name) {
		this.name = name.trim();
	}
	public void setStartAction(String startAction) {
		this.startAction = startAction.trim();
	}
	public void setEndAction(String endAction) {
		this.endAction = endAction.trim();
	}
	public void setAbendAction(String abendAction) {
		this.abendAction = abendAction.trim();
	}
	public void setElseAction(String elseAction) {
		this.elseAction = elseAction.trim();
	}
	
	public SyncItem getSyncItem() {
		String startAction = this.startAction != null ? this.startAction : "";
		String endAction = this.endAction != null ? this.endAction : "";
		String abendAction = this.abendAction != null ? this.abendAction : "";
		String elseAction = SyncItem.ELSE_SKIP; //this.elseAction != null ? this.elseAction : "";
		
		if(this.elseAction != null) {
			switch (this.elseAction.toLowerCase()) {
			case "wait":
				elseAction = SyncItem.ELSE_WAIT;
				break;
			case "abend":
				elseAction = SyncItem.ELSE_ABEND;
				break;
			default:
				elseAction = SyncItem.ELSE_SKIP;
				break;
			}
		}
		
		
		
		
		return new SyncItem(new UC4ObjectName(this.name), startAction, endAction, abendAction, elseAction);
	}
	
	public boolean hasSync() {
		return this.name != null && !this.name.equals("");
	}
}
