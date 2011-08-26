package org.witchcraft.resteasy;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

@Provider
public class DefaultPreProcessInterceptorImpl implements PreProcessInterceptor{

	public ServerResponse preProcess(HttpRequest arg0, ResourceMethod resourceMethod)
			throws Failure, WebApplicationException {
		
		if(!resourceMethod.getMethod().getName().equals("logIn")){	
			System.out.println("making sure session is valid");
		}
		return null;
	}

}
