
	
package com.oreon.smartsis.web.action.domain;
	

import java.util.Set;

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.ExamScore;
import com.oreon.smartsis.domain.GradeSubject;
import com.oreon.smartsis.domain.Student;

	
//@Scope(ScopeType.CONVERSATION)
@Name("examInstanceAction")
public class ExamInstanceAction extends ExamInstanceActionBase implements java.io.Serializable{
	
	@Override
	public void prePopulateListExamScores() {
		if(getInstance().getId() == null && listExamScores.isEmpty()){
			GradeSubject gradeSubject = getInstance().getGradeSubject();
			if(gradeSubject == null)
				return;
			Set<Student> students = getInstance().getGradeSubject().getGrade().getStudents();
			for (Student student : students) {
				ExamScore score = new ExamScore();
				score.setStudent(student);
				score.setExamInstance(this.getInstance());
				listExamScores.add(score);
			}
		}
	}
}
	