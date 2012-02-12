package org.witchcraft.resteasy;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.seam.security.AuthorizationException;
import org.witchcraft.exceptions.ContractViolationException;
import org.witchcraft.exceptions.ServiceFault;

@Provider
public class AuthorizationExceptionMapper implements
		ExceptionMapper<AuthorizationException> {

	public Response toResponse(AuthorizationException exception) {
		ServiceFault dto = new ServiceFault();
		dto.setCode("NOT_AUTHORIZED");
		dto.setMessage("You are not authorized to perform this action");
		//ResponseBuilder rb = Response.status(400);
		return Response.ok(dto).build();
	}

}