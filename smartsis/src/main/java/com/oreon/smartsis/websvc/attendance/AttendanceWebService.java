package com.oreon.smartsis.websvc.attendance;

import javax.jws.WebService;
import com.oreon.smartsis.attendance.Attendance;
import java.util.List;

@WebService
public interface AttendanceWebService {

	public Attendance loadById(Long id);

	public List<Attendance> findByExample(Attendance exampleAttendance);

	public void save(Attendance attendance);

}
