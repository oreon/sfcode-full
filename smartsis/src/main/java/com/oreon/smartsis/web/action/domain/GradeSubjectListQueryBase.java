package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.GradeSubject;

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

import com.oreon.smartsis.domain.GradeSubject;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class GradeSubjectListQueryBase
		extends
			BaseQuery<GradeSubject, Long> {

	private static final String EJBQL = "select gradeSubject from GradeSubject gradeSubject";

	protected GradeSubject gradeSubject = new GradeSubject();

	public GradeSubject getGradeSubject() {
		return gradeSubject;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<GradeSubject> getEntityClass() {
		return GradeSubject.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"gradeSubject.id = #{gradeSubjectList.gradeSubject.id}",

			"gradeSubject.subject.id = #{gradeSubjectList.gradeSubject.subject.id}",

			"gradeSubject.employee.id = #{gradeSubjectList.gradeSubject.employee.id}",

			"gradeSubject.grade.id = #{gradeSubjectList.gradeSubject.grade.id}",

			"gradeSubject.dateCreated <= #{gradeSubjectList.dateCreatedRange.end}",
			"gradeSubject.dateCreated >= #{gradeSubjectList.dateCreatedRange.begin}",};

	public List<GradeSubject> getGradeSubjectsByGrade(
			com.oreon.smartsis.domain.Grade grade) {
		//setMaxResults(10000);
		gradeSubject.setGrade(grade);
		return getResultList();
	}

	@Observer("archivedGradeSubject")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, GradeSubject e) {

		builder.append("\""
				+ (e.getSubject() != null ? e.getSubject().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getEmployee() != null ? e.getEmployee().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getGrade() != null ? e.getGrade().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Subject" + ",");

		builder.append("Employee" + ",");

		builder.append("Grade" + ",");

		builder.append("\r\n");
	}
}
