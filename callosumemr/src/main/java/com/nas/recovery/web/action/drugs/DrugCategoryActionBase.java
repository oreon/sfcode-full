package com.nas.recovery.web.action.drugs;

import com.oreon.callosum.drugs.DrugCategory;

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

public abstract class DrugCategoryActionBase extends BaseAction<DrugCategory>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private DrugCategory drugCategory;

	@DataModel
	private List<DrugCategory> drugCategoryRecordList;

	public void setDrugCategoryId(Long id) {
		if (id == 0) {
			clearInstance();
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
	public void setDrugCategoryIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getDrugCategoryId() {
		return (Long) getId();
	}

	public DrugCategory getEntity() {
		return drugCategory;
	}

	//@Override
	public void setEntity(DrugCategory t) {
		this.drugCategory = t;
		loadAssociations();
	}

	public DrugCategory getDrugCategory() {
		return (DrugCategory) getInstance();
	}

	@Override
	protected DrugCategory createInstance() {
		return new DrugCategory();
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

	public DrugCategory getDefinedInstance() {
		return (DrugCategory) (isIdDefined() ? getInstance() : null);
	}

	public void setDrugCategory(DrugCategory t) {
		this.drugCategory = t;
		loadAssociations();
	}

	@Override
	public Class<DrugCategory> getEntityClass() {
		return DrugCategory.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.callosum.drugs.Drug> listDrugs;

	void initListDrugs() {
		listDrugs = new ArrayList<com.oreon.callosum.drugs.Drug>();

		if (getInstance().getDrugs().isEmpty()) {

		} else
			listDrugs.addAll(getInstance().getDrugs());

	}

	public List<com.oreon.callosum.drugs.Drug> getListDrugs() {
		if (listDrugs == null)
			initListDrugs();
		return listDrugs;
	}

	public void setListDrugs(List<com.oreon.callosum.drugs.Drug> listDrugs) {
		this.listDrugs = listDrugs;
	}

	protected List<com.oreon.callosum.drugs.Drug> listAvailableDrugs;

	void initListAvailableDrugs() {
		listAvailableDrugs = new ArrayList<com.oreon.callosum.drugs.Drug>();

		listAvailableDrugs = getEntityManager().createQuery(
				"select r from Drug r").getResultList();
		listAvailableDrugs.removeAll(getInstance().getDrugs());

	}

	public List<com.oreon.callosum.drugs.Drug> getListAvailableDrugs() {
		if (listAvailableDrugs == null)
			initListAvailableDrugs();
		return listAvailableDrugs;
	}

	public void setListAvailableDrugs(
			List<com.oreon.callosum.drugs.Drug> listAvailableDrugs) {
		this.listAvailableDrugs = listAvailableDrugs;
	}

	public void updateComposedAssociations() {

		if (listDrugs != null) {
			getInstance().getDrugs().clear();
			getInstance().getDrugs().addAll(listDrugs);
		}
	}

}
