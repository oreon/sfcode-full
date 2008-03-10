package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

/**  This input component renderer interface 
 * @author jsingh
 *
 */
public interface InputComponentRenderer {
	
	/** Type of the attribute to be rendered e.g h:inputText , h:selectOneMenu
	 * @param attribute
	 * @return
	 */
	public String getType(Attribute attribute);
	
	/** The content - for a selectonemenu it will be all the dropdown elements
	 * @param attribute
	 * @return
	 */
	public String getContent(Attribute attribute);
	
	/** Validators that are created in the content part of the control e.g 
	 * <h:inputText ...> <f:validateLongRange> </h:inputText> - in this case 
	 * <f:validateLongRange> will be returned by this method 
	 * @param attribute
	 * @return
	 */
	public String getValidatorContent(Attribute attribute);
	
	/** Attributes such as maxlength, style etc other than 'required' attribute 
	 * @param attribute
	 * @return
	 */
	public String getAttributes(Attribute attribute);
	
	/** Indicates weather this is a required attribute in the form
	 * @param attribute
	 * @return
	 */
	public Boolean isRequired(Attribute attribute);
}