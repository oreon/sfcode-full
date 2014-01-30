package com.oreon.phonestore.web.action.users;

import com.oreon.phonestore.users.User;

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
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class UserActionBase extends BaseAction<User>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long userId;

	public void setUserId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		instance = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setUserIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getUserId() {
		return (Long) getId();
	}

	public User getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(User t) {
		this.instance = t;
		loadAssociations();
	}

	public User getUser() {
		return (User) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('user', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('user', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected User createInstance() {
		User instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}

	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public User getDefinedInstance() {
		return (User) (isIdDefined() ? getInstance() : null);
	}

	public void setUser(User t) {
		this.instance = t;
		if (getInstance() != null)
			setUserId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	public com.oreon.phonestore.users.User findByUnqUserName(String userName) {
		return executeSingleResultNamedQuery("user.findByUnqUserName", userName);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListRoles();
		initListAvailableRoles();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.phonestore.users.Role> listRoles = new ArrayList<com.oreon.phonestore.users.Role>();

	void initListRoles() {

		if (listRoles.isEmpty())
			listRoles.addAll(getInstance().getRoles());

	}

	public List<com.oreon.phonestore.users.Role> getListRoles() {

		prePopulateListRoles();
		return listRoles;
	}

	public void prePopulateListRoles() {
	}

	public void setListRoles(List<com.oreon.phonestore.users.Role> listRoles) {
		this.listRoles = listRoles;
	}

	protected DualListModel<com.oreon.phonestore.users.Role> listAvailableRoles;

	void initListAvailableRoles() {

		List<com.oreon.phonestore.users.Role> availableroles = ((com.oreon.phonestore.web.action.users.RoleListQuery) Component
				.getInstance("roleList")).getAll();

		List<com.oreon.phonestore.users.Role> currentroles = getInstance()
				.getListRoles();

		if (availableroles == null)
			availableroles = new ArrayList<com.oreon.phonestore.users.Role>();

		if (getEntity() != null)
			availableroles.removeAll(getEntity().getRoles());

		listAvailableRoles = new DualListModel<com.oreon.phonestore.users.Role>(
				availableroles, currentroles);
	}

	public DualListModel<com.oreon.phonestore.users.Role> getListAvailableRoles() {
		return listAvailableRoles;
	}

	public void setListAvailableRoles(
			DualListModel<com.oreon.phonestore.users.Role> listAvailableRoles) {
		this.listAvailableRoles = listAvailableRoles;
	}

	public void updateComposedAssociations() {

		if (listRoles != null) {
			getInstance().getRoles().clear();
			getInstance().getRoles().addAll(listRoles);
		}
	}

	public void clearLists() {

		listRoles.clear();

	}

	public String viewUser() {
		load(currentEntityId);
		return "viewUser";
	}

}
