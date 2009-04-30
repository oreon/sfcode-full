package org.oscar.html.tags;

import org.apache.commons.lang.StringUtils;

public class InputTag extends AbstractInputHtmlTag{
	
	private String type = "text";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setValue(Integer intVal){
		if(intVal != null)
			setValue(String.valueOf(intVal));
	}

	@Override
	protected String render() {
		return "<input " + getRenderedName() + getRenderedValue() +  getRenderedType() + "/>";
	}
	
	public String getRenderedName(){
		return " name='" + getName() +  "' ";
	}
	
	public String getRenderedType(){
		return " type='" + getType() +  "' ";
	}
	
	public String getRenderedValue() {
		if(super.getValue() == null) 
			return StringUtils.EMPTY; 
		return " value='" + super.getValue() +  "' ";
	}
	
	

}
