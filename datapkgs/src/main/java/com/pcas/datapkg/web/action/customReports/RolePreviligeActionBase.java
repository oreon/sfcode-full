package com.pcas.datapkg.web.action.customReports;

import com.pcas.datapkg.customReports.RolePrevilige;

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

public abstract class RolePreviligeActionBase extends BaseAction<RolePrevilige>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RolePrevilige rolePrevilige;

	@In(create = true, value = "appRoleAction")
	com.pcas.datapkg.web.action.users.AppRoleAction appRoleAction;

	@In(create = true, value = "fieldPreviligeAction")
	com.pcas.datapkg.web.action.customReports.FieldPreviligeAction fieldPreviligeAction;

	@DataModel
	private List<RolePrevilige> rolePreviligeRecordList;

	public void setRolePreviligeId(Long id) {
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
	public void setRolePreviligeIdForModalDlg(Long id) {
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

	public void setFieldPreviligeId(Long id) {

		if (id != null && id > 0)
			getInstance()
					.setFieldPrevilige(fieldPreviligeAction.loadFromId(id));

	}

	public Long getFieldPreviligeId() {
		if (getInstance().getFieldPrevilige() != null)
			return getInstance().getFieldPrevilige().getId();
		return 0L;
	}

	public Long getRolePreviligeId() {
		return (Long) getId();
	}

	public RolePrevilige getEntity() {
		return rolePrevilige;
	}

	//@Override
	public void setEntity(RolePrevilige t) {
		this.rolePrevilige = t;
		loadAssociations();
	}

	public RolePrevilige getRolePrevilige() {
		return (RolePrevilige) getInstance();
	}

	@Override
	protected RolePrevilige createInstance() {
		RolePrevilige instance = super.createInstance();

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

		com.pcas.datapkg.customReports.FieldPrevilige fieldPrevilige = fieldPreviligeAction
				.getDefinedInstance();
		if (fieldPrevilige != null && isNew()) {
			getInstance().setFieldPrevilige(fieldPrevilige);
		}

	}

	public boolean isWired() {
		return true;
	}

	public RolePrevilige getDefinedInstance() {
		return (RolePrevilige) (isIdDefined() ? getInstance() : null);
	}

	public void setRolePrevilige(RolePrevilige t) {
		this.rolePrevilige = t;
		if (rolePrevilige != null)
			setRolePreviligeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<RolePrevilige> getEntityClass() {
		return RolePrevilige.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (rolePrevilige.getAppRole() != null) {
			criteria = criteria.add(Restrictions.eq("appRole.id", rolePrevilige
					.getAppRole().getId()));
		}

		if (rolePrevilige.getFieldPrevilige() != null) {
			criteria = criteria.add(Restrictions.eq("fieldPrevilige.id",
					rolePrevilige.getFieldPrevilige().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (rolePrevilige.getAppRole() != null) {
			appRoleAction.setInstance(getInstance().getAppRole());
		}

		if (rolePrevilige.getFieldPrevilige() != null) {
			fieldPreviligeAction.setInstance(getInstance().getFieldPrevilige());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewRolePrevilige() {
		load(currentEntityId);
		return "viewRolePrevilige";
	}

}
