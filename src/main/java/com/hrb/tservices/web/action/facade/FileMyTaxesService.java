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

@Name("fileMyTaxesService")
@Path("fileMyTaxesService")
public class FileMyTaxesService implements java.io.Serializable {

	@In(create = true)
	com.hrb.tservices.facade.FileMyTaxes fileMyTaxes;

	@GET
	@Path("viewOfferInvite")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('FileMyTaxes','viewOfferInvite')}")
	public com.hrb.tservices.dtos.OfferInvite viewOfferInvite(
			@QueryParam("language") String language,
			@QueryParam("securityKey") String securityKey) {
		return fileMyTaxes.viewOfferInvite(language, securityKey);
	}

	@GET
	@Path("viewOffer")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('FileMyTaxes','viewOffer')}")
	public Response viewOffer(@QueryParam("language") String language,
			@QueryParam("securityKey") String securityKey) {
		return fileMyTaxes.viewOffer(language, securityKey);
	}

}
