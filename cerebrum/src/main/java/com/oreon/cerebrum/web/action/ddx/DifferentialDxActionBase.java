package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.DifferentialDx;

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

public abstract class DifferentialDxActionBase
		extends
			BaseAction<DifferentialDx> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	private DifferentialDx differentialDx;

	@In(create = true, value = "dxCategoryAction")
	com.oreon.cerebrum.web.action.ddx.DxCategoryAction dxCategoryAction;

	@In(create = true, value = "findingAction")
	com.oreon.cerebrum.web.action.ddx.FindingAction findingAction;

	public void setDifferentialDxId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		differentialDx = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDifferentialDxIdForModalDlg(Long id) {
		setId(id);
		differentialDx = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setDxCategoryId(Long id) {

		if (id != null && id > 0)
			getInstance().setDxCategory(dxCategoryAction.loadFromId(id));

	}

	public Long getDxCategoryId() {
		if (getInstance().getDxCategory() != null)
			return getInstance().getDxCategory().getId();
		return 0L;
	}

	public void setFindingId(Long id) {

		if (id != null && id > 0)
			getInstance().setFinding(findingAction.loadFromId(id));

	}

	public Long getFindingId() {
		if (getInstance().getFinding() != null)
			return getInstance().getFinding().getId();
		return 0L;
	}

	public Long getDifferentialDxId() {
		return (Long) getId();
	}

	public DifferentialDx getEntity() {
		return differentialDx;
	}

	//@Override
	public void setEntity(DifferentialDx t) {
		this.differentialDx = t;
		loadAssociations();
	}

	public DifferentialDx getDifferentialDx() {
		return (DifferentialDx) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('differentialDx', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('differentialDx', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected DifferentialDx createInstance() {
		DifferentialDx instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.ddx.DxCategory dxCategory = dxCategoryAction
				.getDefinedInstance();
		if (dxCategory != null && isNew()) {
			getInstance().setDxCategory(dxCategory);
		}

		com.oreon.cerebrum.ddx.Finding finding = findingAction
				.getDefinedInstance();
		if (finding != null && isNew()) {
			getInstance().setFinding(finding);
		}

	}

	public boolean isWired() {
		return true;
	}

	public DifferentialDx getDefinedInstance() {
		return (DifferentialDx) (isIdDefined() ? getInstance() : null);
	}

	public void setDifferentialDx(DifferentialDx t) {
		this.differentialDx = t;
		if (differentialDx != null)
			setDifferentialDxId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<DifferentialDx> getEntityClass() {
		return DifferentialDx.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (differentialDx.getDxCategory() != null) {
			criteria = criteria.add(Restrictions.eq("dxCategory.id",
					differentialDx.getDxCategory().getId()));
		}

		if (differentialDx.getFinding() != null) {
			criteria = criteria.add(Restrictions.eq("finding.id",
					differentialDx.getFinding().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (differentialDx.getDxCategory() != null) {
			dxCategoryAction.setInstance(getInstance().getDxCategory());
			dxCategoryAction.loadAssociations();
		}

		if (differentialDx.getFinding() != null) {
			findingAction.setInstance(getInstance().getFinding());
			findingAction.loadAssociations();
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewDifferentialDx() {
		load(currentEntityId);
		return "viewDifferentialDx";
	}

}
