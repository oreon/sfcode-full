package com.nas.recovery.web.action.domain;

import org.wc.trackrite.domain.EndUser;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.domain.EndUser;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class EndUserListQueryBase extends BaseQuery<EndUser, Long> {

	//private static final String EJBQL = "select endUser from EndUser endUser";

	protected EndUser endUser = new EndUser();

	private Range<Date> user_lastLoginRange = new Range<Date>();
	public Range<Date> getUser_lastLoginRange() {
		return user_lastLoginRange;
	}
	public void setUser_lastLogin(Range<Date> user_lastLoginRange) {
		this.user_lastLoginRange = user_lastLoginRange;
	}

	private static final String[] RESTRICTIONS = {
			"endUser.id = #{endUserList.endUser.id}",

			"lower(endUser.firstName) like concat(lower(#{endUserList.endUser.firstName}),'%')",

			"lower(endUser.lastName) like concat(lower(#{endUserList.endUser.lastName}),'%')",

			"lower(endUser.user.userName) like concat(lower(#{endUserList.endUser.user.userName}),'%')",

			"endUser.user.enabled = #{endUserList.endUser.user.enabled}",

			"lower(endUser.user.email) like concat(lower(#{endUserList.endUser.user.email}),'%')",

			"endUser.user.lastLogin >= #{endUserList.user_lastLoginRange.begin}",
			"endUser.user.lastLogin <= #{endUserList.user_lastLoginRange.end}",

			"endUser.dateCreated <= #{endUserList.dateCreatedRange.end}",
			"endUser.dateCreated >= #{endUserList.dateCreatedRange.begin}",};

	public EndUser getEndUser() {
		return endUser;
	}

	@Override
	public Class<EndUser> getEntityClass() {
		return EndUser.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedEndUser")
	public void onArchive() {
		refresh();
	}
}
