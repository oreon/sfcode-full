package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.Customer;

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

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public class CustomerActionBase extends BaseAction<Customer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Customer customer;

	@In(create = true, value = "userAction")
	com.wc.jshopper.users.action.UserAction userAction;

	@DataModel
	private List<Customer> customerRecordList;

	public void setCustomerId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getCustomerId() {
		return (Long) getId();
	}

	@Factory("customerRecordList")
	@Observer("archivedCustomer")
	public void findRecords() {
		search();
	}

	public Customer getEntity() {
		return customer;
	}

	@Override
	public void setEntity(Customer t) {
		this.customer = t;
		loadAssociations();
	}

	public Customer getCustomer() {
		return getInstance();
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
		com.wc.jshopper.users.User user = userAction.getDefinedInstance();
		if (user != null) {
			getInstance().setUser(user);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Customer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setCustomer(Customer t) {
		this.customer = t;
		loadAssociations();
	}

	@Override
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}

	@Override
	public void setEntityList(List<Customer> list) {
		this.customerRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (customer.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", customer
					.getUser().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (customer.getUser() != null) {
			userAction.setEntity(getEntity().getUser());
		}

	}

	public void updateAssociations() {

	}

	public List<Customer> getEntityList() {
		if (customerRecordList == null) {
			findRecords();
		}
		return customerRecordList;
	}

	public com.wc.jshopper.domain.Customer findCustomerByEmail(String email) {

		return executeSingleResultNamedQuery("findCustomerByEmail", email);

	}

}
