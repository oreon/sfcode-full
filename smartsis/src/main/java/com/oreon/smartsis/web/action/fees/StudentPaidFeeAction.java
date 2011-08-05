package com.oreon.smartsis.web.action.fees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.FeeMonth;
import com.oreon.smartsis.domain.Grade;
import com.oreon.smartsis.domain.Student;
import com.oreon.smartsis.fees.MonthlyFee;
import com.oreon.smartsis.fees.StudentPaidFee;

//@Scope(ScopeType.CONVERSATION)
@Name("studentPaidFeeAction")
public class StudentPaidFeeAction extends StudentPaidFeeActionBase implements
		java.io.Serializable {

	private FeeMonth month;

	private Grade grade;

	private List<Student> students;

	private List<Integer> years;

	public FeeMonth getMonth() {
		return month;
	}

	public void setMonth(FeeMonth month) {
		this.month = month;
	}

	public void updateFee() {
		updateMonthlyFee();
		System.out.println("amt owed" + getInstance().getAmountOwed());
	}

	public void updateStudents() {
		try {
			String qry = "Select s from Student s where s.grade.id = ?1 ";
			students = executeQuery(qry, getGrade().getId());
		} catch (NullPointerException npe) {
			log.error("null pointer trying to get student list ");
		} catch (Exception e) {
			log.error("an error occured");
		}
	}

	private void updateMonthlyFee() {
		try {
			String qry = "Select mf from MonthlyFee mf where mf.grade.id = ?1 and mf.month = ?2";
			MonthlyFee monthlyFee = executeSingleResultQuery(qry, getInstance()
					.getStudent().getGrade().getId(), month);
			getInstance().setMonthlyFee(monthlyFee);
			getInstance().setMonth(monthlyFee.getMonth());
		} catch (NullPointerException npe) {
			log.error("null pointer trying to update fee");
		} catch (Exception e) {
			log.error("an error occured");
		}
	}

	// @Override
	public String recieveFee() {
		updateMonthlyFee();
		super.save();
		return "success";
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public List<Integer> getYears() {
		if (years == null) {
			years = new ArrayList<Integer>();
			for (int i = 2011; i < 2021; i++) {
				years.add(i);
			}
		}
		return years;
	}

	@Override
	protected StudentPaidFee createInstance() {
		// TODO Auto-generated method stub
		StudentPaidFee studentPaidFee = super.createInstance();
		Date today = new Date();
		studentPaidFee.setYear(today.getYear() + 1900);
		return studentPaidFee;
	}

}
