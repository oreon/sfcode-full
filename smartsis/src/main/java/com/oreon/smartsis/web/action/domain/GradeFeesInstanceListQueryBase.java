package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.GradeFeesInstance;

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

import com.oreon.smartsis.domain.GradeFeesInstance;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class GradeFeesInstanceListQueryBase
		extends
			BaseQuery<GradeFeesInstance, Long> {

	private static final String EJBQL = "select gradeFeesInstance from GradeFeesInstance gradeFeesInstance";

	protected GradeFeesInstance gradeFeesInstance = new GradeFeesInstance();

	public GradeFeesInstance getGradeFeesInstance() {
		return gradeFeesInstance;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<GradeFeesInstance> getEntityClass() {
		return GradeFeesInstance.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"gradeFeesInstance.id = #{gradeFeesInstanceList.gradeFeesInstance.id}",

			"gradeFeesInstance.grade.id = #{gradeFeesInstanceList.gradeFeesInstance.grade.id}",

			"lower(gradeFeesInstance.period) like concat(lower(#{gradeFeesInstanceList.gradeFeesInstance.period}),'%')",

			"gradeFeesInstance.dateCreated <= #{gradeFeesInstanceList.dateCreatedRange.end}",
			"gradeFeesInstance.dateCreated >= #{gradeFeesInstanceList.dateCreatedRange.begin}",};

	@Observer("archivedGradeFeesInstance")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, GradeFeesInstance e) {

		builder.append("\""
				+ (e.getGrade() != null ? e.getGrade().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getPeriod() != null ? e.getPeriod().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Grade" + ",");

		builder.append("Period" + ",");

		builder.append("\r\n");
	}
}
