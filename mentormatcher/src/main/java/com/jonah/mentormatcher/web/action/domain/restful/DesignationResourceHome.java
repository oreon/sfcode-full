package com.jonah.mentormatcher.web.action.domain.restful;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.framework.Home;
import org.jboss.seam.resteasy.ResourceHome;
import org.jboss.seam.resteasy.ResourceQuery;

import java.util.List;

import com.jonah.mentormatcher.domain.Designation;

@Name("designationResourceHome")
@Path("designation")
public class DesignationResourceHome extends ResourceHome<Designation, Long> {
	@In(create = true)
	private EntityHome<Designation> designationAction;

	@Override
	public Home<?, Designation> getEntityHome() {
		return designationAction;
	}

}
