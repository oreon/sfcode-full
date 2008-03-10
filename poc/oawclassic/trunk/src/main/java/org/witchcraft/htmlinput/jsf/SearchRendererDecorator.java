package org.witchcraft.htmlinput.jsf;

import org.apache.commons.lang.StringUtils;
import org.openarchitectureware.meta.uml.classifier.Attribute;

/** 
 * We use decorator design pattern to retrieve an input component renderer to be used specifically
 * while creating search views - typically we don't need validations for input control while searching
 * @author jesing
 *
 */
public class SearchRendererDecorator implements InputComponentRenderer{
	
	private InputComponentRenderer inputComponentRenderer;

	public SearchRendererDecorator(InputComponentRenderer inputComponentRenderer){
		this.inputComponentRenderer = inputComponentRenderer;
	}

	public String getType(Attribute attribute) {
		return inputComponentRenderer.getType(attribute);
	}
	
	/** 
	 * For Search views we don't need to show the required attributes as mandatory 
	 * @see org.witchcraft.htmlinput.jsf.AbstractInputComponentRenderer#getRequired(org.openarchitectureware.meta.uml.classifier.Attribute)
	 */
	public Boolean isRequired(Attribute attribute) {
		return false;
	}
	

	public String getAttributes(Attribute attribute) {
		return StringUtils.EMPTY; //inputComponentRenderer.getAttributes(attribute);
	}

	public String getContent(Attribute attribute) {
		return inputComponentRenderer.getContent(attribute);
	}

	public String getValidatorContent(Attribute attribute) {
		return StringUtils.EMPTY;
	}
	
	
}
