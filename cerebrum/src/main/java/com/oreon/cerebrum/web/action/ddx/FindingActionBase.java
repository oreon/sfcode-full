package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.Finding;

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

import com.oreon.cerebrum.ddx.DifferentialDx;

public abstract class FindingActionBase extends BaseAction<Finding>
		implements
			java.io.Serializable {

	public void setFindingId(Long id) {
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
	public void setFindingIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getFindingId() {
		return (Long) getId();
	}

	public Finding getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Finding t) {
		this.instance = t;
		loadAssociations();
	}

	public Finding getFinding() {
		return (Finding) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('finding', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('finding', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Finding createInstance() {
		Finding instance = super.createInstance();

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

	public Finding getDefinedInstance() {
		return (Finding) (isIdDefined() ? getInstance() : null);
	}

	public void setFinding(Finding t) {
		this.instance = t;
		if (getInstance() != null)
			setFindingId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Finding> getEntityClass() {
		return Finding.class;
	}

	public com.oreon.cerebrum.ddx.Finding findByUnqName(String name) {
		return executeSingleResultNamedQuery("finding.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListDifferentialDxs();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.ddx.DifferentialDx> listDifferentialDxs = new ArrayList<com.oreon.cerebrum.ddx.DifferentialDx>();

	void initListDifferentialDxs() {

		if (listDifferentialDxs.isEmpty())
			listDifferentialDxs.addAll(getInstance().getDifferentialDxs());

	}

	public List<com.oreon.cerebrum.ddx.DifferentialDx> getListDifferentialDxs() {

		prePopulateListDifferentialDxs();
		return listDifferentialDxs;
	}

	public void prePopulateListDifferentialDxs() {
	}

	public void setListDifferentialDxs(
			List<com.oreon.cerebrum.ddx.DifferentialDx> listDifferentialDxs) {
		this.listDifferentialDxs = listDifferentialDxs;
	}

	public void deleteDifferentialDxs(int index) {
		listDifferentialDxs.remove(index);
	}

	@Begin(join = true)
	public void addDifferentialDxs() {

		initListDifferentialDxs();
		DifferentialDx differentialDxs = new DifferentialDx();

		differentialDxs.setFinding(getInstance());

		getListDifferentialDxs().add(differentialDxs);

	}

	public void updateComposedAssociations() {

		if (listDifferentialDxs != null) {
			getInstance().getDifferentialDxs().clear();
			getInstance().getDifferentialDxs().addAll(listDifferentialDxs);
		}
	}

	public void clearLists() {
		listDifferentialDxs.clear();

	}

	public String viewFinding() {
		load(currentEntityId);
		return "viewFinding";
	}

}
