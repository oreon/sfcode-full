package com.oreon.cerebrum.web.action.users;

import com.oreon.cerebrum.users.AppUser;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class AppUserActionBase extends BaseAction<AppUser>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private AppUser appUser;

	public void setAppUserId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		appUser = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAppUserIdForModalDlg(Long id) {
		setId(id);
		appUser = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getAppUserId() {
		return (Long) getId();
	}

	public AppUser getEntity() {
		return appUser;
	}

	//@Override
	public void setEntity(AppUser t) {
		this.appUser = t;
		loadAssociations();
	}

	public AppUser getAppUser() {
		return (AppUser) getInstance();
	}

	@Override
	protected AppUser createInstance() {
		AppUser instance = super.createInstance();

		return instance;
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

	public AppUser getDefinedInstance() {
		return (AppUser) (isIdDefined() ? getInstance() : null);
	}

	public void setAppUser(AppUser t) {
		this.appUser = t;
		if (appUser != null)
			setAppUserId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<AppUser> getEntityClass() {
		return AppUser.class;
	}

	public com.oreon.cerebrum.users.AppUser findByUnqUserName(String userName) {
		return executeSingleResultNamedQuery("appUser.findByUnqUserName",
				userName);
	}

	public com.oreon.cerebrum.users.AppUser findByUnqEmail(String email) {
		return executeSingleResultNamedQuery("appUser.findByUnqEmail", email);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListAppRoles();
		initListAvailableAppRoles();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.users.AppRole> listAppRoles = new ArrayList<com.oreon.cerebrum.users.AppRole>();

	void initListAppRoles() {

		if (listAppRoles.isEmpty())
			listAppRoles.addAll(getInstance().getAppRoles());

	}

	public List<com.oreon.cerebrum.users.AppRole> getListAppRoles() {

		prePopulateListAppRoles();
		return listAppRoles;
	}

	public void prePopulateListAppRoles() {
	}

	public void setListAppRoles(
			List<com.oreon.cerebrum.users.AppRole> listAppRoles) {
		this.listAppRoles = listAppRoles;
	}

	protected List<com.oreon.cerebrum.users.AppRole> listAvailableAppRoles = new ArrayList<com.oreon.cerebrum.users.AppRole>();

	void initListAvailableAppRoles() {

		listAvailableAppRoles = getEntityManager().createQuery(
				"select r from AppRole r").getResultList();
		listAvailableAppRoles.removeAll(getInstance().getAppRoles());

	}

	@Begin(join = true)
	public List<com.oreon.cerebrum.users.AppRole> getListAvailableAppRoles() {

		prePopulateListAvailableAppRoles();
		return listAvailableAppRoles;
	}

	public void prePopulateListAvailableAppRoles() {
	}

	public void setListAvailableAppRoles(
			List<com.oreon.cerebrum.users.AppRole> listAvailableAppRoles) {
		this.listAvailableAppRoles = listAvailableAppRoles;
	}

	public void updateComposedAssociations() {

		if (listAppRoles != null) {
			getInstance().getAppRoles().clear();
			getInstance().getAppRoles().addAll(listAppRoles);
		}
	}

	public void clearLists() {

		listAppRoles.clear();

	}

	public String viewAppUser() {
		load(currentEntityId);
		return "viewAppUser";
	}

}
