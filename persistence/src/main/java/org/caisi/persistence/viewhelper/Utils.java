package org.caisi.persistence.viewhelper;

import org.apache.commons.lang.StringUtils;

public class Utils {

	public static String getNonNull(String val){
		return val == null?StringUtils.EMPTY:val;
	}
}
