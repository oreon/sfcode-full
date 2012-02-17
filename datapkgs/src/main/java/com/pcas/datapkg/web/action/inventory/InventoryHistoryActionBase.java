package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.InventoryHistory;

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

public abstract class InventoryHistoryActionBase
		extends
			BaseAction<InventoryHistory> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private InventoryHistory inventoryHistory;

	@In(create = true, value = "drugInventoryAction")
	com.pcas.datapkg.web.action.inventory.DrugInventoryAction drugInventoryAction;

	@DataModel
	private List<InventoryHistory> inventoryHistoryRecordList;

	public void setInventoryHistoryId(Long id) {
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
	public void setInventoryHistoryIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setDrugInventoryId(Long id) {

		if (id != null && id > 0)
			getInstance().setDrugInventory(drugInventoryAction.loadFromId(id));

	}

	public Long getDrugInventoryId() {
		if (getInstance().getDrugInventory() != null)
			return getInstance().getDrugInventory().getId();
		return 0L;
	}

	public Long getInventoryHistoryId() {
		return (Long) getId();
	}

	public InventoryHistory getEntity() {
		return inventoryHistory;
	}

	//@Override
	public void setEntity(InventoryHistory t) {
		this.inventoryHistory = t;
		loadAssociations();
	}

	public InventoryHistory getInventoryHistory() {
		return (InventoryHistory) getInstance();
	}

	@Override
	protected InventoryHistory createInstance() {
		InventoryHistory instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pcas.datapkg.domain.inventory.DrugInventory drugInventory = drugInventoryAction
				.getDefinedInstance();
		if (drugInventory != null && isNew()) {
			getInstance().setDrugInventory(drugInventory);
		}

	}

	public boolean isWired() {
		return true;
	}

	public InventoryHistory getDefinedInstance() {
		return (InventoryHistory) (isIdDefined() ? getInstance() : null);
	}

	public void setInventoryHistory(InventoryHistory t) {
		this.inventoryHistory = t;
		if (inventoryHistory != null)
			setInventoryHistoryId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<InventoryHistory> getEntityClass() {
		return InventoryHistory.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (inventoryHistory.getDrugInventory() != null) {
			criteria = criteria.add(Restrictions.eq("drugInventory.id",
					inventoryHistory.getDrugInventory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (inventoryHistory.getDrugInventory() != null) {
			drugInventoryAction.setInstance(getInstance().getDrugInventory());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewInventoryHistory() {
		load(currentEntityId);
		return "viewInventoryHistory";
	}

}
