package org.cerebrum.domain.users.action;

import org.cerebrum.domain.users.User;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
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

import org.cerebrum.domain.users.Role;

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
	public void findRecords() {
		userList = entityManager.createQuery(
				"select user from User user order by user.id desc")
				.getResultList();
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

	private List<Role> listRoles;

	void initListRoles() {
		listRoles = new ArrayList<Role>();
		if (user.getRoles().isEmpty()) {

		} else
			listRoles.addAll(user.getRoles());
	}

	public List<Role> getListRoles() {
		if (listRoles == null) {
			initListRoles();
		}
		return listRoles;
	}

	public void setListRoles(List<Role> listRoles) {
		this.listRoles = listRoles;
	}

	public void deleteRoles(Role roles) {
		listRoles.remove(roles);
	}

	@Begin(join = true)
	public void addRoles() {
		Role roles = new Role();

		listRoles.add(roles);
	}

	public void updateComposedAssociations() {

		user.getRoles().clear();
		user.getRoles().addAll(listRoles);

	}

}
