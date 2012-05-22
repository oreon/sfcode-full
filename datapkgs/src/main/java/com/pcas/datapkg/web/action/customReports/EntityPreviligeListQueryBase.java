package com.pcas.datapkg.web.action.customReports;

import com.pcas.datapkg.customReports.EntityPrevilige;

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

import com.pcas.datapkg.customReports.EntityPrevilige;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class EntityPreviligeListQueryBase
		extends
			BaseQuery<EntityPrevilige, Long> {

	private static final String EJBQL = "select entityPrevilige from EntityPrevilige entityPrevilige";

	protected EntityPrevilige entityPrevilige = new EntityPrevilige();

	public EntityPrevilige getEntityPrevilige() {
		return entityPrevilige;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<EntityPrevilige> getEntityClass() {
		return EntityPrevilige.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"entityPrevilige.id = #{entityPreviligeList.entityPrevilige.id}",

			"entityPrevilige.metaEntity.id = #{entityPreviligeList.entityPrevilige.metaEntity.id}",

			"entityPrevilige.dateCreated <= #{entityPreviligeList.dateCreatedRange.end}",
			"entityPrevilige.dateCreated >= #{entityPreviligeList.dateCreatedRange.begin}",};

	@Observer("archivedEntityPrevilige")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, EntityPrevilige e) {

		builder.append("\""
				+ (e.getMetaEntity() != null ? e.getMetaEntity()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("MetaEntity" + ",");

		builder.append("\r\n");
	}
}
