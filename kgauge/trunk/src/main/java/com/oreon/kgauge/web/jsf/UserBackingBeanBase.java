package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.kgauge.domain.User;
import com.oreon.kgauge.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - User class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class UserBackingBeanBase extends BaseBackingBean<User> {

	protected User user = new User();

	protected UserService userService;

	private String repeatPassword;

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatpassword) {
		this.repeatPassword = repeatpassword;
	}

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return this.userService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<User> getBaseService() {
		return userService;
	}

	public User getEntity() {
		return getUser();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		user = new User();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			user = userService.load(id);

		repeatPassword = user.getPassword();

	}

	@Override
	public String update() {

		return super.update();
	}

}
