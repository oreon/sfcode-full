package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

public abstract class AbstractInputComponentRenderer implements InputComponentRenderer {
	
	public String getContent(Attribute attribute) {
		// TODO Auto-generated method stub
		return "";
	}

	/** The additional attributes for the rendere 
	 * @param attribute
	 * @return nothing for default
	 */
	public String getAttributes(Attribute attribute) {
		return "" ; // e.g renderAsPopup=\"true\" renderPopupButtonAsImage=\"true\"";
	}
	
	
}
