package org.witchcraft.htmlinput.jsf;

import oaw4.demo.classic.uml.meta.Column;

import org.apache.commons.lang.StringUtils;
import org.openarchitectureware.meta.uml.classifier.Attribute;

public class InputTextRenderer extends AbstractInputComponentRenderer {

	public final static int LENGTH_FOR_TEXTAREA = 120;

	public String getType(Attribute attribute) {
		if (attribute instanceof Column) {
			Column column = (Column) attribute;

			if (column.getMaxLength() > LENGTH_FOR_TEXTAREA)
				return "h:inputTextarea";

			if (("secret").equalsIgnoreCase(column.getInputType()))
				return "h:inputSecret";

			if (("file").equalsIgnoreCase(column.getInputType()))
				return "t:inputFileUpload";
		}
		return "h:inputText";
	}

	public String getContent(Attribute attribute) {

		String retMessage = StringUtils.EMPTY;

		if (attribute instanceof Column) {
			Column column = (Column) attribute;
			retMessage += getColumnValidator(column);

			String columnType = column.Type().NameS();
			String validatorType = getValidatorType(columnType);
			

			int min = column.getMinLength();
			int max = column.getMaxLength();

			if (min == 0 && max == 0)
				return retMessage;

			if (validatorType != null)
				retMessage = "<" + validatorType
						+ ((min > 0) ? " minimum=\"" + min + "\"" : "")
						+ ((max > 0) ? " maximum=\"" + max + "\"" : "") + "/>";

			
		}

		return retMessage;
	}

	/** Function appends columns validator
	 * @param column
	 * @return
	 */
	private String getColumnValidator(Column column) {

		String validator = column.getValidator();
		System.out.println(column.NameS() + " validator is "  + validator);

		if (Validator.EMAIL.toString().equalsIgnoreCase(validator))
			return "<t:validateEmail />";
		else if (Validator.CREDIT_CARD.toString().equalsIgnoreCase(validator))
			return "<t:validateCreditCard  />";

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
			Column column = (Column) attribute;
			attrib = " maxLength=\"" + column.getMaxLength() + "\" ";
			
			//for passwords etc, we need to set redisplay to true for 
			// ajax validation to work properly
			if (("secret").equalsIgnoreCase(column.getInputType()))
				attrib += " redisplay=\"true\" ";
		}
		return attrib + super.getAttributes(attribute);
	}

}
