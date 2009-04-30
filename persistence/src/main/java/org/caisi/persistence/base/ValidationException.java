package org.caisi.persistence.base;

import java.util.List;

import org.caisi.persistence.base.exceptions.BusinessException;

/**
 * Any exception related to validation
 * 
 * @author jsingh
 * 
 */
public class ValidationException extends BusinessException {

	private List<UserMessage> messages;
	
	
	public List<UserMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<UserMessage> messages) {
		this.messages = messages;
	}

	public ValidationException(List<UserMessage> messages) {
		super("A validation error has occured");
		this.messages = messages;
	}

	public ValidationException(String message) {
		super(message);
	}

	/**
	 * This constructor is used to wrap a low level exception
	 * 
	 * @param message
	 * @param t
	 *            - the cause
	 */
	public ValidationException(String message, Throwable t) {
		super(message, t);
	}
}
