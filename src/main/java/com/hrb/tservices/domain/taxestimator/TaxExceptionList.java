package com.hrb.tservices.domain.taxestimator;

import java.util.ArrayList;
import java.util.List;

public class TaxExceptionList extends Exception 
{
	private static final long serialVersionUID = -8089887894152675502L;
	private List<String> messages;
	
	public TaxExceptionList()
	{
		super();
		messages = new ArrayList<String>(20);
	}
	
	public TaxExceptionList(List<String> messages)
	{
		super();
		this.messages = messages;
	}
	
	public void addWarning(String message)
	{
		messages.add(message);
	}
	
	public List<String> getList()
	{
		return messages;
	}
	
	public boolean isMessageFree()
	{
		return messages.isEmpty();
	}
}
