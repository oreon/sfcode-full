package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

public class CalendarRenderer extends AbstractInputComponentRenderer{

	public String getType(Attribute attribute) {
		// TODO Auto-generated method stub
		return "rich:calendar ";
	}
	
	public String getAttributes(Attribute attribute) {
		// TODO Auto-generated method stub
		return super.getAttributes(attribute) +  
			" popup=\"true\" ";
	}

}
