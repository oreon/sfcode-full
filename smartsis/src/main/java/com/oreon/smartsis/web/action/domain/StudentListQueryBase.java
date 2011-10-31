package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Student;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.smartsis.domain.Student;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class StudentListQueryBase extends BaseQuery<Student, Long> {

	private static final String EJBQL = "select student from Student student";

	protected Student student = new Student();

	public Student getStudent() {
		return student;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Student> getEntityClass() {
		return Student.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateOfBirthRange = new Range<Date>();
	public Range<Date> getDateOfBirthRange() {
		return dateOfBirthRange;
	}
	public void setDateOfBirth(Range<Date> dateOfBirthRange) {
		this.dateOfBirthRange = dateOfBirthRange;
	}

	private Range<Double> scholarshipRange = new Range<Double>();
	public Range<Double> getScholarshipRange() {
		return scholarshipRange;
	}
	public void setScholarship(Range<Double> scholarshipRange) {
		this.scholarshipRange = scholarshipRange;
	}

	private Range<Integer> rollNumberRange = new Range<Integer>();
	public Range<Integer> getRollNumberRange() {
		return rollNumberRange;
	}
	public void setRollNumber(Range<Integer> rollNumberRange) {
		this.rollNumberRange = rollNumberRange;
	}

	private Range<Date> discontinueDateRange = new Range<Date>();
	public Range<Date> getDiscontinueDateRange() {
		return discontinueDateRange;
	}
	public void setDiscontinueDate(Range<Date> discontinueDateRange) {
		this.discontinueDateRange = discontinueDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"student.id = #{studentList.student.id}",

			"lower(student.firstName) like concat(lower(#{studentList.student.firstName}),'%')",

			"lower(student.lastName) like concat(lower(#{studentList.student.lastName}),'%')",

			"lower(student.contactDetails.primaryPhone) like concat(lower(#{studentList.student.contactDetails.primaryPhone}),'%')",

			"lower(student.contactDetails.secondaryPhone) like concat(lower(#{studentList.student.contactDetails.secondaryPhone}),'%')",

			"lower(student.contactDetails.city) like concat(lower(#{studentList.student.contactDetails.city}),'%')",

			"student.gender = #{studentList.student.gender}",

			"student.dateOfBirth >= #{studentList.dateOfBirthRange.begin}",
			"student.dateOfBirth <= #{studentList.dateOfBirthRange.end}",

			"student.grade.id = #{studentList.student.grade.id}",

			"student.scholarship >= #{studentList.scholarshipRange.begin}",
			"student.scholarship <= #{studentList.scholarshipRange.end}",

			"student.rollNumber >= #{studentList.rollNumberRange.begin}",
			"student.rollNumber <= #{studentList.rollNumberRange.end}",

			"student.discontinueDate >= #{studentList.discontinueDateRange.begin}",
			"student.discontinueDate <= #{studentList.discontinueDateRange.end}",

			"student.discontinueReason = #{studentList.student.discontinueReason}",

			"student.primary.id = #{studentList.student.primary.id}",

			"student.secondary.id = #{studentList.student.secondary.id}",

			"student.dateCreated <= #{studentList.dateCreatedRange.end}",
			"student.dateCreated >= #{studentList.dateCreatedRange.begin}",};

	public List<Student> getStudentsByGrade(
			com.oreon.smartsis.domain.Grade grade) {
		//setMaxResults(10000);
		student.setGrade(grade);
		return getResultList();
	}

	@Observer("archivedStudent")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Student e) {

		builder.append("\"" + (e.getGender() != null ? e.getGender() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDateOfBirth() != null ? e.getDateOfBirth() : "")
				+ "\",");

		builder.append("\""
				+ (e.getGrade() != null ? e.getGrade().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getScholarship() != null ? e.getScholarship() : "")
				+ "\",");

		builder.append("\""
				+ (e.getRollNumber() != null ? e.getRollNumber() : "") + "\",");

		builder
				.append("\""
						+ (e.getDiscontinueDate() != null ? e
								.getDiscontinueDate() : "") + "\",");

		builder.append("\""
				+ (e.getDiscontinueReason() != null
						? e.getDiscontinueReason()
						: "") + "\",");

		builder.append("\""
				+ (e.getPrimary() != null ? e.getPrimary().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getSecondary() != null ? e.getSecondary().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Gender" + ",");

		builder.append("DateOfBirth" + ",");

		builder.append("Grade" + ",");

		builder.append("Scholarship" + ",");

		builder.append("RollNumber" + ",");

		builder.append("DiscontinueDate" + ",");

		builder.append("DiscontinueReason" + ",");

		builder.append("Primary" + ",");

		builder.append("Secondary" + ",");

		builder.append("\r\n");
	}
}
