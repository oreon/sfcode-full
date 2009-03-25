package com.wcmda.model;

import org.apache.log4j.Logger;

public class Log4JUtils {

	private static Logger logger = Logger.getLogger("output");

	public static Logger getLogger() {
		return logger;
	}
	
	
}
