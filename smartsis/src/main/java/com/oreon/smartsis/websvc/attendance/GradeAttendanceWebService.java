package com.oreon.smartsis.websvc.attendance;

import javax.jws.WebService;
import com.oreon.smartsis.attendance.GradeAttendance;
import java.util.List;

@WebService
public interface GradeAttendanceWebService {

	public GradeAttendance loadById(Long id);

	public List<GradeAttendance> findByExample(
			GradeAttendance exampleGradeAttendance);

	public void save(GradeAttendance gradeAttendance);

}
