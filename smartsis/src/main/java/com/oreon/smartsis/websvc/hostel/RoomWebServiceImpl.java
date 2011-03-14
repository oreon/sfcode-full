package com.oreon.smartsis.websvc.hostel;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.hostel.Room;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.hostel.RoomWebService", serviceName = "RoomWebService")
@Name("roomWebService")
public class RoomWebServiceImpl implements RoomWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.hostel.RoomAction roomAction;

	public Room loadById(Long id) {
		return roomAction.loadFromId(id);
	}

	public List<Room> findByExample(Room exampleRoom) {
		return roomAction.search(exampleRoom);
	}

	public void save(Room room) {
		roomAction.persist(room);
	}

}
