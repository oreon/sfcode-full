package com.oreon.smartsis.web.action.services;

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

@Name("promotionServiceService")
@Path("promotionServiceService")
public class PromotionServiceService implements java.io.Serializable {

	@In(create = true)
	com.oreon.smartsis.services.PromotionService promotionService;

	@GET
	@Path("promote")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('PromotionService','promote')}")
	public Boolean promote() {
		return promotionService.promote();
	}

}
