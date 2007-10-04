package org.witchcraft.model.support.springbeanhelpers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** contains utility methods to retrieve spring beans 
 * @author jsingh
 *
 */
public class BeanHelper {
	
	private static ApplicationContext applicationContext;
	
	static {
		applicationContext =  new ClassPathXmlApplicationContext
			(new String[]{"classpath:/applicationContext.xml", "classpath:/testDataFactories.xml"});
	}
	
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}

}
