package org.witchcraft.htmlinput.jsf;

import oaw4.demo.classic.uml.meta.Column;

import org.openarchitectureware.meta.uml.classifier.Attribute;

public class InputTextRenderer extends AbstractInputComponentRenderer {
	
	public final static int LENGTH_FOR_TEXTAREA = 50;
	
	public String getType(Attribute attribute){
		if(attribute instanceof Column && 
				((Column)attribute).getMaxLength() > LENGTH_FOR_TEXTAREA  ){
			System.out.println(((Column)attribute).getMaxLength());
			return "h:inputTextarea";
		}
		return "h:inputText" ;
	}
	
	public String getContent(Attribute attribute){
		if(attribute instanceof Column ){
			Column column = (Column) attribute;
			
			String columnType = column.Type().NameS();
			String validatorType = getValidatorType(columnType);
			
			int min = column.getMinLength();
			int max = column.getMaxLength();
			
			if(min == 0 && max == 0) 
				return "";
			
			if(validatorType != null)
			return "<" + validatorType + ((min > 0 )?" minimum=\""  + min + "\"":"")
			+ ((max > 0 )?" maximum=\""  + max + "\"" :"") 
					+ "/>";
			
		}
		return " ";
	}

	private String getValidatorType(String columnType) {
		String validatorType = null;
		
		if(columnType.equalsIgnoreCase("int") || columnType.equalsIgnoreCase("long")){
			validatorType = "f:validateLongRange";
		}else if (columnType.equalsIgnoreCase("double") || columnType.equalsIgnoreCase("BigDecimal")){
			validatorType = "f:validateDoubleRange";
		}else if(columnType.equalsIgnoreCase("String")){
			validatorType = "f:validateLength";
		}
		return validatorType;
	}
	
	@Override
	public String getAttributes(Attribute attribute) {
		
		String attrib = "";
		
		if(attribute instanceof Column && 
				((Column)attribute).getMaxLength() > 0  ){
			System.out.println();
			attrib = " maxLength=\"" + ((Column)attribute).getMaxLength() + "\" ";
		}
		return attrib + super.getAttributes(attribute);
	}
	
	
	
	
}
