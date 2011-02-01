package com.nas.recovery.web.action.domain;

import org.wc.mytapovan.domain.Student;

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

import org.wc.mytapovan.domain.Student;

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

	private static final String[] RESTRICTIONS = {
			"student.id = #{studentList.student.id}",

			"lower(student.firstName) like concat(lower(#{studentList.student.firstName}),'%')",

			"lower(student.lastName) like concat(lower(#{studentList.student.lastName}),'%')",

			"student.sponsorship.id = #{studentList.student.sponsorship.id}",

			"student.gender = #{studentList.student.gender}",

			"student.dateOfBirth >= #{studentList.dateOfBirthRange.begin}",
			"student.dateOfBirth <= #{studentList.dateOfBirthRange.end}",

			"lower(student.description) like concat(lower(#{studentList.student.description}),'%')",

			"student.grade.id = #{studentList.student.grade.id}",

			"student.dateCreated <= #{studentList.dateCreatedRange.end}",
			"student.dateCreated >= #{studentList.dateCreatedRange.begin}",};

	public List<Student> getStudentBySponsorship(
			org.wc.mytapovan.domain.Sponsorship sponsorship) {
		//setMaxResults(10000);
		student.setSponsorship(sponsorship);
		return getResultList();
	}

	public List<Student> getStudentsByGrade(org.wc.mytapovan.domain.Grade grade) {
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

		builder.append("\""
				+ (e.getSponsorship() != null ? e.getSponsorship()
						.getDisplayName() : "") + "\",");

		builder.append("\"" + (e.getGender() != null ? e.getGender() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDateOfBirth() != null ? e.getDateOfBirth() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription() : "")
				+ "\",");

		builder.append("\""
				+ (e.getGrade() != null ? e.getGrade().getDisplayName() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Sponsorship" + ",");

		builder.append("Gender" + ",");

		builder.append("DateOfBirth" + ",");

		builder.append("Description" + ",");

		builder.append("Grade" + ",");

		builder.append("\r\n");
	}
}
