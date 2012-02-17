package com.pcas.datapkg.web.action.services;

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

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

@Name("invServiceService")
@Path("invServiceService")
public class InvServiceService implements java.io.Serializable {

	@In(create = true)
	com.pcas.datapkg.services.InvService invService;

	@GET
	@Path("getStockDelta")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('InvService','getStockDelta')}")
	public com.pcas.datapkg.services.DeltaStockDto getStockDelta(
			@QueryParam("customerId") long customerId,
			@QueryParam("date") Date date) {
		return invService.getStockDelta(customerId, date);
	}

	@GET
	@Path("getInventory")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('InvService','getInventory')}")
	public com.pcas.datapkg.services.DeltaStockDto getInventory(
			@QueryParam("customerId") long customerId) {
		return invService.getInventory(customerId);
	}

	@GET
	@Path("runReportById")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('InvService','runReportById')}")
	public com.pcas.datapkg.services.GenericReportDto runReportById(
			@QueryParam("reportName") String reportName) {
		return invService.runReportById(reportName);
	}

	@GET
	@Path("getCustomers")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('InvService','getCustomers')}")
	public com.pcas.datapkg.services.GenericReportDto getCustomers() {
		return invService.getCustomers();
	}

}
