package org.witchcraft.resteasy;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.seam.security.AuthorizationException;
import org.jboss.seam.security.NotLoggedInException;
import org.witchcraft.exceptions.ContractViolationException;
import org.witchcraft.exceptions.ServiceFault;

@Provider
public class NotLoggedInExceptionMapper implements
		ExceptionMapper<NotLoggedInException> {

	public Response toResponse(NotLoggedInException exception) {
		ServiceFault dto = new ServiceFault();
		dto.setCode("NOT_LOGGED_IN");
		dto.setMessage("This action requires you to be logged in");
		return Response.ok(dto).build();
	}

}