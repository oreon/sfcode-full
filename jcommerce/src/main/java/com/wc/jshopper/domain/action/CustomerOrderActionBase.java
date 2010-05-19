package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.CustomerOrder;

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

import com.wc.jshopper.domain.OrderItem;

public class CustomerOrderActionBase extends BaseAction<CustomerOrder>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private CustomerOrder customerOrder;

	@In(create = true, value = "employeeAction")
	com.wc.jshopper.domain.action.EmployeeAction employeeAction;

	@DataModel
	private List<CustomerOrder> customerOrderRecordList;

	public void setCustomerOrderId(Long id) {

		listOrderItems = new ArrayList<OrderItem>();

		setId(id);
		loadAssociations();
	}

	public Long getCustomerOrderId() {
		return (Long) getId();
	}

	@Factory("customerOrderRecordList")
	@Observer("archivedCustomerOrder")
	public void findRecords() {
		search();
	}

	public CustomerOrder getEntity() {
		return customerOrder;
	}

	@Override
	public void setEntity(CustomerOrder t) {
		this.customerOrder = t;
		loadAssociations();
	}

	public CustomerOrder getCustomerOrder() {
		return getInstance();
	}

	@Override
	protected CustomerOrder createInstance() {
		return new CustomerOrder();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.wc.jshopper.domain.Employee employee = employeeAction
				.getDefinedInstance();
		if (employee != null) {
			getInstance().setEmployee(employee);
		}

	}

	public boolean isWired() {
		return true;
	}

	public CustomerOrder getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setCustomerOrder(CustomerOrder t) {
		this.customerOrder = t;
		loadAssociations();
	}

	@Override
	public Class<CustomerOrder> getEntityClass() {
		return CustomerOrder.class;
	}

	@Override
	public void setEntityList(List<CustomerOrder> list) {
		this.customerOrderRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (customerOrder.getEmployee() != null) {
			criteria = criteria.add(Restrictions.eq("employee.id",
					customerOrder.getEmployee().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (customerOrder.getEmployee() != null) {
			employeeAction.setEntity(getEntity().getEmployee());
		}

	}

	public void updateAssociations() {

	}

	private List<OrderItem> listOrderItems;

	void initListOrderItems() {
		listOrderItems = new ArrayList<OrderItem>();
		if (getInstance().getOrderItems().isEmpty()) {

			addOrderItems();

		} else
			listOrderItems.addAll(getInstance().getOrderItems());
	}

	public List<OrderItem> getListOrderItems() {
		if (listOrderItems == null) {
			initListOrderItems();
		}
		return listOrderItems;
	}

	public void setListOrderItems(List<OrderItem> listOrderItems) {
		this.listOrderItems = listOrderItems;
	}

	public void deleteOrderItems(OrderItem orderItems) {
		listOrderItems.remove(orderItems);
	}

	@Begin(join = true)
	public void addOrderItems() {
		OrderItem orderItems = new OrderItem();

		orderItems.setCustomerOrder(getInstance());

		listOrderItems.add(orderItems);
	}

	public void updateComposedAssociations() {

		getInstance().getOrderItems().clear();
		getInstance().getOrderItems().addAll(listOrderItems);

	}

	public List<CustomerOrder> getEntityList() {
		if (customerOrderRecordList == null) {
			findRecords();
		}
		return customerOrderRecordList;
	}

}
