package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.GradeSubject;

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

import com.oreon.tapovan.domain.GradeSubject;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
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
			com.oreon.tapovan.domain.Grade grade) {
		//setMaxResults(10000);
		gradeSubject.setGrade(grade);
		return getResultList();
	}

	@Observer("archivedGradeSubject")
	public void onArchive() {
		refresh();
	}

}
