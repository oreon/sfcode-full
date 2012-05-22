package com.pcas.datapkg.web.action.customReports;

import com.pcas.datapkg.customReports.FieldPrevilige;

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

import com.pcas.datapkg.customReports.FieldPrevilige;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FieldPreviligeListQueryBase
		extends
			BaseQuery<FieldPrevilige, Long> {

	private static final String EJBQL = "select fieldPrevilige from FieldPrevilige fieldPrevilige";

	protected FieldPrevilige fieldPrevilige = new FieldPrevilige();

	public FieldPrevilige getFieldPrevilige() {
		return fieldPrevilige;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FieldPrevilige> getEntityClass() {
		return FieldPrevilige.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"fieldPrevilige.id = #{fieldPreviligeList.fieldPrevilige.id}",

			"fieldPrevilige.metaField.id = #{fieldPreviligeList.fieldPrevilige.metaField.id}",

			"fieldPrevilige.entityPrevilige.id = #{fieldPreviligeList.fieldPrevilige.entityPrevilige.id}",

			"fieldPrevilige.dateCreated <= #{fieldPreviligeList.dateCreatedRange.end}",
			"fieldPrevilige.dateCreated >= #{fieldPreviligeList.dateCreatedRange.begin}",};

	public List<FieldPrevilige> getFieldPreviligesByEntityPrevilige(
			com.pcas.datapkg.customReports.EntityPrevilige entityPrevilige) {
		//setMaxResults(10000);
		fieldPrevilige.setEntityPrevilige(entityPrevilige);
		return getResultList();
	}

	@Observer("archivedFieldPrevilige")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FieldPrevilige e) {

		builder.append("\""
				+ (e.getMetaField() != null ? e.getMetaField().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getEntityPrevilige() != null ? e.getEntityPrevilige()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("MetaField" + ",");

		builder.append("EntityPrevilige" + ",");

		builder.append("\r\n");
	}
}
