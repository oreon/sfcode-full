package com.ganz.wonders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;

public class UtilsClass {

	public static int add(int a, int b) {
		return a + b;
	}

	public static String getBuildNumber(ServletContext context) {

		String appServerHome = context.getRealPath("/");

		File manifestFile = new File(appServerHome, "META-INF/MANIFEST.MF");

		Manifest mf = new Manifest();
		try {
			mf.read(new FileInputStream(manifestFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ("Couldnt read MANIFEST");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Attributes atts = mf.getMainAttributes();

		StringBuffer buffer = new StringBuffer();
		buffer.append("Version: "
				+ atts.getValue("Implementation-Version") + "<br/>");
		buffer.append("Build: " + atts.getValue("Implementation-Build"));
		
		return buffer.toString();

	}

}
