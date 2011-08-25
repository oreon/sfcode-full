package com.jonah.mentormatcher.web.action.mentorship.restful;

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

import com.jonah.mentormatcher.domain.mentorship.MentorshipOffering;

@Name("mentorshipOfferingResourceHome")
@Path("mentorshipOffering")
public class MentorshipOfferingResourceHome
		extends
			ResourceHome<MentorshipOffering, Long> {
	@In(create = true)
	private EntityHome<MentorshipOffering> mentorshipOfferingAction;

	@Override
	public Home<?, MentorshipOffering> getEntityHome() {
		return mentorshipOfferingAction;
	}

}
