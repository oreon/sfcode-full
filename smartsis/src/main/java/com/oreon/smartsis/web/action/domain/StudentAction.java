package com.oreon.smartsis.web.action.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.math.stat.ranking.NaNStrategy;
import org.apache.commons.math.stat.ranking.NaturalRanking;
import org.apache.commons.math.stat.ranking.TiesStrategy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.security.Identity;
import org.witchcraft.exceptions.ContractViolationException;

import com.oreon.smartsis.MonthAttendance;
import com.oreon.smartsis.domain.Exam;
import com.oreon.smartsis.domain.ExamInstance;
import com.oreon.smartsis.domain.ExamScore;
import com.oreon.smartsis.domain.PaidFee;
import com.oreon.smartsis.domain.Student;
import com.oreon.smartsis.exam.ElectronicExamInstance;

import edu.emory.mathcs.backport.java.util.Collections;

//@Scope(ScopeType.CONVERSATION)
@Name("studentAction")
public class StudentAction extends StudentActionBase implements
		java.io.Serializable {

	List<List<ExamScore>> reportCard;
	
	//Cartesi

	List<ExamScore> subjects = new ArrayList<ExamScore>();
	private List<ExamInstance> exams;

	private List<Integer> listSubjectTotals;
	private List<ScoreMetrics> listExamTotals;

	private Long total;
	private Long totalMM;

	public Long getTotal() {
		
		Identity identity;
	
		
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
			if(getInstance().getId() == null)
				return listSubjectTotals;
			
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

	public List<ScoreMetrics> getListExamTotals() {
		if (listExamTotals == null) {
			listExamTotals = new ArrayList<ScoreMetrics>();
			String qry = "Select sum(e.marks), sum(e.examInstance.exam.maxMarks) from ExamScore e WHERE  e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 "
					+ " group by e.examInstance.exam  order by  e.examInstance.exam  ";
			List<Object[]> result = executeQuery(qry, getInstance().getId(),
					getInstance().getGrade().getId());
			for (Object[] tuple : result) {
				ScoreMetrics metrics = new ScoreMetrics();
				metrics.setTotal((Long) tuple[0]);
				metrics.setMax((Long) tuple[1]);
				metrics.setPercentage(100.0 * metrics.getTotal().doubleValue()
						/ metrics.getMax());
				listExamTotals.add(metrics);
			}
		}
		return listExamTotals;
	}

	public List<List<ExamScore>> getCurrentReportCard() {
		if (reportCard == null) {
			reportCard = new ArrayList<List<ExamScore>>();
			Student student = getInstance();
		
			
			if(student == null || student.getId() == null){
				statusMessages.add(Severity.ERROR , "No student selected");
				return reportCard;
			}
			
			String qry = "Select e from ExamScore e where e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 " +
					"and e.examInstance.exam.id = ?3 order by e.examInstance.gradeSubject.subject, e.examInstance.exam.name ";

			Set<Exam> exams = student.getGrade().getExams();

			

			for (Exam exam : exams) {

				List<ExamScore> scores = executeQuery(qry, student.getId(),
						student.getGrade().getId(), exam.getId());
				if (!scores.isEmpty())
					reportCard.add(scores);
			}

			Collections.sort(reportCard, EXAMNAME_DESC);
		}
		return reportCard;
	}

	public Integer getRank() {
		// TODO: create a flag in examinstance idicating current/or come up with
		// a strategy
		String qry = "Select sum(e.marks) from ExamScore e WHERE  e.student.grade.id  = e.examInstance.gradeSubject.grade.id "
				+ " and e.student.id in (select id from Student s where s.grade.id = ?1 ) group by e.student ";

		List<Long> list = executeQuery(qry, getInstance().getGrade().getId());

		return calcRank(list, getTotal());
	}

	public Integer getSubjectRank(Long subjectId) {
		return 0;
	}

	public Integer getExamRank(Long examId) {
		String qry = "Select sum(e.marks) from ExamScore e WHERE  e.student.grade.id  = e.examInstance.gradeSubject.grade.id "
				+ " and e.student.id in (select id from Student s where s.grade.id = ?1 ) and e.examInstance.exam.id = ?2 group by e.student ";

		List<Long> list = executeQuery(qry, getInstance().getGrade().getId(),
				examId);

		String totalQry = "Select sum(e.marks) from ExamScore e WHERE  e.student.id = ?1 and e.examInstance.gradeSubject.grade.id = ?2 "
				+ " and e.examInstance.exam.id = ?3 ";

		Long total = executeSingleResultQuery(totalQry, getInstance().getId(),
				getInstance().getGrade().getId(), examId);

		return calcRank(list, total);
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

	public Double getPercentage(Long num, Long den) {
		return 100.0 * num.doubleValue() / den;
	}

	static final Comparator<List<ExamScore>> EXAMNAME_DESC = new Comparator<List<ExamScore>>() {
		public int compare(List<ExamScore> es1, List<ExamScore> es2) {
			return es1.get(0).getExamInstance().getExam().getName().compareTo(
					es2.get(0).getExamInstance().getExam().getName());
		}
	};

	static Integer calcRank(List<Long> list, Long score) {
		Long MAX = 10000L;
		if(score == null ) return 0;
		Integer myIndex = list.indexOf(score.longValue());
		if (myIndex < 0) {
			throw new ContractViolationException(
					"No Score found in the list - " + score);
		}
		double[] scores = new double[list.size()];

		for (int i = 0; i < list.size(); i++) {
			scores[i] = MAX - list.get(i).doubleValue();
		}

		double[] ranks = new NaturalRanking(NaNStrategy.MINIMAL,
				TiesStrategy.MINIMUM).rank(scores);

		return new Double(ranks[myIndex]).intValue();
	}

	public static void main(String[] args) {

		List<Integer> myList = Arrays.asList(23, 22, 22, 25, 27, 19);
		// System.out.println(calcRank(myList, 29));
	}

	public List<MonthAttendance> getAttendanceCount() {

		String totalAttendance = "select count(*)   from Attendance attendance where "
				+ " attendance.student.id = ?1 and attendance.gradeAttendance.grade.id = student.grade.id group by Month(attendance.gradeAttendance.date) order by Month(attendance.gradeAttendance.date)";

		List<Long> totals = executeQuery(totalAttendance, getInstance().getId());

		String qry = "select Month(attendance.gradeAttendance.date) , count(*)  from Attendance attendance where attendance.absenceCode is null"
				+ " and attendance.student.id = ?1 and attendance.gradeAttendance.grade.id = student.grade.id group by Month(attendance.gradeAttendance.date) order by Month(attendance.gradeAttendance.date) ";
		List<Object[]> result = executeQuery(qry, getInstance().getId());

		List<MonthAttendance> lstAttendance = new ArrayList<MonthAttendance>();
		int i = 0;
		for (Object[] objects : result) {
			MonthAttendance monthAttendance = new MonthAttendance(
					(Integer) objects[0], (Long) objects[1], (Long) totals
							.get(i++));
			lstAttendance.add(monthAttendance);
		}

		return lstAttendance;
	}

	public List<ElectronicExamInstance> getElecExams() {
		if(isNew())
			return null;
		return executeQuery("Select e from ElectronicExamInstance e where e.student = ?1 ", getInstance());
	}

	public List<PaidFee> getPaidFees() {
		String qry = "Select f from PaidFee f where f.student.id = ?1";
		return executeQuery(qry, getInstance().getId());
	}
	
	public String admitStudent(){
		save();
		return "admitted";
	}
	
	public String dischargeStudent(){
		return "discharged";
	}
}
