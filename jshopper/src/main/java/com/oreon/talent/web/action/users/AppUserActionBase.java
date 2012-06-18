package com.oreon.talent.web.action.users;

import com.oreon.talent.users.AppUser;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DualListModel;

import org.witchcraft.utils.ViewUtils;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public abstract class AppUserActionBase extends BaseAction<AppUser>
		implements
			java.io.Serializable {

	@Inject
	com.oreon.talent.web.action.users.AppRoleAction appRoleAction;

	protected Predicate[] getSearchPredicates(Root<AppUser> root) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		/*
		String name = search.getName();
		if (name != null && !"".equals(name)) {
			predicatesList.add(builder.like(root.<String> get("name"),
					'%' + name + '%'));
		}
		
		int stock = search.getStock();
		if (stock != 0) {
			predicatesList.add(builder.equal(root.get("stock"), stock));
		}*/

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	@Override
	protected Class<AppUser> getEntityClass() {
		return AppUser.class;
	}

	public AppUser createInstance() {
		return new AppUser();
	}

	public AppUser getAppUser() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setAppUser(AppUser appUser) {
		this.entity = appUser;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListAppRoles();

	}

	public void updateAssociations() {
	}

	protected DualListModel<com.oreon.talent.users.AppRole> listAppRoles = new DualListModel<com.oreon.talent.users.AppRole>();

	void initListAppRoles() {

		List<com.oreon.talent.users.AppRole> availableappRoles = appRoleAction
				.getAll();
		List<com.oreon.talent.users.AppRole> currentappRoles = getEntity() != null
				? ViewUtils.asList(getEntity().getAppRoles())
				: new ArrayList<com.oreon.talent.users.AppRole>();

		if (availableappRoles == null)
			availableappRoles = new ArrayList<com.oreon.talent.users.AppRole>();
		if (getEntity() != null)
			availableappRoles.removeAll(getEntity().getAppRoles());

		listAppRoles = new DualListModel<com.oreon.talent.users.AppRole>(
				availableappRoles, currentappRoles);
	}

	public DualListModel<com.oreon.talent.users.AppRole> getListAppRoles() {
		return listAppRoles;
	}

	public void setListAppRoles(
			DualListModel<com.oreon.talent.users.AppRole> listAppRoles) {
		this.listAppRoles = listAppRoles;
	}

	public void prePopulateListAppRoles() {
	}

	public void updateComposedAssociations() {

		if (listAppRoles != null) {
			getEntity().getAppRoles().clear();
			getEntity().getAppRoles().addAll(listAppRoles.getTarget());
		}
	}

	public void clearLists() {

	}

	public com.oreon.talent.users.AppUser findByUnqUserName(String userName) {
		return executeSingleResultNamedQuery("appUser.findByUnqUserName",
				userName);
	}

}
