package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;

public class InputTextRenderer implements InputComponentRenderer {
	
	public String getType(Attribute attribute){
		return "h:inputText" ;
	}
	
	public String getContent(Attribute attribute){
		return "";
	}
}
