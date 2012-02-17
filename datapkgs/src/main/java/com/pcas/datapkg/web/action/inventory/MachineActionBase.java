package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.Machine;

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

import com.pcas.datapkg.domain.inventory.DrugInventory;

public abstract class MachineActionBase extends BaseAction<Machine>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Machine machine;

	@In(create = true, value = "customerAction")
	com.pcas.datapkg.web.action.inventory.CustomerAction customerAction;

	@In(create = true, value = "locationAction")
	com.pcas.datapkg.web.action.inventory.LocationAction locationAction;

	@DataModel
	private List<Machine> machineRecordList;

	public void setMachineId(Long id) {
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
	public void setMachineIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setCustomerId(Long id) {

		if (id != null && id > 0)
			getInstance().setCustomer(customerAction.loadFromId(id));

	}

	public Long getCustomerId() {
		if (getInstance().getCustomer() != null)
			return getInstance().getCustomer().getId();
		return 0L;
	}

	public void setLocationId(Long id) {

		if (id != null && id > 0)
			getInstance().setLocation(locationAction.loadFromId(id));

	}

	public Long getLocationId() {
		if (getInstance().getLocation() != null)
			return getInstance().getLocation().getId();
		return 0L;
	}

	public Long getMachineId() {
		return (Long) getId();
	}

	public Machine getEntity() {
		return machine;
	}

	//@Override
	public void setEntity(Machine t) {
		this.machine = t;
		loadAssociations();
	}

	public Machine getMachine() {
		return (Machine) getInstance();
	}

	@Override
	protected Machine createInstance() {
		Machine instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pcas.datapkg.domain.inventory.Customer customer = customerAction
				.getDefinedInstance();
		if (customer != null && isNew()) {
			getInstance().setCustomer(customer);
		}

		com.pcas.datapkg.domain.inventory.Location location = locationAction
				.getDefinedInstance();
		if (location != null && isNew()) {
			getInstance().setLocation(location);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Machine getDefinedInstance() {
		return (Machine) (isIdDefined() ? getInstance() : null);
	}

	public void setMachine(Machine t) {
		this.machine = t;
		if (machine != null)
			setMachineId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Machine> getEntityClass() {
		return Machine.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (machine.getCustomer() != null) {
			criteria = criteria.add(Restrictions.eq("customer.id", machine
					.getCustomer().getId()));
		}

		if (machine.getLocation() != null) {
			criteria = criteria.add(Restrictions.eq("location.id", machine
					.getLocation().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (machine.getCustomer() != null) {
			customerAction.setInstance(getInstance().getCustomer());
		}

		if (machine.getLocation() != null) {
			locationAction.setInstance(getInstance().getLocation());
		}

		initListDrugInventorys();

	}

	public void updateAssociations() {

	}

	protected List<com.pcas.datapkg.domain.inventory.DrugInventory> listDrugInventorys = new ArrayList<com.pcas.datapkg.domain.inventory.DrugInventory>();

	void initListDrugInventorys() {

		if (listDrugInventorys.isEmpty())
			listDrugInventorys.addAll(getInstance().getDrugInventorys());

	}

	public List<com.pcas.datapkg.domain.inventory.DrugInventory> getListDrugInventorys() {

		prePopulateListDrugInventorys();
		return listDrugInventorys;
	}

	public void prePopulateListDrugInventorys() {
	}

	public void setListDrugInventorys(
			List<com.pcas.datapkg.domain.inventory.DrugInventory> listDrugInventorys) {
		this.listDrugInventorys = listDrugInventorys;
	}

	public void deleteDrugInventorys(int index) {
		listDrugInventorys.remove(index);
	}

	@Begin(join = true)
	public void addDrugInventorys() {
		initListDrugInventorys();
		DrugInventory drugInventorys = new DrugInventory();

		drugInventorys.setMachine(getInstance());

		getListDrugInventorys().add(drugInventorys);
	}

	public void updateComposedAssociations() {

		if (listDrugInventorys != null) {
			getInstance().getDrugInventorys().clear();
			getInstance().getDrugInventorys().addAll(listDrugInventorys);
		}
	}

	public void clearLists() {
		listDrugInventorys.clear();

	}

	public String viewMachine() {
		load(currentEntityId);
		return "viewMachine";
	}

}
