package com.oreon.bugtracker.test;

import com.oreon.bugtracker.biz.IGeogManager;

/**
 * @author Kamalpreet Singh
 *
 */
public class BugtrackerTest {

	public static void main(String[] args) {
		IGeogManager geogManager = (IGeogManager) BugtrackerTestUtils.getBean("geogManager");
		System.out.println(geogManager);
	}
}
