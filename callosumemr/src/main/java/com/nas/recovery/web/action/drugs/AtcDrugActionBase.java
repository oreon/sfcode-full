package com.nas.recovery.web.action.drugs;

import com.oreon.callosum.drugs.AtcDrug;

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

import com.oreon.callosum.drugs.AtcDrug;

public abstract class AtcDrugActionBase extends BaseAction<AtcDrug>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private AtcDrug atcDrug;

	@In(create = true, value = "drugAction")
	com.nas.recovery.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "atcDrugAction")
	com.nas.recovery.web.action.drugs.AtcDrugAction parentAction;

	@DataModel
	private List<AtcDrug> atcDrugRecordList;

	public void setAtcDrugId(Long id) {
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
	public void setAtcDrugIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
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
		return atcDrug;
	}

	//@Override
	public void setEntity(AtcDrug t) {
		this.atcDrug = t;
		loadAssociations();
	}

	public AtcDrug getAtcDrug() {
		return (AtcDrug) getInstance();
	}

	@Override
	protected AtcDrug createInstance() {
		return new AtcDrug();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.callosum.drugs.Drug drug = drugAction.getDefinedInstance();
		if (drug != null) {
			getInstance().setDrug(drug);
		}

		com.oreon.callosum.drugs.AtcDrug parent = parentAction
				.getDefinedInstance();
		if (parent != null) {
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
		this.atcDrug = t;
		loadAssociations();
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

		if (atcDrug.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", atcDrug
					.getDrug().getId()));
		}

		if (atcDrug.getParent() != null) {
			criteria = criteria.add(Restrictions.eq("parent.id", atcDrug
					.getParent().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (atcDrug.getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());
		}

		if (atcDrug.getParent() != null) {
			parentAction.setInstance(getInstance().getParent());
		}

		initListSubcategories();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.callosum.drugs.AtcDrug> listSubcategories;

	void initListSubcategories() {
		listSubcategories = new ArrayList<com.oreon.callosum.drugs.AtcDrug>();

		if (getInstance().getSubcategories().isEmpty()) {

		} else
			listSubcategories.addAll(getInstance().getSubcategories());

	}

	public List<com.oreon.callosum.drugs.AtcDrug> getListSubcategories() {
		if (listSubcategories == null)
			initListSubcategories();
		return listSubcategories;
	}

	public void setListSubcategories(
			List<com.oreon.callosum.drugs.AtcDrug> listSubcategories) {
		this.listSubcategories = listSubcategories;
	}

	public void deleteSubcategories(int index) {
		listSubcategories.remove(index);
	}

	@Begin(join = true)
	public void addSubcategories() {
		AtcDrug subcategories = new AtcDrug();

		subcategories.setParent(getInstance());

		getListSubcategories().add(subcategories);
	}

	public void updateComposedAssociations() {

		if (listSubcategories != null) {
			getInstance().getSubcategories().clear();
			getInstance().getSubcategories().addAll(listSubcategories);
		}
	}

}
