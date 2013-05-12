package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.DxCategory;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class DxCategoryActionBase extends BaseAction<DxCategory>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private DxCategory dxCategory;

	public void setDxCategoryId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		dxCategory = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDxCategoryIdForModalDlg(Long id) {
		setId(id);
		dxCategory = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getDxCategoryId() {
		return (Long) getId();
	}

	public DxCategory getEntity() {
		return dxCategory;
	}

	//@Override
	public void setEntity(DxCategory t) {
		this.dxCategory = t;
		loadAssociations();
	}

	public DxCategory getDxCategory() {
		return (DxCategory) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('dxCategory', 'edit'}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('dxCategory', 'delete'}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected DxCategory createInstance() {
		DxCategory instance = super.createInstance();

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

	public DxCategory getDefinedInstance() {
		return (DxCategory) (isIdDefined() ? getInstance() : null);
	}

	public void setDxCategory(DxCategory t) {
		this.dxCategory = t;
		if (dxCategory != null)
			setDxCategoryId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<DxCategory> getEntityClass() {
		return DxCategory.class;
	}

	public com.oreon.cerebrum.ddx.DxCategory findByUnqName(String name) {
		return executeSingleResultNamedQuery("dxCategory.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewDxCategory() {
		load(currentEntityId);
		return "viewDxCategory";
	}

}
