package org.oscar.html.tags;

import java.util.Map;
import java.util.Set;


public class SelectTag extends AbstractInputHtmlTag {

	private Map<String, String> map;
	
	private boolean showSelect = true;
	

	public boolean isShowSelect() {
		return showSelect;
	}

	public void setShowSelect(boolean showSelect) {
		this.showSelect = showSelect;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	
	@Override
	protected String render() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("<select name='" +  getName() + "' " + " id='"+ getName() + "' >");
		if(showSelect)
			buffer.append("<option value=''> Select </option>");
		Set keys = map.keySet();
		for (Object key : keys) {
			//System.out.println("comp " + key + getValue());
			String selected = (key.equals(getValue()))?" SELECTED ":""; 
			buffer.append("<option value='" + key + "'" + selected + ">" + map.get(key) + "</option>" );
		}
		buffer.append("</select>");
		return buffer.toString();
	}
}
