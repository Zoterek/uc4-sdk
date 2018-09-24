package pl.automic.helpers;

import java.util.HashMap;
import java.util.Map;

public class AttributeGroup extends Attribute {
	public Map<String, String> map;
	public String key;
	public int index;
	
	public AttributeGroup() {
		this.map = new HashMap<>();
	}
}
