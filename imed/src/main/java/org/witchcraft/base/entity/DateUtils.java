package org.witchcraft.base.entity;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

/** Date helper methods such as find duration/age
 * @author jsingh
 *
 */
public class DateUtils {
	
	/** Calculates age in years from today's date
	 * @param fromDate 
	 * @return age in years 
	 */
	public static int calcAge(Date fromDate){
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar then = new GregorianCalendar();
		then.setTime(fromDate);
		
		return now.get(GregorianCalendar.YEAR) - then.get(GregorianCalendar.YEAR);
		
	}

}
