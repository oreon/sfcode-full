package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class BaseDTO {

	private Status responseStatus;

	private String responseMessage;

	public void setResponseStatus(Status responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Status getResponseStatus() {
		return responseStatus;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

}
