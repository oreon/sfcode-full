package org.witchcraft.users.action;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.users.AppRole;
import org.witchcraft.users.AppUser;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppRoleListQueryBase extends BaseQuery<AppRole, Long> {

	private static final String EJBQL = "select appRole from AppRole appRole";

	protected AppRole appRole = new AppRole();

	public AppRole getAppRole() {
		return appRole;
	}

	private AppUser appUsersToSearch;

	public void setAppUsersToSearch(
			AppUser appUserToSearch) {
		this.appUsersToSearch = appUserToSearch;
	}

	public AppUser getAppUsersToSearch() {
		return appUsersToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<AppRole> getEntityClass() {
		return AppRole.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"appRole.id = #{appRoleList.appRole.id}",

			"lower(appRole.name) like concat(lower(#{appRoleList.appRole.name}),'%')",

			"#{appRoleList.appUsersToSearch} in elements(appRole.appUsers)",

			"appRole.dateCreated <= #{appRoleList.dateCreatedRange.end}",
			"appRole.dateCreated >= #{appRoleList.dateCreatedRange.begin}",};

	@Observer("archivedAppRole")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, AppRole e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getAppUsers() != null ? e.getAppUsers() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("AppUsers" + ",");

		builder.append("\r\n");
	}
}
