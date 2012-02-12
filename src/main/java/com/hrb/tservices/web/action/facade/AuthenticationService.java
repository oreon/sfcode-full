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

@Name("authenticationService")
@Path("authenticationService")
public class AuthenticationService implements java.io.Serializable {

	@In(create = true)
	com.hrb.tservices.facade.Authentication authentication;

	@POST
	@Path("login")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('Authentication','login')}")
	public com.hrb.tservices.dtos.AuthenticationResponse login(
			@FormParam("username") String username,
			@FormParam("password") String password) {
		return authentication.login(username, password);
	}

	@GET
	@Path("logout")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('Authentication','logout')}")
	public com.hrb.tservices.dtos.AuthenticationResponse logout() {
		return authentication.logout();
	}

}
