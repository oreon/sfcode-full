package com.jonah.mentormatcher.web.action.domain;

import com.jonah.mentormatcher.domain.Designation;

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

import com.jonah.mentormatcher.domain.Designation;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DesignationListQueryBase
		extends
			BaseQuery<Designation, Long> {

	private static final String EJBQL = "select designation from Designation designation";

	protected Designation designation = new Designation();

	public Designation getDesignation() {
		return designation;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Designation> getEntityClass() {
		return Designation.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"designation.id = #{designationList.designation.id}",

			"lower(designation.name) like concat(lower(#{designationList.designation.name}),'%')",

			"designation.dateCreated <= #{designationList.dateCreatedRange.end}",
			"designation.dateCreated >= #{designationList.dateCreatedRange.begin}",};

	@Observer("archivedDesignation")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Designation e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

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
