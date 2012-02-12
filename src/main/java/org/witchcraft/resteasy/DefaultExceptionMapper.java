package org.witchcraft.resteasy;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.witchcraft.exceptions.ContractViolationException;
import org.witchcraft.exceptions.ServiceFault;

@Provider
public class DefaultExceptionMapper implements
		ExceptionMapper<Exception> {

	private static final String SERVICE_ERROR = "SERVICE_ERROR";
	Logger logger = Logger.getLogger(DefaultExceptionMapper.class);
	
	public Response toResponse(Exception exception) {
		
		ServiceFault dto = new ServiceFault();
		dto.setCode(SERVICE_ERROR);
		dto.setMessage(exception.getMessage());
		logger.error(SERVICE_ERROR, exception);
		//ResponseBuilder rb = Response.status(400);
		return Response.ok(dto).build();
	}

}