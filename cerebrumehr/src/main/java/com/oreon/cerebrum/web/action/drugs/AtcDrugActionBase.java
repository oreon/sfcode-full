package com.oreon.cerebrum.web.action.drugs;

import com.oreon.cerebrum.drugs.AtcDrug;

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

import com.oreon.cerebrum.drugs.AtcDrug;

//
public abstract class AtcDrugActionBase extends BaseAction<AtcDrug>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long atcDrugId;

	@In(create = true, value = "drugAction")
	com.oreon.cerebrum.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "atcDrugAction")
	com.oreon.cerebrum.web.action.drugs.AtcDrugAction parentAction;

	public void setAtcDrugId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAtcDrugIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setDrugId(Long id) {

		if (id != null && id > 0)
			getInstance().setDrug(drugAction.loadFromId(id));

	}

	public Long getDrugId() {
		if (getInstance().getDrug() != null)
			return getInstance().getDrug().getId();
		return 0L;
	}

	public void setParentId(Long id) {

		if (id != null && id > 0)
			getInstance().setParent(parentAction.loadFromId(id));

	}

	public Long getParentId() {
		if (getInstance().getParent() != null)
			return getInstance().getParent().getId();
		return 0L;
	}

	public Long getAtcDrugId() {
		return (Long) getId();
	}

	public AtcDrug getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(AtcDrug t) {
		this.instance = t;
		loadAssociations();
	}

	public AtcDrug getAtcDrug() {
		return (AtcDrug) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('atcDrug', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('atcDrug', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected AtcDrug createInstance() {
		AtcDrug instance = super.createInstance();

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

		com.oreon.cerebrum.drugs.Drug drug = drugAction.getDefinedInstance();
		if (drug != null && isNew()) {
			getInstance().setDrug(drug);
		}

		com.oreon.cerebrum.drugs.AtcDrug parent = parentAction
				.getDefinedInstance();
		if (parent != null && isNew()) {
			getInstance().setParent(parent);
		}

	}

	public boolean isWired() {
		return true;
	}

	public AtcDrug getDefinedInstance() {
		return (AtcDrug) (isIdDefined() ? getInstance() : null);
	}

	public void setAtcDrug(AtcDrug t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setAtcDrugId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<AtcDrug> getEntityClass() {
		return AtcDrug.class;
	}

	public List<AtcDrug> getTopLevelParent() {
		return getEntityManager().createQuery(
				"select e from AtcDrug e where e.parent is null")
				.getResultList();
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", instance
					.getDrug().getId()));
		}

		if (instance.getParent() != null) {
			criteria = criteria.add(Restrictions.eq("parent.id", instance
					.getParent().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());
			drugAction.loadAssociations();
		}

		if (getInstance().getParent() != null) {
			parentAction.setInstance(getInstance().getParent());
			parentAction.loadAssociations();
		}

		initListSubcategories();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.drugs.AtcDrug> listSubcategories = new ArrayList<com.oreon.cerebrum.drugs.AtcDrug>();

	void initListSubcategories() {

		if (listSubcategories.isEmpty())
			listSubcategories.addAll(getInstance().getSubcategories());

	}

	public List<com.oreon.cerebrum.drugs.AtcDrug> getListSubcategories() {

		prePopulateListSubcategories();
		return listSubcategories;
	}

	public void prePopulateListSubcategories() {
	}

	public void setListSubcategories(
			List<com.oreon.cerebrum.drugs.AtcDrug> listSubcategories) {
		this.listSubcategories = listSubcategories;
	}

	public void deleteSubcategories(int index) {
		listSubcategories.remove(index);
	}

	@Begin(join = true)
	public void addSubcategories() {

		initListSubcategories();
		AtcDrug subcategories = new AtcDrug();

		subcategories.setParent(getInstance());

		getListSubcategories().add(subcategories);

	}

	public void tions() {

		if (listSubcategories != null) {

			java.util.Set<AtcDrug> items = getInstance().getSubcategories();
			for (AtcDrug item : items) {
				if (!listSubcategories.contains(item))
					getEntityManager().remove(item);
			}

			for (AtcDrug item : listSubcategories) {
				item.setParent(getInstance());
			}

			getInstance().getSubcategories().clear();
			getInstance().getSubcategories().addAll(listSubcategories);
		}
	}

	public void clearLists() {
		listSubcategories.clear();

	}

	public String viewAtcDrug() {
		load(currentEntityId);
		return "viewAtcDrug";
	}

}
