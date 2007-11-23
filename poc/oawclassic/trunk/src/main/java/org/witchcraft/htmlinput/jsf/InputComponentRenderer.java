package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

/**  This input component renderer interface 
 * @author jsingh
 *
 */
/**
 * @author jsingh
 *
 */
public interface InputComponentRenderer {
	public String getType(Attribute attribute);
	public String getContent(Attribute attribute);
	public String getAttributes(Attribute attribute);
	
	/** Indicates weather this is a required attribute in the form
	 * @param attribute
	 * @return
	 */
	public Boolean isRequired(Attribute attribute);
}