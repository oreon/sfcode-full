package org.sonatype.mavenbook.web;

import java.io.*;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.*;
import javax.servlet.http.*;

public class SimpleServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	out.println( "SimpleServlet Executed again and again" );
        out.flush();
        out.close();
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