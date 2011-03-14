package com.oreon.smartsis.websvc.attendance;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.attendance.Attendance;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.attendance.AttendanceWebService", serviceName = "AttendanceWebService")
@Name("attendanceWebService")
public class AttendanceWebServiceImpl implements AttendanceWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.attendance.AttendanceAction attendanceAction;

	public Attendance loadById(Long id) {
		return attendanceAction.loadFromId(id);
	}

	public List<Attendance> findByExample(Attendance exampleAttendance) {
		return attendanceAction.search(exampleAttendance);
	}

	public void save(Attendance attendance) {
		attendanceAction.persist(attendance);
	}

}
