package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class AuthenticationResponse extends com.hrb.tservices.dtos.BaseDTO {

	private String securityKey;

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public String getSecurityKey() {
		return securityKey;
	}

}
