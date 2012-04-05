package com.pcas.datapkg.web.action.managedsecurity;

import com.pcas.datapkg.managedsecurity.RoleFieldPrivilege;

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

import com.pcas.datapkg.managedsecurity.RoleFieldPrivilege;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RoleFieldPrivilegeListQueryBase
		extends
			BaseQuery<RoleFieldPrivilege, Long> {

	private static final String EJBQL = "select roleFieldPrivilege from RoleFieldPrivilege roleFieldPrivilege";

	protected RoleFieldPrivilege roleFieldPrivilege = new RoleFieldPrivilege();

	public RoleFieldPrivilege getRoleFieldPrivilege() {
		return roleFieldPrivilege;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<RoleFieldPrivilege> getEntityClass() {
		return RoleFieldPrivilege.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"roleFieldPrivilege.id = #{roleFieldPrivilegeList.roleFieldPrivilege.id}",

			"roleFieldPrivilege.readAccess = #{roleFieldPrivilegeList.roleFieldPrivilege.readAccess}",

			"roleFieldPrivilege.writeAccess = #{roleFieldPrivilegeList.roleFieldPrivilege.writeAccess}",

			"roleFieldPrivilege.appRole.id = #{roleFieldPrivilegeList.roleFieldPrivilege.appRole.id}",

			"roleFieldPrivilege.metaField.id = #{roleFieldPrivilegeList.roleFieldPrivilege.metaField.id}",

			"roleFieldPrivilege.dateCreated <= #{roleFieldPrivilegeList.dateCreatedRange.end}",
			"roleFieldPrivilege.dateCreated >= #{roleFieldPrivilegeList.dateCreatedRange.begin}",};

	public List<RoleFieldPrivilege> getRoleFieldPrivilegesByMetaField(
			com.pcas.datapkg.customReports.MetaField metaField) {
		//setMaxResults(10000);
		roleFieldPrivilege.setMetaField(metaField);
		return getResultList();
	}

	@Observer("archivedRoleFieldPrivilege")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, RoleFieldPrivilege e) {

		builder.append("\""
				+ (e.getReadAccess() != null ? e.getReadAccess() : "") + "\",");

		builder.append("\""
				+ (e.getWriteAccess() != null ? e.getWriteAccess() : "")
				+ "\",");

		builder.append("\""
				+ (e.getAppRole() != null ? e.getAppRole().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getMetaField() != null ? e.getMetaField().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("ReadAccess" + ",");

		builder.append("WriteAccess" + ",");

		builder.append("AppRole" + ",");

		builder.append("MetaField" + ",");

		builder.append("\r\n");
	}
}
