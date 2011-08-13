package com.oreon.smartsis.web.action.attendance;

import java.util.Set;

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.attendance.Attendance;
import com.oreon.smartsis.attendance.GradeAttendance;
import com.oreon.smartsis.domain.ExamScore;
import com.oreon.smartsis.domain.Grade;
import com.oreon.smartsis.domain.Student;

//@Scope(ScopeType.CONVERSATION)
@Name("gradeAttendanceAction")
public class GradeAttendanceAction extends GradeAttendanceActionBase implements
		java.io.Serializable {

	@Override
	public void prePopulateListAttendances() {
		if (getInstance().getId() == null && listAttendances.isEmpty()) {

			Grade grade = getInstance().getGrade();
			if (grade == null)
				return;
			
			String qry = "select a from GradeAttendance a where a.grade.id =  ?1 and  a.date = ?2";
			GradeAttendance ga = executeSingleResultQuery(qry, getInstance()
					.getGrade().getId(), getInstance().getDate());
			
			
			if (ga != null) {
				setId(ga.getId());
			} else {
					
				Set<Student> students = grade.getStudents();
				for (Student student : students) {

					// if (attendance == null) {
					System.out.println(getInstance().getDate());
					Attendance attendance = new Attendance();
					attendance.setGradeAttendance(getInstance());
					attendance.setStudent(student);
					attendance.setDate(getInstance().getDate());
					// }
					listAttendances.add(attendance);
				}

			}
		}
	}

	public void onChangeGrade() {

	}
}
