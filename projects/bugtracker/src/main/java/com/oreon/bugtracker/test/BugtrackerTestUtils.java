package com.oreon.bugtracker.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Kamalpreet Singh
 *
 */
public class BugtrackerTestUtils {

	public static ApplicationContext getApplicationContext() {
		ApplicationContext applicationContext = 
			new FileSystemXmlApplicationContext("src/main/java/com/oreon/bugtracker/test/parent-context.xml");
		return applicationContext;
	}
	
	public static Object getBean(String beanIdentifier) {
		return getApplicationContext().getBean(beanIdentifier);
	}
}
