package org.witchcraft.resteasy;

import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;

import com.hrb.tservices.dtos.BaseDTO;
import com.hrb.tservices.dtos.Status;


@Provider
public class DefaultPostProcessInterceptor implements PostProcessInterceptor{

	
	public void postProcess(ServerResponse resp) {
		BaseDTO baseDto = (BaseDTO) resp.getEntity();
		if(baseDto == null) return;
		
		if(baseDto.getResponseMessage() == null)
			baseDto.setResponseMessage("OK");
		if(baseDto.getResponseStatus() == null)
			baseDto.setResponseStatus(Status.SUCCESS);
	}

}
