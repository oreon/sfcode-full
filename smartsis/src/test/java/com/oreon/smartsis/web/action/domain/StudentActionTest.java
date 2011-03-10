package com.oreon.smartsis.web.action.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.oreon.smartsis.domain.Exam;
import com.oreon.smartsis.domain.ExamInstance;
import com.oreon.smartsis.domain.ExamScore;

public class StudentActionTest extends StudentActionTestBase {

	@Test
	public void testCalculateScore() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				StudentAction studentAction = (StudentAction) org.jboss.seam.Component
						.getInstance("studentAction");
				studentAction.setId(1L);

				/*
				Map<Exam, List<ExamScore>> mapScores = studentAction
						.getCurrentReportCard();
				
				Set<Exam> exams = mapScores.keySet();
				
				for (Exam examInstance : exams) {
					List<ExamScore> lstscore = mapScores.get(examInstance);
					System.out.println(examInstance.getName()
							+ " ");
					for (ExamScore examScore : lstscore) {
						//if(examScore.getExamInstance().getId() == examInstance.getId())
						System.out.print(examScore.getExamInstance().getGradeSubject().getSubject().getName() + ": " +examScore.getMarks() + " ");
					}
					System.out.println();
				}*/
			}

		}.run();
	}

}
