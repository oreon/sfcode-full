package com.oreon.smartsis.web.action.domain.restful;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.resteasy.ResourceQuery;

import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.framework.Home;
import org.jboss.seam.resteasy.ResourceHome;

import org.jboss.seam.annotations.In;

import java.util.List;

import com.oreon.smartsis.domain.Fee;

@Name("feeResourceHome")
@Path("fee")
public class FeeResourceHome extends ResourceHome<Fee, Long> {
	@In(create = true)
	private EntityHome<Fee> feeAction;

	@Override
	public Home<?, Fee> getEntityHome() {
		return feeAction;
	}

}
