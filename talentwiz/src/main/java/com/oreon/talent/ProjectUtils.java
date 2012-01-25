package com.oreon.talent;

import org.apache.commons.lang.StringUtils;


public class ProjectUtils {

	
	public static void main(String[] args) {
		String fff = null;
		if (StringUtils.isNotEmpty(fff) && (StringUtils.isNotBlank(fff)) ){
			System.out.println("is not null");
		}else{
			System.out.println("is null");
		}
	}
}
