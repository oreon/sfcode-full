package com.oreon.smartsis.web.action.exam.restful;

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

import com.oreon.smartsis.exam.ElectronicExamEvent;

@Name("electronicExamEventResourceHome")
@Path("electronicExamEvent")
public class ElectronicExamEventResourceHome
		extends
			ResourceHome<ElectronicExamEvent, Long> {
	@In(create = true)
	private EntityHome<ElectronicExamEvent> electronicExamEventAction;

	@Override
	public Home<?, ElectronicExamEvent> getEntityHome() {
		return electronicExamEventAction;
	}

}
