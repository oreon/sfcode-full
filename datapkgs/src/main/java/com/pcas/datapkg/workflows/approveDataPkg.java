package com.pcas.datapkg.workflows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class approveDataPkg {

	private com.pcas.datapkg.domain.DataPackage processToken;

	public void setProcessToken(com.pcas.datapkg.domain.DataPackage processToken) {
		this.processToken = processToken;
	}

	public com.pcas.datapkg.domain.DataPackage getProcessToken() {
		return processToken;
	}

}
