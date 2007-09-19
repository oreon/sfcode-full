package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

/**  This input component renderer interface 
 * @author jsingh
 *
 */
public interface InputComponentRenderer {
	public String getType(Attribute attribute);
	public String getContent(Attribute attribute);
	public String getAttributes(Attribute attribute);
}