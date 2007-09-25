package org.witchcraft.htmlinput.jsf;

import oaw4.demo.classic.uml.meta.Column;

import org.openarchitectureware.meta.uml.classifier.Attribute;

public abstract class AbstractInputComponentRenderer implements InputComponentRenderer {
	
	private RenderContext renderContext;
	
	public String getContent(Attribute attribute) {
		return "";
	}


	/** The additional attributes for the renderer
	 * @param attribute e.g renderAsPopup=\"true\" renderPopupButtonAsImage=\"true\"";
	 * @return nothing for default
	 */
	public String getAttributes(Attribute attribute) {
		return ""; // 
	}
	
	public Boolean isRequired(Attribute attribute){
		return isAttributeRequired(attribute);
	}
	


	/**
	 * This function returns if the attribute must be supplied by user in the
	 * user interface
	 * 
	 * @param attribute
	 * @return
	 */
	private boolean isAttributeRequired(Attribute attribute) {
		boolean defRequired = false;
		if (attribute instanceof Column) {
			Column column = (Column) attribute;
			return column.isNullable() ? false : true;
		}
		return defRequired;
	}
}
