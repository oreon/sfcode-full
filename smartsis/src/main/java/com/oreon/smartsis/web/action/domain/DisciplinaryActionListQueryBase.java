package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.DisciplinaryAction;

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

import com.oreon.smartsis.domain.DisciplinaryAction;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DisciplinaryActionListQueryBase
		extends
			BaseQuery<DisciplinaryAction, Long> {

	private static final String EJBQL = "select disciplinaryAction from DisciplinaryAction disciplinaryAction";

	protected DisciplinaryAction disciplinaryAction = new DisciplinaryAction();

	public DisciplinaryAction getDisciplinaryAction() {
		return disciplinaryAction;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DisciplinaryAction> getEntityClass() {
		return DisciplinaryAction.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"disciplinaryAction.id = #{disciplinaryActionList.disciplinaryAction.id}",

			"disciplinaryAction.student.id = #{disciplinaryActionList.disciplinaryAction.student.id}",

			"lower(disciplinaryAction.title) like concat(lower(#{disciplinaryActionList.disciplinaryAction.title}),'%')",

			"lower(disciplinaryAction.description) like concat(lower(#{disciplinaryActionList.disciplinaryAction.description}),'%')",

			"disciplinaryAction.employee.id = #{disciplinaryActionList.disciplinaryAction.employee.id}",

			"disciplinaryAction.dateCreated <= #{disciplinaryActionList.dateCreatedRange.end}",
			"disciplinaryAction.dateCreated >= #{disciplinaryActionList.dateCreatedRange.begin}",};

	@Observer("archivedDisciplinaryAction")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DisciplinaryAction e) {

		builder.append("\""
				+ (e.getStudent() != null ? e.getStudent().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getTitle() != null ? e.getTitle().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getEmployee() != null ? e.getEmployee().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Student" + ",");

		builder.append("Title" + ",");

		builder.append("Description" + ",");

		builder.append("Employee" + ",");

		builder.append("\r\n");
	}
}
