package pl.automic.helpers.lists;

import java.util.ArrayList;
import java.util.List;

import pl.automic.helpers.lists.properties.SyncProperties;

public class SyncList {
	public List<SyncProperties> syncList;
	public int index;
	
	public SyncList() {
		this.syncList =  new ArrayList<>();
	}
}
