package com.nas.recovery.web.action.users;

import org.wc.trackrite.users.User;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class UserActionBase extends BaseAction<User>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private User user;

	@DataModel
	private List<User> userRecordList;

	public void setUserId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setUserIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getUserId() {
		return (Long) getId();
	}

	//@Factory("userRecordList")
	//@Observer("archivedUser")
	public void findRecords() {
		//search();
	}

	public User getEntity() {
		return user;
	}

	@Override
	public void setEntity(User t) {
		this.user = t;
		loadAssociations();
	}

	public User getUser() {
		return getInstance();
	}

	@Override
	protected User createInstance() {
		return new User();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public User getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setUser(User t) {
		this.user = t;
		loadAssociations();
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public void setEntityList(List<User> list) {
		this.userRecordList = list;
	}

	public org.wc.trackrite.users.User findByUnqUserName(String userName) {
		return executeSingleResultNamedQuery("findByUnqUserName", userName);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	protected List<org.wc.trackrite.users.Role> listRoles;

	void initListRoles() {
		listRoles = new ArrayList<org.wc.trackrite.users.Role>();

		if (getInstance().getRoles().isEmpty()) {

		} else
			listRoles.addAll(getInstance().getRoles());

	}

	public List<org.wc.trackrite.users.Role> getListRoles() {
		if (listRoles == null)
			initListRoles();
		return listRoles;
	}

	public void setListRoles(List<org.wc.trackrite.users.Role> listRoles) {
		this.listRoles = listRoles;
	}

	protected List<org.wc.trackrite.users.Role> listAvailableRoles;

	void initListAvailableRoles() {
		listAvailableRoles = new ArrayList<org.wc.trackrite.users.Role>();

		listAvailableRoles = getEntityManager().createQuery(
				"select r from Role r").getResultList();
		listAvailableRoles.removeAll(getInstance().getRoles());

	}

	public List<org.wc.trackrite.users.Role> getListAvailableRoles() {
		if (listAvailableRoles == null)
			initListAvailableRoles();
		return listAvailableRoles;
	}

	public void setListAvailableRoles(
			List<org.wc.trackrite.users.Role> listAvailableRoles) {
		this.listAvailableRoles = listAvailableRoles;
	}

	public void updateComposedAssociations() {

		if (listRoles != null) {
			getInstance().getRoles().clear();
			getInstance().getRoles().addAll(listRoles);
		}
	}

	public List<User> getEntityList() {
		if (userRecordList == null) {
			findRecords();
		}
		return userRecordList;
	}

	public org.wc.trackrite.users.User findByUserName(String name) {

		return executeSingleResultNamedQuery("findByUserName", name);

	}

}
