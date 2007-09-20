package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

public class SearchRendererDecorator implements InputComponentRenderer{
	
	private InputComponentRenderer inputComponentRenderer;

	public SearchRendererDecorator(InputComponentRenderer inputComponentRenderer){
		this.inputComponentRenderer = inputComponentRenderer;
	}

	public String getType(Attribute attribute) {
		return inputComponentRenderer.getType(attribute);
	}
	
	/** 
	 * For Search views we dont need to show the required attributes as mandatory 
	 * @see org.witchcraft.htmlinput.jsf.AbstractInputComponentRenderer#getRequired(org.openarchitectureware.meta.uml.classifier.Attribute)
	 */
	public Boolean isRequired(Attribute attribute) {
		return false;
	}
	

	public String getAttributes(Attribute attribute) {
		return inputComponentRenderer.getAttributes(attribute);
	}

	public String getContent(Attribute attribute) {
		return inputComponentRenderer.getContent(attribute);
	}
	
	
}
