package org.witchcraft.resteasy;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.witchcraft.exceptions.ContractViolationException;
import org.witchcraft.exceptions.ServiceFault;

@Provider
public class ContractViolationExceptionMapper implements
		ExceptionMapper<ContractViolationException> {

	public Response toResponse(ContractViolationException exception) {
		ServiceFault dto = new ServiceFault();
		dto.setCode("INVALID_INPUT");
		dto.setMessage(exception.getMessage());
		//ResponseBuilder rb = Response.status(400);
		return Response.ok(dto).build();
	}

}