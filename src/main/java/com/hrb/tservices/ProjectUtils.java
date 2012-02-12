package com.hrb.tservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectUtils {

	public static void amethod(){
		
	}
	
	public static Date createDefaultDate(){
		
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
		} catch (ParseException e) {
			return null;
		}
	}
}
