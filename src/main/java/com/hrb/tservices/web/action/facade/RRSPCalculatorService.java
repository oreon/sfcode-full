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

@Name("rRSPCalculatorService")
@Path("rRSPCalculatorService")
public class RRSPCalculatorService implements java.io.Serializable {

	@In(create = true)
	com.hrb.tservices.facade.RRSPCalculator rRSPCalculator;

	@POST
	@Path("getRRSPCalculation")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('RRSPCalculator','getRRSPCalculation')}")
	public com.hrb.tservices.dtos.RRSPCalculation getRRSPCalculation(
			@FormParam("taxYear") Integer taxYear,
			@FormParam("province") String province,
			@FormParam("userDeductionLimit") Long userDeductionLimit,
			@FormParam("spouseDeductionLimit") Long spouseDeductionLimit,
			@FormParam("userIncome") Long userIncome,
			@FormParam("spousIncome") Long spousIncome,
			@FormParam("userContribution") Long userContribution,
			@FormParam("spouseContribution") Long spouseContribution,
			@FormParam("securityKey") String securityKey,
			@FormParam("maxResults") Integer maxResults) {
		return rRSPCalculator.getRRSPCalculation(taxYear, province,
				userDeductionLimit, spouseDeductionLimit, userIncome,
				spousIncome, userContribution, spouseContribution, securityKey,
				maxResults);
	}

}
