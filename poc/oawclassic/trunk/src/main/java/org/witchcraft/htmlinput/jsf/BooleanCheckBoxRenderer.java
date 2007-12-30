package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

public class BooleanCheckBoxRenderer extends AbstractInputComponentRenderer{

	public String getType(Attribute attribute) {
		return "h:selectBooleanCheckbox ";
	}
	
	public String getAttributes(Attribute attribute) {
		return super.getAttributes(attribute)+ " title=\"" + attribute.getNameProperty() + "\"";
	}
}
