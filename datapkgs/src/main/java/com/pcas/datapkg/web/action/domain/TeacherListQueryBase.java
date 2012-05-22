package com.pcas.datapkg.web.action.domain;

import com.pcas.datapkg.domain.Teacher;

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

import java.math.BigDecimal;

import com.pcas.datapkg.domain.Teacher;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TeacherListQueryBase extends BaseQuery<Teacher, Long> {

	private static final String EJBQL = "select teacher from Teacher teacher";

	protected Teacher teacher = new Teacher();

	public Teacher getTeacher() {
		return teacher;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Teacher> getEntityClass() {
		return Teacher.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<BigDecimal> salaryRange = new Range<BigDecimal>();

	public Range<BigDecimal> getSalaryRange() {
		return salaryRange;
	}
	public void setSalary(Range<BigDecimal> salaryRange) {
		this.salaryRange = salaryRange;
	}

	private Range<Date> appUser_lastLoginRange = new Range<Date>();

	public Range<Date> getAppUser_lastLoginRange() {
		return appUser_lastLoginRange;
	}
	public void setAppUser_lastLogin(Range<Date> appUser_lastLoginRange) {
		this.appUser_lastLoginRange = appUser_lastLoginRange;
	}

	private static final String[] RESTRICTIONS = {
			"teacher.id = #{teacherList.teacher.id}",

			"teacher.department.id = #{teacherList.teacher.department.id}",

			"lower(teacher.employeeNumber) like concat(lower(#{teacherList.teacher.employeeNumber}),'%')",

			"teacher.employeeType = #{teacherList.teacher.employeeType}",

			"teacher.salary >= #{teacherList.salaryRange.begin}",
			"teacher.salary <= #{teacherList.salaryRange.end}",

			"teacher.customer.id = #{teacherList.teacher.customer.id}",

			"lower(teacher.appUser.userName) like concat(lower(#{teacherList.teacher.appUser.userName}),'%')",

			"teacher.appUser.enabled = #{teacherList.teacher.appUser.enabled}",

			"lower(teacher.appUser.email) like concat(lower(#{teacherList.teacher.appUser.email}),'%')",

			"teacher.appUser.lastLogin >= #{teacherList.appUser_lastLoginRange.begin}",
			"teacher.appUser.lastLogin <= #{teacherList.appUser_lastLoginRange.end}",

			"lower(teacher.firstName) like concat(lower(#{teacherList.teacher.firstName}),'%')",

			"lower(teacher.lastName) like concat(lower(#{teacherList.teacher.lastName}),'%')",

			"lower(teacher.contactDetails.phone) like concat(lower(#{teacherList.teacher.contactDetails.phone}),'%')",

			"lower(teacher.contactDetails.secondaryPhone) like concat(lower(#{teacherList.teacher.contactDetails.secondaryPhone}),'%')",

			"lower(teacher.contactDetails.city) like concat(lower(#{teacherList.teacher.contactDetails.city}),'%')",

			"lower(teacher.skills) like concat(lower(#{teacherList.teacher.skills}),'%')",

			"teacher.dateCreated <= #{teacherList.dateCreatedRange.end}",
			"teacher.dateCreated >= #{teacherList.dateCreatedRange.begin}",};

	@Observer("archivedTeacher")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Teacher e) {

		builder.append("\""
				+ (e.getSkills() != null ? e.getSkills().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Skills" + ",");

		builder.append("\r\n");
	}
}
