package org.caisi.persistence.base;

import org.caisi.persistence.viewhelper.ViewHelper;

public class UserMessage {

	private String fieldName;
	private String message;

	public UserMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFormattedMessage() {
		if (message != null) {
			return ViewHelper.convertCamelCaseToTitleCase(fieldName) + " " + getMessage();
		}
		return message;
	}

	

}
