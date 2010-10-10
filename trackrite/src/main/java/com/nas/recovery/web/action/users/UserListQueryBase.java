package com.nas.recovery.web.action.users;

import org.wc.trackrite.users.User;

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

import org.wc.trackrite.users.User;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class UserListQueryBase extends BaseQuery<User, Long> {

	//private static final String EJBQL = "select user from User user";

	protected User user = new User();

	private static final String[] RESTRICTIONS = {
			"user.id = #{userList.user.id}",

			"lower(user.userName) like concat(lower(#{userList.user.userName}),'%')",

			"user.enabled = #{userList.user.enabled}",

			"lower(user.email) like concat(lower(#{userList.user.email}),'%')",

			"user.dateCreated <= #{userList.dateCreatedRange.end}",
			"user.dateCreated >= #{userList.dateCreatedRange.begin}",};

	public User getUser() {
		return user;
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedUser")
	public void onArchive() {
		refresh();
	}
}
