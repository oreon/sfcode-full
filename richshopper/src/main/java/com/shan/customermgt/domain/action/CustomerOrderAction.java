package com.shan.customermgt.domain.action;

import com.shan.customermgt.domain.CustomerOrder;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

import com.shan.customermgt.domain.OrderItem;

@Scope(ScopeType.CONVERSATION)
@Name("customerOrderAction")
public class CustomerOrderAction extends BaseAction<CustomerOrder>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private CustomerOrder customerOrder;

	@DataModel
	private List<CustomerOrder> customerOrderList;

	@Factory("customerOrderList")
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
	}

	@Override
	public void setEntityList(List<CustomerOrder> list) {
		this.customerOrderList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (customerOrder.getAccount() != null) {
			criteria = criteria.add(Restrictions.eq("account.id", customerOrder
					.getAccount().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<OrderItem> listOrderItems;

	void initListOrderItems() {
		listOrderItems = new ArrayList<OrderItem>();
		if (customerOrder.getOrderItems().isEmpty()) {

		} else
			listOrderItems.addAll(customerOrder.getOrderItems());
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

		orderItems.setCustomerOrder(customerOrder);

		listOrderItems.add(orderItems);
	}

	public void updateComposedAssociations() {

		customerOrder.getOrderItems().clear();
		customerOrder.getOrderItems().addAll(listOrderItems);

	}

	public List<CustomerOrder> getEntityList() {
		if (customerOrderList == null) {
			findRecords();
		}
		return customerOrderList;
	}

}
