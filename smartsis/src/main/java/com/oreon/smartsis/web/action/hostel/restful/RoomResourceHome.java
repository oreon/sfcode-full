package com.oreon.smartsis.web.action.hostel.restful;

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

import com.oreon.smartsis.hostel.Room;

@Name("roomResourceHome")
@Path("room")
public class RoomResourceHome extends ResourceHome<Room, Long> {
	@In(create = true)
	private EntityHome<Room> roomAction;

	@Override
	public Home<?, Room> getEntityHome() {
		return roomAction;
	}

}