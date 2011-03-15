package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.Customer;

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

import com.pwc.insuranceclaims.quickclaim.Policy;
import com.pwc.insuranceclaims.quickclaim.Dependent;

public abstract class CustomerActionBase extends BaseAction<Customer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Customer customer;

	@In(create = true, value = "policyAction")
	com.pwc.insuranceclaims.web.action.quickclaim.PolicyAction policysAction;

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
		return new Customer();
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
		loadAssociations();
	}

	@Override
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListPolicys();

		initListDependents();

	}

	public void updateAssociations() {

		com.pwc.insuranceclaims.quickclaim.Policy policys = (com.pwc.insuranceclaims.quickclaim.Policy) org.jboss.seam.Component
				.getInstance("policy");
		policys.setCustomer(customer);
		events.raiseTransactionSuccessEvent("archivedPolicy");

	}

	protected List<com.pwc.insuranceclaims.quickclaim.Policy> listPolicys = new ArrayList<com.pwc.insuranceclaims.quickclaim.Policy>();

	void initListPolicys() {

		if (listPolicys.isEmpty())
			listPolicys.addAll(getInstance().getPolicys());

	}

	public List<com.pwc.insuranceclaims.quickclaim.Policy> getListPolicys() {

		prePopulateListPolicys();
		return listPolicys;
	}

	public void prePopulateListPolicys() {
	}

	public void setListPolicys(
			List<com.pwc.insuranceclaims.quickclaim.Policy> listPolicys) {
		this.listPolicys = listPolicys;
	}

	public void deletePolicys(int index) {
		listPolicys.remove(index);
	}

	@Begin(join = true)
	public void addPolicys() {
		initListPolicys();
		Policy policys = new Policy();

		policys.setCustomer(getInstance());

		getListPolicys().add(policys);
	}

	protected List<com.pwc.insuranceclaims.quickclaim.Dependent> listDependents = new ArrayList<com.pwc.insuranceclaims.quickclaim.Dependent>();

	void initListDependents() {

		if (listDependents.isEmpty())
			listDependents.addAll(getInstance().getDependents());

	}

	public List<com.pwc.insuranceclaims.quickclaim.Dependent> getListDependents() {

		prePopulateListDependents();
		return listDependents;
	}

	public void prePopulateListDependents() {
	}

	public void setListDependents(
			List<com.pwc.insuranceclaims.quickclaim.Dependent> listDependents) {
		this.listDependents = listDependents;
	}

	public void deleteDependents(int index) {
		listDependents.remove(index);
	}

	@Begin(join = true)
	public void addDependents() {
		initListDependents();
		Dependent dependents = new Dependent();

		dependents.setCustomer(getInstance());

		getListDependents().add(dependents);
	}

	public void updateComposedAssociations() {

		if (listPolicys != null) {
			getInstance().getPolicys().clear();
			getInstance().getPolicys().addAll(listPolicys);
		}

		if (listDependents != null) {
			getInstance().getDependents().clear();
			getInstance().getDependents().addAll(listDependents);
		}
	}

	public void clearLists() {
		listPolicys.clear();
		listDependents.clear();

	}

}
