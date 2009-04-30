package org.caisi.persistence.base.exceptions;

/** To be thrown in case of a programmer error
 * @author jsingh
 *
 */
public class ConstraintException extends AbstractCaisiException{

	public ConstraintException(String message){
		super(message);
	}
	
	/** This constructor is used to wrap a low level exception
	 * @param message
	 * @param t - the cause
	 */
	public ConstraintException(String message, Throwable t){
		super(message, t);
	}
}
