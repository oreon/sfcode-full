package org.witchcraft.model.utils;

import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class DateUtilsTest extends TestCase {

	public void testCalcAge() {
		GregorianCalendar gc = new GregorianCalendar(1976, 9, 28);
		int age = DateUtils.calcAge(gc.getTime());
		System.out.println(age);
		assertTrue(age > 25);
	}

}
