package com.nas.recovery.web.action.domain;

import org.wc.mytapovan.domain.Grade;

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

import org.wc.mytapovan.domain.Grade;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class GradeListQueryBase extends BaseQuery<Grade, Long> {

	private static final String EJBQL = "select grade from Grade grade";

	protected Grade grade = new Grade();

	public Grade getGrade() {
		return grade;
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

	private static final String[] RESTRICTIONS = {
			"grade.id = #{gradeList.grade.id}",

			"lower(grade.name) like concat(lower(#{gradeList.grade.name}),'%')",

			"grade.dateCreated <= #{gradeList.dateCreatedRange.end}",
			"grade.dateCreated >= #{gradeList.dateCreatedRange.begin}",};

	@Observer("archivedGrade")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Grade e) {

		builder.append("\"" + (e.getName() != null ? e.getName() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
