package com.oreon.smartsis.web.action.domain;

import java.util.Comparator;
import java.util.Set;

import org.apache.commons.math.stat.ranking.NaNStrategy;
import org.apache.commons.math.stat.ranking.NaturalRanking;
import org.apache.commons.math.stat.ranking.TiesStrategy;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.ExamInstance;
import com.oreon.smartsis.domain.ExamScore;
import com.oreon.smartsis.domain.GradeSubject;
import com.oreon.smartsis.domain.Student;

//@Scope(ScopeType.CONVERSATION)
@Name("examInstanceAction")
public class ExamInstanceAction extends ExamInstanceActionBase implements
		java.io.Serializable {

	protected void ensureExamApplied() {
		if (instance.getExam() != null) {
			if (!instance.getGradeSubject().getGrade().getExams().contains(
					instance.getExam())) {
				instance.getGradeSubject().getGrade().getExams().add(
						instance.getExam());
			}
		}
	}

	@Override
	public void prePopulateListExamScores() {
		if (getInstance().getId() == null && listExamScores.isEmpty()) {

			GradeSubject gradeSubject = getInstance().getGradeSubject();
			if (gradeSubject == null)
				return;
			System.out.println(instance.getExam().getName() + " " + gradeSubject.getSubject().getDisplayName());

			ensureExamApplied();	

			String qry = "Select e from ExamInstance e where e.gradeSubject = ?1 and e.exam = ?2";
			ExamInstance examInstance = executeSingleResultQuery(qry,
					gradeSubject, instance.getExam());

			if (examInstance != null) {
				setInstance(examInstance);
				loadAssociations();
			} else {
				Set<Student> students = getInstance().getGradeSubject()
						.getGrade().getStudents();
				for (Student student : students) {
					ExamScore score = new ExamScore();
					score.setStudent(student);
					score.setExamInstance(this.getInstance());
					listExamScores.add(score);
				}
			}
		}
	}

	@Override
	public String save() {
		if (!listExamScores.isEmpty()) {
			updateAverageAndRank();
		}
		return super.save();
	}

	private void updateAverageAndRank() {
		Integer totalMarks = 0;
		double[] scores = new double[listExamScores.size()];

		for (int i = 0; i < listExamScores.size(); i++) {
			scores[i] = instance.getExam().getMaxMarks()
					- listExamScores.get(i).getMarks().doubleValue();
		}

		double[] ranks = new NaturalRanking(NaNStrategy.MINIMAL,
				TiesStrategy.MINIMUM).rank(scores);

		for (int i = 0; i < listExamScores.size(); i++) {
			totalMarks += listExamScores.get(i).getMarks();
			listExamScores.get(i).setRank(new Double(ranks[i]).intValue());
		}

		int average = totalMarks / listExamScores.size();
		getInstance().setAverage(average);

		// Collections.sort(listExamScores, MARKS_DESC);
	}

	static final Comparator<ExamScore> MARKS_DESC = new Comparator<ExamScore>() {
		public int compare(ExamScore es1, ExamScore es2) {
			return es2.getMarks().compareTo(es1.getMarks());
		}
	};

	public void onChangeGradeSubject() {
		prePopulateListExamScores();
	}
}
