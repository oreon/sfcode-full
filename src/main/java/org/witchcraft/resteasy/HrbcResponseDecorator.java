package org.witchcraft.resteasy;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;

@Provider
@ServerInterceptor
public class HrbcResponseDecorator implements MessageBodyWriterInterceptor {

	public void write(MessageBodyWriterContext context) throws IOException,
			WebApplicationException {
		
		//context.getOutputStream().write("Hi THERE".getBytes());
		//HrbcResponse hrbcResponse = (HrbcResponse) context.getEntity();
		//hrbcResponse.setPayload();
		//hrbcResponse.setMessage("OK");
		//hrbcResponse.setExecutionStatus(Status.STATUS_SUCCESS);
		// context.getHeaders().add("My-Header", "custom");
		//context.setEntity(hrbcResponse);
		context.proceed();
	}
}