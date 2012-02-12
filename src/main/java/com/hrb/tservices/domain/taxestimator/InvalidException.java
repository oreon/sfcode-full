package com.hrb.tservices.domain.taxestimator;

public class InvalidException extends Exception 
{
	private static final long serialVersionUID = -713059818867090250L;

	public InvalidException(String message)
	{
		super(message);
	}
	
	public InvalidException(String message, Throwable t)
	{
		super(message, t);
	}
}
