package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Enumeration;

/** Select one menu renderer for enumerations 
 * @author jsingh
 *
 */
public class SelectOneMenuRenderer extends AbstractInputComponentRenderer{

	public String getType(Attribute attribute) {
		return "h:selectOneMenu";
	}	
	
	public String getContent(Attribute attribute) {
		return generateEnumLiterals((Enumeration)attribute.Type());
	}

	
	
	public static String generateEnumLiterals(Enumeration enm) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("<f:selectItem itemLabel=\"Select\" />"); 
		for (int i = 0; i < enm.Literal().size(); i++) {
			buffer.append("<f:selectItem itemLabel=\"" + enm.Literal(i).NameS()  + "\" itemValue=\"" + 
					enm.Literal(i).NameS() + "\" />\n" );
		}

		return buffer.toString();
	}

	
	public static void main(String[] args) {
		Validator validator = Validator.CREDIT_CARD;
		System.out.println(validator.toString());
		
	}
}



