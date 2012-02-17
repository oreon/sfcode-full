package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.DrugInventory;

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

public abstract class DrugInventoryActionBase extends BaseAction<DrugInventory>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private DrugInventory drugInventory;

	@In(create = true, value = "drugAbstractAction")
	com.pcas.datapkg.web.action.inventory.DrugAbstractAction drugAbstractAction;

	@In(create = true, value = "machineAction")
	com.pcas.datapkg.web.action.inventory.MachineAction machineAction;

	@DataModel
	private List<DrugInventory> drugInventoryRecordList;

	public void setDrugInventoryId(Long id) {
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
	public void setDrugInventoryIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setDrugAbstractId(Long id) {

		if (id != null && id > 0)
			getInstance().setDrugAbstract(drugAbstractAction.loadFromId(id));

	}

	public Long getDrugAbstractId() {
		if (getInstance().getDrugAbstract() != null)
			return getInstance().getDrugAbstract().getId();
		return 0L;
	}

	public void setMachineId(Long id) {

		if (id != null && id > 0)
			getInstance().setMachine(machineAction.loadFromId(id));

	}

	public Long getMachineId() {
		if (getInstance().getMachine() != null)
			return getInstance().getMachine().getId();
		return 0L;
	}

	public Long getDrugInventoryId() {
		return (Long) getId();
	}

	public DrugInventory getEntity() {
		return drugInventory;
	}

	//@Override
	public void setEntity(DrugInventory t) {
		this.drugInventory = t;
		loadAssociations();
	}

	public DrugInventory getDrugInventory() {
		return (DrugInventory) getInstance();
	}

	@Override
	protected DrugInventory createInstance() {
		DrugInventory instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pcas.datapkg.domain.inventory.DrugAbstract drugAbstract = drugAbstractAction
				.getDefinedInstance();
		if (drugAbstract != null && isNew()) {
			getInstance().setDrugAbstract(drugAbstract);
		}

		com.pcas.datapkg.domain.inventory.Machine machine = machineAction
				.getDefinedInstance();
		if (machine != null && isNew()) {
			getInstance().setMachine(machine);
		}

	}

	public boolean isWired() {
		return true;
	}

	public DrugInventory getDefinedInstance() {
		return (DrugInventory) (isIdDefined() ? getInstance() : null);
	}

	public void setDrugInventory(DrugInventory t) {
		this.drugInventory = t;
		if (drugInventory != null)
			setDrugInventoryId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<DrugInventory> getEntityClass() {
		return DrugInventory.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (drugInventory.getDrugAbstract() != null) {
			criteria = criteria.add(Restrictions.eq("drugAbstract.id",
					drugInventory.getDrugAbstract().getId()));
		}

		if (drugInventory.getMachine() != null) {
			criteria = criteria.add(Restrictions.eq("machine.id", drugInventory
					.getMachine().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (drugInventory.getDrugAbstract() != null) {
			drugAbstractAction.setInstance(getInstance().getDrugAbstract());
		}

		if (drugInventory.getMachine() != null) {
			machineAction.setInstance(getInstance().getMachine());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewDrugInventory() {
		load(currentEntityId);
		return "viewDrugInventory";
	}

}
