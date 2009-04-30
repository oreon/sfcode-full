package org.caisi.persistence.base.exceptions;

/** This class represents non critical business exceptions e.g
 *  A user trying to register with an email when another user with that 
 *  email already exists and email is flagged as unique in model. 
 * @author jsingh
 *
 */
public  abstract class AbstractCaisiException extends RuntimeException{

	public AbstractCaisiException(String message){
		super(message);
	}
	
	/** This constructor is used to wrap a low level exception
	 * @param message
	 * @param t - the cause
	 */
	public AbstractCaisiException(String message, Throwable t){
		super(message, t);
	}
	
}