package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Enumeration;

public class SelectOneMenuRenderer extends AbstractInputComponentRenderer{

	public String getContent(Attribute attribute) {
		// TODO Auto-generated method stub
		
		return generateEnumLiterals((Enumeration)attribute.Type());
	}

	public String getType(Attribute attribute) {
		
		// TODO Auto-generated method stub
		return "h:selectOneMenu";
	}
	
	public static String generateEnumLiterals(Enumeration enm) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < enm.Literal().size(); i++) {
			buffer.append("<f:selectItem itemLabel=\"" + enm.Literal(i).NameS()  + "\" itemValue=\"" + 
					enm.Literal(i).NameS() + "\" />" );
		}

		return buffer.toString();
	}

}
