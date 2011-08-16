package com.oreon.smartsis.web.action.attendance;

import java.util.Set;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Conversational;
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

	}

	@Begin(join=true)
	public void onChangeGrade() {

		listAttendances.clear();

		Grade grade = getInstance().getGrade();
		if (grade == null)
			return;

		/*
		System.out.println(getInstance().getGrade().getId());

		String qry = "select a from GradeAttendance a where a.grade.id =  ?1 and  a.date = ?2";
		GradeAttendance ga = executeSingleResultQuery(qry, getInstance()
				.getGrade().getId(), getInstance().getDate());

		if (ga != null) {
			setInstance(ga);
			System.out.println("found " + ga.getAttendances().size());
			loadAssociations();
		} else { */

			Set<Student> students = grade.getStudents();
			for (Student student : students) {

				// if (attendance == null) {
				System.out.println(getInstance().getDate());
				Attendance attendance = new Attendance();
				attendance.setGradeAttendance(getInstance());
				attendance.setStudent(student);
				// attendance.setDate(getInstance().getDate());
				// }
				listAttendances.add(attendance);
			}

		//}
	}
}
