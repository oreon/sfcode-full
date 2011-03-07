package com.oreon.smartsis.web.action.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Exam;
import com.oreon.smartsis.domain.ExamInstance;
import com.oreon.smartsis.domain.ExamScore;
import com.oreon.smartsis.domain.Student;

//@Scope(ScopeType.CONVERSATION)
@Name("studentAction")
public class StudentAction extends StudentActionBase implements
		java.io.Serializable {

	public Map<Exam, List<ExamScore>> getCurrentReportCard() {
		Student student = getInstance();
		String qry = "Select e from ExamScore e where e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 order by e.examInstance.gradeSubject.subject, e.examInstance.exam.name ";

		List<ExamScore> scores = executeQuery(qry, student.getId(), student
				.getGrade().getId());

		Map<Exam, List<ExamScore>> mapScores = new HashMap<Exam, List<ExamScore>>();

		for (ExamScore examScore : scores) {
			List<ExamScore> listScores = mapScores.get(examScore.getExamInstance().getExam());
			
			if (listScores == null) {
				listScores = new ArrayList<ExamScore>();
			}
			listScores.add(examScore);
			System.out.println("adding " + examScore.getExamInstance().getGradeSubject().getSubject().getName() + 
					" " + examScore.getExamInstance().getExam().getName());
			mapScores.put(examScore.getExamInstance().getExam(), listScores);
		}
		return mapScores;
	}
}
