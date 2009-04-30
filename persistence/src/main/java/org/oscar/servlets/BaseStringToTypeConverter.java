package org.oscar.servlets;

import java.util.Date;

/**
 * @author jsingh
 * 
 */
public class BaseStringToTypeConverter<T> {

	T t;

	public T getConvertedValue(String strValue){
		if( t.getClass().equals(Integer.class) )
			return (T)new Integer(Integer.parseInt(strValue));
		else if (t.getClass().equals(java.util.Date.class))
			return (T)( new Date());
		else
			return (T) strValue;
	}

	

}
