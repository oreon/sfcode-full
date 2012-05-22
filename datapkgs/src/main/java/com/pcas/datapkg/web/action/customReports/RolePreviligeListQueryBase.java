package com.pcas.datapkg.web.action.customReports;

import com.pcas.datapkg.customReports.RolePrevilige;

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

import com.pcas.datapkg.customReports.RolePrevilige;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RolePreviligeListQueryBase
		extends
			BaseQuery<RolePrevilige, Long> {

	private static final String EJBQL = "select rolePrevilige from RolePrevilige rolePrevilige";

	protected RolePrevilige rolePrevilige = new RolePrevilige();

	public RolePrevilige getRolePrevilige() {
		return rolePrevilige;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<RolePrevilige> getEntityClass() {
		return RolePrevilige.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"rolePrevilige.id = #{rolePreviligeList.rolePrevilige.id}",

			"rolePrevilige.edit = #{rolePreviligeList.rolePrevilige.edit}",

			"rolePrevilige.view = #{rolePreviligeList.rolePrevilige.view}",

			"rolePrevilige.appRole.id = #{rolePreviligeList.rolePrevilige.appRole.id}",

			"rolePrevilige.fieldPrevilige.id = #{rolePreviligeList.rolePrevilige.fieldPrevilige.id}",

			"rolePrevilige.dateCreated <= #{rolePreviligeList.dateCreatedRange.end}",
			"rolePrevilige.dateCreated >= #{rolePreviligeList.dateCreatedRange.begin}",};

	public List<RolePrevilige> getRolePreviligesByFieldPrevilige(
			com.pcas.datapkg.customReports.FieldPrevilige fieldPrevilige) {
		//setMaxResults(10000);
		rolePrevilige.setFieldPrevilige(fieldPrevilige);
		return getResultList();
	}

	@Observer("archivedRolePrevilige")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, RolePrevilige e) {

		builder.append("\"" + (e.getEdit() != null ? e.getEdit() : "") + "\",");

		builder.append("\"" + (e.getView() != null ? e.getView() : "") + "\",");

		builder.append("\""
				+ (e.getAppRole() != null ? e.getAppRole().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getFieldPrevilige() != null ? e.getFieldPrevilige()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Edit" + ",");

		builder.append("View" + ",");

		builder.append("AppRole" + ",");

		builder.append("FieldPrevilige" + ",");

		builder.append("\r\n");
	}
}
