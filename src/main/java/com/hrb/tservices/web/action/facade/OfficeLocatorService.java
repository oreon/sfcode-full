package com.hrb.tservices.web.action.facade;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.framework.Home;
import org.jboss.seam.resteasy.ResourceHome;
import org.jboss.seam.resteasy.ResourceQuery;

import java.util.List;

@Name("officeLocatorService")
@Path("officeLocatorService")
public class OfficeLocatorService implements java.io.Serializable {

	@In(create = true)
	com.hrb.tservices.facade.OfficeLocator officeLocator;

	@GET
	@Path("locateOfficeByPostalCode")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('OfficeLocator','locateOfficeByPostalCode')}")
	public com.hrb.tservices.dtos.OfficeLocationList locateOfficeByPostalCode(
			@QueryParam("postalCode") String postalCode,
			@QueryParam("language") String language,
			@QueryParam("securityKey") String securityKey,
			@QueryParam("maxResults") Integer maxResults) {
		return officeLocator.locateOfficeByPostalCode(postalCode, language,
				securityKey, maxResults);
	}

	@GET
	@Path("locateOfficeByAddress")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('OfficeLocator','locateOfficeByAddress')}")
	public com.hrb.tservices.dtos.OfficeLocationList locateOfficeByAddress(
			@QueryParam("address") String address,
			@QueryParam("language") String language,
			@QueryParam("city") String city,
			@QueryParam("province") String province,
			@QueryParam("securityKey") String securityKey,
			@QueryParam("maxResults") Integer maxResults) {
		return officeLocator.locateOfficeByAddress(address, language, city,
				province, securityKey, maxResults);
	}

}
