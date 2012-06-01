package com.pcas.datapkg.web.action.customReports;

import com.pcas.datapkg.customReports.EntityFieldPrivilege;

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

import com.pcas.datapkg.customReports.EntityFieldPrivilege;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class EntityFieldPrivilegeListQueryBase
		extends
			BaseQuery<EntityFieldPrivilege, Long> {

	private static final String EJBQL = "select entityFieldPrivilege from EntityFieldPrivilege entityFieldPrivilege";

	protected EntityFieldPrivilege entityFieldPrivilege = new EntityFieldPrivilege();

	public EntityFieldPrivilege getEntityFieldPrivilege() {
		return entityFieldPrivilege;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<EntityFieldPrivilege> getEntityClass() {
		return EntityFieldPrivilege.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"entityFieldPrivilege.id = #{entityFieldPrivilegeList.entityFieldPrivilege.id}",

			"entityFieldPrivilege.metaEntity.id = #{entityFieldPrivilegeList.entityFieldPrivilege.metaEntity.id}",

			"entityFieldPrivilege.appRole.id = #{entityFieldPrivilegeList.entityFieldPrivilege.appRole.id}",

			"entityFieldPrivilege.privilegeType = #{entityFieldPrivilegeList.entityFieldPrivilege.privilegeType}",

			"entityFieldPrivilege.dateCreated <= #{entityFieldPrivilegeList.dateCreatedRange.end}",
			"entityFieldPrivilege.dateCreated >= #{entityFieldPrivilegeList.dateCreatedRange.begin}",};

	public List<EntityFieldPrivilege> getEntityFieldPrivilegesByMetaEntity(
			com.pcas.datapkg.customReports.MetaEntity metaEntity) {
		//setMaxResults(10000);
		entityFieldPrivilege.setMetaEntity(metaEntity);
		return getResultList();
	}

	@Observer("archivedEntityFieldPrivilege")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, EntityFieldPrivilege e) {

		builder.append("\""
				+ (e.getMetaEntity() != null ? e.getMetaEntity()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getAppRole() != null ? e.getAppRole().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getPrivilegeType() != null ? e.getPrivilegeType() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("MetaEntity" + ",");

		builder.append("AppRole" + ",");

		builder.append("PrivilegeType" + ",");

		builder.append("\r\n");
	}
}
