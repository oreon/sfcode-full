package org.witchcraft.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceFault {
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String code;
	private String message;

}
