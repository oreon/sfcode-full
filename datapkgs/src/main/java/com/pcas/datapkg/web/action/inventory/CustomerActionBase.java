package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.Customer;

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

import com.pcas.datapkg.domain.inventory.Machine;
import com.pcas.datapkg.domain.Employee;

public abstract class CustomerActionBase extends BaseAction<Customer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Customer customer;

	@In(create = true, value = "machineAction")
	com.pcas.datapkg.web.action.inventory.MachineAction machinesAction;

	@DataModel
	private List<Customer> customerRecordList;

	public void setCustomerId(Long id) {
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
	public void setCustomerIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getCustomerId() {
		return (Long) getId();
	}

	public Customer getEntity() {
		return customer;
	}

	//@Override
	public void setEntity(Customer t) {
		this.customer = t;
		loadAssociations();
	}

	public Customer getCustomer() {
		return (Customer) getInstance();
	}

	@Override
	protected Customer createInstance() {
		Customer instance = super.createInstance();

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

	public Customer getDefinedInstance() {
		return (Customer) (isIdDefined() ? getInstance() : null);
	}

	public void setCustomer(Customer t) {
		this.customer = t;
		if (customer != null)
			setCustomerId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}

	public com.pcas.datapkg.domain.inventory.Customer findByUnqName(String name) {
		return executeSingleResultNamedQuery("customer.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListMachines();

		initListEmployees();

	}

	public void updateAssociations() {

		com.pcas.datapkg.domain.inventory.Machine machines = (com.pcas.datapkg.domain.inventory.Machine) org.jboss.seam.Component
				.getInstance("machine");
		machines.setCustomer(customer);
		events.raiseTransactionSuccessEvent("archivedMachine");

	}

	protected List<com.pcas.datapkg.domain.inventory.Machine> listMachines = new ArrayList<com.pcas.datapkg.domain.inventory.Machine>();

	void initListMachines() {

		if (listMachines.isEmpty())
			listMachines.addAll(getInstance().getMachines());

	}

	public List<com.pcas.datapkg.domain.inventory.Machine> getListMachines() {

		prePopulateListMachines();
		return listMachines;
	}

	public void prePopulateListMachines() {
	}

	public void setListMachines(
			List<com.pcas.datapkg.domain.inventory.Machine> listMachines) {
		this.listMachines = listMachines;
	}

	public void deleteMachines(int index) {
		listMachines.remove(index);
	}

	@Begin(join = true)
	public void addMachines() {
		initListMachines();
		Machine machines = new Machine();

		machines.setCustomer(getInstance());

		getListMachines().add(machines);
	}

	protected List<com.pcas.datapkg.domain.Employee> listEmployees = new ArrayList<com.pcas.datapkg.domain.Employee>();

	void initListEmployees() {

		if (listEmployees.isEmpty())
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<com.pcas.datapkg.domain.Employee> getListEmployees() {

		prePopulateListEmployees();
		return listEmployees;
	}

	public void prePopulateListEmployees() {
	}

	public void setListEmployees(
			List<com.pcas.datapkg.domain.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public void deleteEmployees(int index) {
		listEmployees.remove(index);
	}

	@Begin(join = true)
	public void addEmployees() {
		initListEmployees();
		Employee employees = new Employee();

		employees.setCustomer(getInstance());

		getListEmployees().add(employees);
	}

	public void updateComposedAssociations() {

		if (listMachines != null) {
			getInstance().getMachines().clear();
			getInstance().getMachines().addAll(listMachines);
		}

		if (listEmployees != null) {
			getInstance().getEmployees().clear();
			getInstance().getEmployees().addAll(listEmployees);
		}
	}

	public void clearLists() {
		listMachines.clear();
		listEmployees.clear();

	}

	public String viewCustomer() {
		load(currentEntityId);
		return "viewCustomer";
	}

}
