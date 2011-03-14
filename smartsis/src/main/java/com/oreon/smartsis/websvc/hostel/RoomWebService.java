package com.oreon.smartsis.websvc.hostel;

import javax.jws.WebService;
import com.oreon.smartsis.hostel.Room;
import java.util.List;

@WebService
public interface RoomWebService {

	public Room loadById(Long id);

	public List<Room> findByExample(Room exampleRoom);

	public void save(Room room);

}
