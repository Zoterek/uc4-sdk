package pl.automic.communication.requests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.uc4.api.DateTime;
import pl.automic.config.SearchFilterConfig;
import pl.automic.config.TextSearchConfig;

public class SearchObject extends com.uc4.communication.requests.SearchObject {
	private SearchFilterConfig filter;
	
	public SearchObject() {
		super();
	}

	public SearchObject(SearchFilterConfig filter) throws IOException {
		if(filter == null) {
			return;
		}
		this.filter = filter;
		
		setDate();
		setName();
		setSearchForUse();
		setTextSearch();
		setType();
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
		if(filter.textSearch == null) {
			return;
		}
		
		TextSearchConfig config = filter.textSearch;
		if(config.hasTextSearch()) {
			this.setTextSearch(config.getText(), config.getProcess(), config.getDocumentation(), config.getObjectTitle(), config.getArchiveKey());
		}
	}
	
	private void setType() {
		if(filter.type == null || filter.type.length == 0) {
			this.selectAllObjectTypes();
		} else {
			for(String type : filter.type) {
				setType(type);
			}
		}
	}
	
	private void setType(String type) {
		Method method = null;
		try {
			method = this.getClass().getMethod("setType" + type, boolean.class);
		} catch (NoSuchMethodException e) {
			System.err.println("The object type does not exist: " + type);
			e.printStackTrace();
			System.exit(0);
		} catch (SecurityException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			method.invoke(this, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}
