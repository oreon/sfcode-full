package com.oreon.smartsis.web.action.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.Tuple;

import org.apache.commons.math.stat.ranking.NaNStrategy;
import org.apache.commons.math.stat.ranking.NaturalRanking;
import org.apache.commons.math.stat.ranking.TiesStrategy;
import org.jboss.seam.annotations.Name;
import org.witchcraft.exceptions.ContractViolationException;

import com.oreon.smartsis.domain.Exam;
import com.oreon.smartsis.domain.ExamInstance;
import com.oreon.smartsis.domain.ExamScore;
import com.oreon.smartsis.domain.Student;

import edu.emory.mathcs.backport.java.util.Collections;

//@Scope(ScopeType.CONVERSATION)
@Name("studentAction")
public class StudentAction extends StudentActionBase implements
		java.io.Serializable {

	List<List<ExamScore>> reportCard;

	List<ExamScore> subjects = new ArrayList<ExamScore>();
	private List<ExamInstance> exams;

	private List<Integer> listSubjectTotals;
	private List<Integer> listExamTotals;

	private Long total;
	private Long totalMM;

	public Long getTotal() {
		if (total == null) {
			String qry = "Select sum(e.marks) from ExamScore e WHERE  e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 ";
			total = executeSingleResultQuery(qry, getInstance().getId(),
					getInstance().getGrade().getId());
		}
		return total;
	}

	public void setTotal(Long getTotal) {
		this.total = getTotal;
	}

	public Long getTotalMM() {
		if (totalMM == null) {
			String qry = "Select sum(e.examInstance.exam.maxMarks) from ExamScore e WHERE  e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 ";
			totalMM = executeSingleResultQuery(qry, getInstance().getId(),
					getInstance().getGrade().getId());
		}
		return totalMM;
	}

	public Double getPercentage() {
		return 100.0 * total.doubleValue() / totalMM;
	}

	public void setTotalMM(Long getTotalMM) {
		totalMM = getTotalMM;
	}

	public List<Integer> getListSubjectTotals() {
		if (listSubjectTotals == null) {
			listSubjectTotals = new ArrayList<Integer>();
			String qry = "Select sum(e.marks) from ExamScore e WHERE  e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 "
					+ " group by e.examInstance.gradeSubject.subject  order by  e.examInstance.gradeSubject.subject  ";
			listSubjectTotals = executeQuery(qry, getInstance().getId(),
					getInstance().getGrade().getId());
		}
		return listSubjectTotals;
	}

	public void setListSubjectTotals(List<Integer> listSubjectTotals) {
		this.listSubjectTotals = listSubjectTotals;

	}

	public List<Integer> getListExamTotals() {
		if (listExamTotals == null) {
			listExamTotals = new ArrayList<Integer>();
			String qry = "Select sum(e.marks) from ExamScore e WHERE  e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 "
					+ " group by e.examInstance.exam  order by  e.examInstance.exam  ";
			listExamTotals = executeQuery(qry, getInstance().getId(),
					getInstance().getGrade().getId());
		}
		return listExamTotals;
	}

	public void setListExamTotals(List<Integer> listExamTotals) {
		this.listExamTotals = listExamTotals;
	}

	public List<List<ExamScore>> getCurrentReportCard() {
		if (reportCard == null) {
			Student student = getInstance();
			String qry = "Select e from ExamScore e where e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 " +
					"and e.examInstance.exam.id = ?3 order by e.examInstance.gradeSubject.subject, e.examInstance.exam.name ";

			Set<Exam> exams = student.getGrade().getExams();

			reportCard = new ArrayList<List<ExamScore>>();

			for (Exam exam : exams) {

				List<ExamScore> scores = executeQuery(qry, student.getId(),
						student.getGrade().getId(), exam.getId());
				if (!scores.isEmpty())
					reportCard.add(scores);
			}

			Collections.sort(reportCard, MARKS_DESC);
		}
		return reportCard;
	}
	
	public Integer getRank(){
		//TODO: create a flag in examinstance idicating current/or come up with a strategy 
		String qry = "Select sum(e.marks) from ExamScore e WHERE  e.student.grade.id  = e.examInstance.gradeSubject.grade.id " +
				" and e.student.id in (select id from Student s where s.grade.id = ?1 ) group by e.student ";
		
		List<Long> list = executeQuery(qry, getInstance().getGrade().getId());
		
		System.out.println("printing score " + list.size());
		
		return calcRank(list, getTotal());
	}

	public List<ExamScore> getSubjectsList() {
		List<List<ExamScore>> reportCard = getCurrentReportCard();
		
		if (!getCurrentReportCard().isEmpty()) {
			subjects = reportCard.get(0);
		}
		return subjects;
	}

	public void setExams(List<ExamInstance> exams) {
		this.exams = exams;
	}

	public List<ExamInstance> getExams() {
		return exams;
	}

	static final Comparator<List<ExamScore>> MARKS_DESC = new Comparator<List<ExamScore>>() {
		public int compare(List<ExamScore> es1, List<ExamScore> es2) {
			return es1.get(0).getExamInstance().getExam().getName().compareTo(
					es2.get(0).getExamInstance().getExam().getName());
		}
	};
	
	static Integer calcRank(List<Long> list, Long score){
		Long  MAX = 10000L;
		Integer myIndex = list.indexOf(score.longValue());
		if(myIndex < 0){
			throw new ContractViolationException("No Score found in the list - " + score);
		}
		double[] scores = new double[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			scores[i] = MAX -  list.get(i).doubleValue();
		}
		
		double[] ranks = new NaturalRanking(NaNStrategy.MINIMAL,
				TiesStrategy.MINIMUM).rank(scores);

		return new Double( ranks[myIndex]).intValue() ;
	}

	public static void main(String[] args) {
		
		List<Integer> myList = Arrays.asList(23, 22, 22, 25, 27, 19);
		//System.out.println(calcRank(myList, 29));
	}
}
