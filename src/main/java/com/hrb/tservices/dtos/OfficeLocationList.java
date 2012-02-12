package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class OfficeLocationList extends com.hrb.tservices.dtos.BaseDTO {

	private java.util.List<com.hrb.tservices.dtos.OfficeLocation> officeLocations = new ArrayList<com.hrb.tservices.dtos.OfficeLocation>();

	public void setOfficeLocations(List<OfficeLocation> officeLocations) {
		this.officeLocations = officeLocations;
	}

	public List<OfficeLocation> getOfficeLocations() {
		return officeLocations;
	}

}
