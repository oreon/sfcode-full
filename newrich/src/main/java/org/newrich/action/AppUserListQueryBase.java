package org.newrich.action;

import java.util.Date;

import org.docs.richfaces.AppRole;
import org.docs.richfaces.AppUser;
import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

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

	private AppRole appRolesToSearch;

	public void setAppRolesToSearch(
			AppRole appRoleToSearch) {
		this.appRolesToSearch = appRoleToSearch;
	}

	public AppRole getAppRolesToSearch() {
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

	private Range<Date> lastLoginRange = new Range<Date>();

	public Range<Date> getLastLoginRange() {
		return lastLoginRange;
	}
	public void setLastLogin(Range<Date> lastLoginRange) {
		this.lastLoginRange = lastLoginRange;
	}

	private static final String[] RESTRICTIONS = {
			"appUser.id = #{appUserList.appUser.id}",

			"lower(appUser.userName) like concat(lower(#{appUserList.appUser.userName}),'%')",

			"appUser.enabled = #{appUserList.appUser.enabled}",

			"#{appUserList.appRolesToSearch} in elements(appUser.appRoles)",

			"lower(appUser.email) like concat(lower(#{appUserList.appUser.email}),'%')",

			"appUser.lastLogin >= #{appUserList.lastLoginRange.begin}",
			"appUser.lastLogin <= #{appUserList.lastLoginRange.end}",

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

		builder.append("\"" + (e.getEnabled() != null ? e.getEnabled() : "")
				+ "\",");

		builder.append("\"" + (e.getAppRoles() != null ? e.getAppRoles() : "")
				+ "\",");

		builder.append("\""
				+ (e.getEmail() != null ? e.getEmail().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getLastLogin() != null ? e.getLastLogin() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("UserName" + ",");

		builder.append("Enabled" + ",");

		builder.append("AppRoles" + ",");

		builder.append("Email" + ",");

		builder.append("LastLogin" + ",");

		builder.append("\r\n");
	}
}
