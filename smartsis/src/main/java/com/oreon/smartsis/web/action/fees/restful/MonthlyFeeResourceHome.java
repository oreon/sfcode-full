package com.oreon.smartsis.web.action.fees.restful;

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

import com.oreon.smartsis.fees.MonthlyFee;

@Name("monthlyFeeResourceHome")
@Path("monthlyFee")
public class MonthlyFeeResourceHome extends ResourceHome<MonthlyFee, Long> {
	@In(create = true)
	private EntityHome<MonthlyFee> monthlyFeeAction;

	@Override
	public Home<?, MonthlyFee> getEntityHome() {
		return monthlyFeeAction;
	}

}
