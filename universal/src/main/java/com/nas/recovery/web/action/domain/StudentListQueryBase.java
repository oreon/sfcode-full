package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.Student;

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

import com.oreon.tapovan.domain.Student;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
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

			"lower(student.contactDetails.primaryPhone) like concat(lower(#{studentList.student.contactDetails.primaryPhone}),'%')",

			"lower(student.contactDetails.secondaryPhone) like concat(lower(#{studentList.student.contactDetails.secondaryPhone}),'%')",

			"lower(student.contactDetails.email) like concat(lower(#{studentList.student.contactDetails.email}),'%')",

			"student.gender = #{studentList.student.gender}",

			"student.dateOfBirth >= #{studentList.dateOfBirthRange.begin}",
			"student.dateOfBirth <= #{studentList.dateOfBirthRange.end}",

			"student.grade.id = #{studentList.student.grade.id}",

			"student.dateCreated <= #{studentList.dateCreatedRange.end}",
			"student.dateCreated >= #{studentList.dateCreatedRange.begin}",};

	public List<Student> getStudentsByGrade(com.oreon.tapovan.domain.Grade grade) {
		//setMaxResults(10000);
		student.setGrade(grade);
		return getResultList();
	}

	@Observer("archivedStudent")
	public void onArchive() {
		refresh();
	}

}
