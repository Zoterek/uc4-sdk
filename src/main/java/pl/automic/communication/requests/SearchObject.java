package pl.automic.communication.requests;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uc4.api.DateTime;

import pl.automic.communication.requests.json.Filter;

public class SearchObject extends com.uc4.communication.requests.SearchObject {
	private Filter filter;
	
	public SearchObject() {
		super();
	}

	public SearchObject(File filter) throws IOException {
		this.filter = new ObjectMapper().readValue(filter, Filter.class);
		
		
		setDate();
		setName();
		setSearchForUse();
		setTextSearch();
	}
	
	private void setDate() {
		if(filter.from == null || filter.to == null || filter.from.isEmpty() || filter.to.isEmpty())
			return;
		
		
		DateTime from = new DateTime(filter.from);
		DateTime to = new DateTime(filter.to);
		
		switch (filter.dateSelection) {
		case "created":
			this.setDateSelectionCreated(from, to);
			break;
		case "modified":
			this.setDateSelectionModified(from, to);
			break;
		case "used":
			this.setDateSelectionUsed(from, to);
			break;
			
		default:
			break;
		}
	}
	
	private void setName() {
		if(filter.name == null || filter.name.isEmpty()) {
			return;
		}
		
		this.setName(filter.name);
	}
	
	private void setSearchForUse() {
		this.setSearchUseOfObjects(filter.searchForUse);
	}
	
	private void setTextSearch() {
		if(filter.textSearch == null || filter.textSearch.text == null || filter.textSearch.text.isEmpty()) {
			return;
		}
		
		this.setTextSearch(filter.textSearch.text, filter.textSearch.process, false, false, false);
	}
}
