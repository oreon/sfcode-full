package org.witchcraft.htmlinput.jsf;

import oaw4.demo.classic.uml.meta.Column;

import org.apache.commons.lang.StringUtils;
import org.openarchitectureware.meta.uml.classifier.Attribute;

public class InputTextRenderer extends AbstractInputComponentRenderer {

	public final static int LENGTH_FOR_TEXTAREA = 50;

	public String getType(Attribute attribute) {
		if (attribute instanceof Column ){
			Column column = (Column) attribute; 
			
			if(column.getMaxLength() > LENGTH_FOR_TEXTAREA) 
				return "h:inputTextarea";
			
			if(("secret").equalsIgnoreCase(column.getInputType()) )
				return "h:inputSecret";
			
			if(("file").equalsIgnoreCase(column.getInputType()) )
				return "t:inputFileUpload";
		}
		return "h:inputText";
	}

	public String getContent(Attribute attribute) {

		String retMessage = StringUtils.EMPTY;

		if (attribute instanceof Column) {
			Column column = (Column) attribute;

			String columnType = column.Type().NameS();
			String validatorType = getValidatorType(columnType);

			int min = column.getMinLength();
			int max = column.getMaxLength();

			if (min == 0 && max == 0)
				return "";

			if (validatorType != null)
				retMessage = "<" + validatorType
						+ ((min > 0) ? " minimum=\"" + min + "\"" : "")
						+ ((max > 0) ? " maximum=\"" + max + "\"" : "") + "/>";

			retMessage += getColumnValidator(column);
		}

		return retMessage;
	}

	private String getColumnValidator(Column column) {
		if (("email").equalsIgnoreCase(column.getValidator()))
			return "<t:validateEmail />";

		return StringUtils.EMPTY;
	}

	private String getValidatorType(String columnType) {
		String validatorType = null;

		if (columnType.equalsIgnoreCase("int")
				|| columnType.equalsIgnoreCase("long")) {
			validatorType = "f:validateLongRange";
		} else if (columnType.equalsIgnoreCase("double")
				|| columnType.equalsIgnoreCase("BigDecimal")) {
			validatorType = "f:validateDoubleRange";
		} else if (columnType.equalsIgnoreCase("String")) {
			validatorType = "f:validateLength";
		}
		return validatorType;
	}

	@Override
	public String getAttributes(Attribute attribute) {

		String attrib = "";

		if (attribute instanceof Column
				&& ((Column) attribute).getMaxLength() > 0) {
			System.out.println();
			attrib = " maxLength=\"" + ((Column) attribute).getMaxLength()
					+ "\" ";
		}
		return attrib + super.getAttributes(attribute);
	}

}
