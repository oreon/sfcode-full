package org.witchcraft.users.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.users.User;
import org.jboss.seam.annotations.Observer;

@Scope(ScopeType.CONVERSATION)
@Name("userAction")
public class UserAction extends BaseAction<User>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private User user;

	@DataModel
	private List<User> userList;

	@Factory("userList")
	@Observer("archivedUser")
	public void findRecords() {
		search();
	}

	public User getEntity() {
		return user;
	}

	@Override
	public void setEntity(User t) {
		this.user = t;
	}

	@Override
	public void setEntityList(List<User> list) {
		this.userList = list;
	}

	public void updateAssociations() {

	}

	public List<User> getEntityList() {
		if (userList == null) {
			findRecords();
		}
		return userList;
	}

}
