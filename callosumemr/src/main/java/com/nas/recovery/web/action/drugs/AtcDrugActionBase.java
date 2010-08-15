package com.nas.recovery.web.action.drugs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.callosum.drugs.AtcDrug;
import com.oreon.callosum.drugs.Drug;

public abstract class AtcDrugActionBase extends BaseAction<AtcDrug> implements
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

		if (listSubcategories == null || isDifferentFromCurrent(id))
			listSubcategories = new ArrayList<AtcDrug>();

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

	// @Factory("atcDrugRecordList")
	// @Observer("archivedAtcDrug")
	public void findRecords() {
		// search();
	}

	public AtcDrug getEntity() {
		return atcDrug;
	}

	@Override
	public void setEntity(AtcDrug t) {
		this.atcDrug = t;
		loadAssociations();
	}

	public AtcDrug getAtcDrug() {
		return getInstance();
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
		return isIdDefined() ? getInstance() : null;
	}

	public void setAtcDrug(AtcDrug t) {
		this.atcDrug = t;
		loadAssociations();
	}

	@Override
	public Class<AtcDrug> getEntityClass() {
		return AtcDrug.class;
	}

	@Override
	public void setEntityList(List<AtcDrug> list) {
		this.atcDrugRecordList = list;
	}

	/**
	 * This function adds associated entities to an example criterion
	 * 
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (atcDrug.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", atcDrug
					.getDrug().getId()));
		}

		if (atcDrug.getParent() != null) {
			criteria = criteria.add(Restrictions.eq("parent.id", atcDrug
					.getParent().getId()));
		}

	}

	/**
	 * This function is responsible for loading associations for the given
	 * entity e.g. when viewing an order, we load the customer so that customer
	 * can be shown on the customer tab within viewOrder.xhtml
	 * 
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (atcDrug.getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());
		}

		if (atcDrug.getParent() != null) {
			parentAction.setInstance(getInstance().getParent());
		}

	}

	public void updateAssociations() {

	}

	protected List<AtcDrug> listSubcategories;

	void initListSubcategories() {
		listSubcategories = new ArrayList<AtcDrug>();
		if (getInstance().getSubcategories().isEmpty()) {

		} else
			listSubcategories.addAll(getInstance().getSubcategories());
	}

	public List<AtcDrug> getListSubcategories() {
		if (listSubcategories == null || listSubcategories.isEmpty()) {
			initListSubcategories();
		}
		return listSubcategories;
	}

	public void setListSubcategories(List<AtcDrug> listSubcategories) {
		this.listSubcategories = listSubcategories;
	}

	public void deleteSubcategories(int index) {
		listSubcategories.remove(index);
	}

	@Begin(join = true)
	public void addSubcategories() {
		AtcDrug subcategories = new AtcDrug();

		subcategories.setParent(getInstance());

		listSubcategories.add(subcategories);
	}

	public void updateComposedAssociations() {

		if (listSubcategories != null) {
			getInstance().getSubcategories().clear();
			getInstance().getSubcategories().addAll(listSubcategories);
		}

	}

	public List<AtcDrug> getEntityList() {
		if (atcDrugRecordList == null) {
			findRecords();
		}
		return atcDrugRecordList;
	}

	
	private Long currentDrugId; 


	public List<AtcDrug> getTopLevelParent() {
		return executeQuery("select c from AtcDrug c where c.parent is null");
	}

	public void processSelection(NodeSelectedEvent event) {
		HtmlTree tree = (HtmlTree) event.getComponent();

		BusinessEntity be = (BusinessEntity) tree.getRowData();
		if (be instanceof Drug) {
			currentDrugId = be.getId();
		}
	}

	public void setCurrentDrugId(Long currentDrugId) {
		this.currentDrugId = currentDrugId;
	}

	public Long getCurrentDrugId() {
		return currentDrugId;
	}

}
