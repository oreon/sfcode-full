package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

/** 
 * @author jsingh
 *
 */
public interface InputComponentRenderer {
	public String getType(Attribute attribute);
	public String getContent(Attribute attribute);
}