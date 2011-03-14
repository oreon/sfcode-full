package com.oreon.smartsis.websvc.attendance;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.attendance.GradeAttendance;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.attendance.GradeAttendanceWebService", serviceName = "GradeAttendanceWebService")
@Name("gradeAttendanceWebService")
public class GradeAttendanceWebServiceImpl implements GradeAttendanceWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.attendance.GradeAttendanceAction gradeAttendanceAction;

	public GradeAttendance loadById(Long id) {
		return gradeAttendanceAction.loadFromId(id);
	}

	public List<GradeAttendance> findByExample(
			GradeAttendance exampleGradeAttendance) {
		return gradeAttendanceAction.search(exampleGradeAttendance);
	}

	public void save(GradeAttendance gradeAttendance) {
		gradeAttendanceAction.persist(gradeAttendance);
	}

}
