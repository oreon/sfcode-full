
	
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
public class GradeAttendanceAction extends GradeAttendanceActionBase implements java.io.Serializable{
	
	@Override
	public void prePopulateListAttendances() {
		if (getInstance().getId() == null && listAttendances.isEmpty()) {
			Grade grade = getInstance().getGrade();
			if (grade == null)
				return;
			Set<Student> students = grade.getStudents();
			
			for (Student student : students) {
				
				Attendance score = new Attendance();
				score.setGradeAttendance(getInstance());
				score.setStudent(student);
				score.setDate(getInstance().getDate());
				listAttendances.add(score);
			}
		}
	
	}
}
	