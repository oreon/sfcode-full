package com.oreon.smartsis;

import java.util.Date;

public class ProjectUtils {

	public static Integer getCurrentYear() {
		Date today = new Date();
		return today.getYear() + 1900;
	}

}
