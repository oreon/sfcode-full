package com.pcas.datapkg.web.action.customReports;

import com.pcas.datapkg.customReports.EntityFieldPrivilege;

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

public abstract class EntityFieldPrivilegeActionBase
		extends
			BaseAction<EntityFieldPrivilege> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private EntityFieldPrivilege entityFieldPrivilege;

	@In(create = true, value = "metaEntityAction")
	com.pcas.datapkg.web.action.customReports.MetaEntityAction metaEntityAction;

	@In(create = true, value = "appRoleAction")
	com.pcas.datapkg.web.action.users.AppRoleAction appRoleAction;

	@DataModel
	private List<EntityFieldPrivilege> entityFieldPrivilegeRecordList;

	public void setEntityFieldPrivilegeId(Long id) {
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
	public void setEntityFieldPrivilegeIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMetaEntityId(Long id) {

		if (id != null && id > 0)
			getInstance().setMetaEntity(metaEntityAction.loadFromId(id));

	}

	public Long getMetaEntityId() {
		if (getInstance().getMetaEntity() != null)
			return getInstance().getMetaEntity().getId();
		return 0L;
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

	public Long getEntityFieldPrivilegeId() {
		return (Long) getId();
	}

	public EntityFieldPrivilege getEntity() {
		return entityFieldPrivilege;
	}

	//@Override
	public void setEntity(EntityFieldPrivilege t) {
		this.entityFieldPrivilege = t;
		loadAssociations();
	}

	public EntityFieldPrivilege getEntityFieldPrivilege() {
		return (EntityFieldPrivilege) getInstance();
	}

	@Override
	protected EntityFieldPrivilege createInstance() {
		EntityFieldPrivilege instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pcas.datapkg.customReports.MetaEntity metaEntity = metaEntityAction
				.getDefinedInstance();
		if (metaEntity != null && isNew()) {
			getInstance().setMetaEntity(metaEntity);
		}

		com.pcas.datapkg.users.AppRole appRole = appRoleAction
				.getDefinedInstance();
		if (appRole != null && isNew()) {
			getInstance().setAppRole(appRole);
		}

	}

	public boolean isWired() {
		return true;
	}

	public EntityFieldPrivilege getDefinedInstance() {
		return (EntityFieldPrivilege) (isIdDefined() ? getInstance() : null);
	}

	public void setEntityFieldPrivilege(EntityFieldPrivilege t) {
		this.entityFieldPrivilege = t;
		if (entityFieldPrivilege != null)
			setEntityFieldPrivilegeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<EntityFieldPrivilege> getEntityClass() {
		return EntityFieldPrivilege.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (entityFieldPrivilege.getMetaEntity() != null) {
			criteria = criteria.add(Restrictions.eq("metaEntity.id",
					entityFieldPrivilege.getMetaEntity().getId()));
		}

		if (entityFieldPrivilege.getAppRole() != null) {
			criteria = criteria.add(Restrictions.eq("appRole.id",
					entityFieldPrivilege.getAppRole().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (entityFieldPrivilege.getMetaEntity() != null) {
			metaEntityAction.setInstance(getInstance().getMetaEntity());
		}

		if (entityFieldPrivilege.getAppRole() != null) {
			appRoleAction.setInstance(getInstance().getAppRole());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewEntityFieldPrivilege() {
		load(currentEntityId);
		return "viewEntityFieldPrivilege";
	}

}
