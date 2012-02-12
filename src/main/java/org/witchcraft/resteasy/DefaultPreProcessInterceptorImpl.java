package org.witchcraft.resteasy;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.plugins.server.servlet.HttpServletInputMessage;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.witchcraft.seam.action.BaseWebAction;

@Provider
@ServerInterceptor
public class DefaultPreProcessInterceptorImpl implements PreProcessInterceptor {

	public ServerResponse preProcess(HttpRequest request,
			ResourceMethod resourceMethod) throws Failure,
			WebApplicationException {

		HttpServletInputMessage inputMessage = (HttpServletInputMessage) request;

		if (!resourceMethod.getMethod().getName().equalsIgnoreCase("logIn") && 
				!resourceMethod.getMethod().getName().equalsIgnoreCase("logOut")) {

			HttpServletRequest req = null;

			Field fields[] = inputMessage.getClass().getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				if (fields[i].getName().equals("request")) {
					fields[i].setAccessible(true);
					try {
						req = (HttpServletRequest) fields[i].get(inputMessage);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			String key = req.getParameter("securityKey");

			System.out.println("making sure session is valid " + key);
			BaseWebAction.validateSessionContext(key);
		}
		return null;
	}

}
