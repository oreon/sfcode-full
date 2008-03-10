package org.witchcraft.htmlinput.jsf;

import oaw4.demo.classic.uml.meta.Column;

import org.apache.commons.lang.StringUtils;
import org.openarchitectureware.meta.uml.classifier.Attribute;

/**
 * Convenience abstract class for input control renderers - typically concrete input renderes
 * will be derived from this class
 * @author jesing
 *
 */
public abstract class AbstractInputComponentRenderer implements InputComponentRenderer {
	
	private RenderContext renderContext;
	
	public String getContent(Attribute attribute) {
		return StringUtils.EMPTY;
	}

	public String getValidatorContent(Attribute attribute) {
		return StringUtils.EMPTY;
	}

	/** The additional attributes for the renderer
	 * @param attribute e.g renderAsPopup=\"true\" renderPopupButtonAsImage=\"true\"";
	 * @return nothing for default
	 */
	public String getAttributes(Attribute attribute) {
		return StringUtils.EMPTY; // 
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
