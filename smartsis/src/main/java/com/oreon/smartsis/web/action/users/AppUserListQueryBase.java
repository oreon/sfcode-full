package com.oreon.smartsis.web.action.users;

import com.oreon.smartsis.users.AppUser;

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

import com.oreon.smartsis.users.AppUser;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppUserListQueryBase extends BaseQuery<AppUser, Long> {

	private static final String EJBQL = "select appUser from AppUser appUser";

	protected AppUser appUser = new AppUser();

	public AppUser getAppUser() {
		return appUser;
	}

	private com.oreon.smartsis.users.AppRole appRolesToSearch;

	public void setAppRolesToSearch(
			com.oreon.smartsis.users.AppRole appRoleToSearch) {
		this.appRolesToSearch = appRoleToSearch;
	}

	public com.oreon.smartsis.users.AppRole getAppRolesToSearch() {
		return appRolesToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<AppUser> getEntityClass() {
		return AppUser.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"appUser.id = #{appUserList.appUser.id}",

			"lower(appUser.userName) like concat(lower(#{appUserList.appUser.userName}),'%')",

			"#{appUserList.appRolesToSearch} in elements(appUser.appRoles)",

			"lower(appUser.email) like concat(lower(#{appUserList.appUser.email}),'%')",

			"appUser.enabled = #{appUserList.appUser.enabled}",

			"appUser.dateCreated <= #{appUserList.dateCreatedRange.end}",
			"appUser.dateCreated >= #{appUserList.dateCreatedRange.begin}",};

	@Observer("archivedAppUser")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, AppUser e) {

		builder.append("\""
				+ (e.getUserName() != null
						? e.getUserName().replace(",", "")
						: "") + "\",");

		builder.append("\"" + (e.getAppRoles() != null ? e.getAppRoles() : "")
				+ "\",");

		builder.append("\""
				+ (e.getEmail() != null ? e.getEmail().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getEnabled() != null ? e.getEnabled() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("UserName" + ",");

		builder.append("AppRoles" + ",");

		builder.append("Email" + ",");

		builder.append("Enabled" + ",");

		builder.append("\r\n");
	}
}
