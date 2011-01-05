package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.Grade;

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

import com.oreon.tapovan.domain.Grade;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class GradeListQueryBase extends BaseQuery<Grade, Long> {

	private static final String EJBQL = "select grade from Grade grade";

	protected Grade grade = new Grade();

	public Grade getGrade() {
		return grade;
	}

	private com.oreon.tapovan.domain.Exam examToSearch;

	public void setExamToSearch(com.oreon.tapovan.domain.Exam examToSearch) {
		this.examToSearch = examToSearch;
	}

	public com.oreon.tapovan.domain.Exam getExamToSearch() {
		return examToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Grade> getEntityClass() {
		return Grade.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> ordinalRange = new Range<Integer>();
	public Range<Integer> getOrdinalRange() {
		return ordinalRange;
	}
	public void setOrdinal(Range<Integer> ordinalRange) {
		this.ordinalRange = ordinalRange;
	}

	private static final String[] RESTRICTIONS = {
			"grade.id = #{gradeList.grade.id}",

			"lower(grade.name) like concat(lower(#{gradeList.grade.name}),'%')",

			"#{gradeList.examToSearch} in elements(grade.exams)",

			"grade.ordinal >= #{gradeList.ordinalRange.begin}",
			"grade.ordinal <= #{gradeList.ordinalRange.end}",

			"lower(grade.section) like concat(lower(#{gradeList.grade.section}),'%')",

			"grade.dateCreated <= #{gradeList.dateCreatedRange.end}",
			"grade.dateCreated >= #{gradeList.dateCreatedRange.begin}",};

	@Observer("archivedGrade")
	public void onArchive() {
		refresh();
	}

}
