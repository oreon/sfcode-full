package com.pcas.datapkg.web.action.managedsecurity;

import com.pcas.datapkg.managedsecurity.RoleFieldPrivilege;

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

public abstract class RoleFieldPrivilegeActionBase
		extends
			BaseAction<RoleFieldPrivilege> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RoleFieldPrivilege roleFieldPrivilege;

	@In(create = true, value = "appRoleAction")
	com.pcas.datapkg.web.action.users.AppRoleAction appRoleAction;

	@In(create = true, value = "metaFieldAction")
	com.pcas.datapkg.web.action.customReports.MetaFieldAction metaFieldAction;

	@DataModel
	private List<RoleFieldPrivilege> roleFieldPrivilegeRecordList;

	public void setRoleFieldPrivilegeId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setRoleFieldPrivilegeIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setAppRoleId(Long id) {

		if (id != null && id > 0)
			getInstance().setAppRole(appRoleAction.loadFromId(id));

	}

	public Long getAppRoleId() {
		if (getInstance().getAppRole() != null)
			return getInstance().getAppRole().getId();
		return 0L;
	}

	public void setMetaFieldId(Long id) {

		if (id != null && id > 0)
			getInstance().setMetaField(metaFieldAction.loadFromId(id));

	}

	public Long getMetaFieldId() {
		if (getInstance().getMetaField() != null)
			return getInstance().getMetaField().getId();
		return 0L;
	}

	public Long getRoleFieldPrivilegeId() {
		return (Long) getId();
	}

	public RoleFieldPrivilege getEntity() {
		return roleFieldPrivilege;
	}

	//@Override
	public void setEntity(RoleFieldPrivilege t) {
		this.roleFieldPrivilege = t;
		loadAssociations();
	}

	public RoleFieldPrivilege getRoleFieldPrivilege() {
		return (RoleFieldPrivilege) getInstance();
	}

	@Override
	protected RoleFieldPrivilege createInstance() {
		RoleFieldPrivilege instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pcas.datapkg.users.AppRole appRole = appRoleAction
				.getDefinedInstance();
		if (appRole != null && isNew()) {
			getInstance().setAppRole(appRole);
		}

		com.pcas.datapkg.customReports.MetaField metaField = metaFieldAction
				.getDefinedInstance();
		if (metaField != null && isNew()) {
			getInstance().setMetaField(metaField);
		}

	}

	public boolean isWired() {
		return true;
	}

	public RoleFieldPrivilege getDefinedInstance() {
		return (RoleFieldPrivilege) (isIdDefined() ? getInstance() : null);
	}

	public void setRoleFieldPrivilege(RoleFieldPrivilege t) {
		this.roleFieldPrivilege = t;
		if (roleFieldPrivilege != null)
			setRoleFieldPrivilegeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<RoleFieldPrivilege> getEntityClass() {
		return RoleFieldPrivilege.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (roleFieldPrivilege.getAppRole() != null) {
			criteria = criteria.add(Restrictions.eq("appRole.id",
					roleFieldPrivilege.getAppRole().getId()));
		}

		if (roleFieldPrivilege.getMetaField() != null) {
			criteria = criteria.add(Restrictions.eq("metaField.id",
					roleFieldPrivilege.getMetaField().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (roleFieldPrivilege.getAppRole() != null) {
			appRoleAction.setInstance(getInstance().getAppRole());
		}

		if (roleFieldPrivilege.getMetaField() != null) {
			metaFieldAction.setInstance(getInstance().getMetaField());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewRoleFieldPrivilege() {
		load(currentEntityId);
		return "viewRoleFieldPrivilege";
	}

}
