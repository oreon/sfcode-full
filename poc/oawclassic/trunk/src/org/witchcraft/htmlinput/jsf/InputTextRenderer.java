package org.witchcraft.htmlinput.jsf;

import oaw4.demo.classic.uml.meta.Column;

import org.openarchitectureware.meta.uml.classifier.Attribute;

public class InputTextRenderer extends AbstractInputComponentRenderer {
	
	public final static int LENGTH_FOR_TEXTAREA = 50;
	
	public String getType(Attribute attribute){
		if(attribute instanceof Column && 
				((Column)attribute).getMaxLength() > LENGTH_FOR_TEXTAREA  ){
			System.out.println(((Column)attribute).getMaxLength());
			return "h:inputArea";
		}
		return "h:inputText" ;
	}
	
	public String getContent(Attribute attribute){
		if(attribute instanceof Column ){
			Column column = (Column) attribute;
		}
		return " ";
	}
}
