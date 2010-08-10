package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.OrderItem;

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

public class OrderItemActionBase extends BaseAction<OrderItem>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private OrderItem orderItem;

	@In(create = true, value = "customerOrderAction")
	com.wc.jshopper.domain.action.CustomerOrderAction customerOrderAction;

	@In(create = true, value = "productAction")
	com.wc.jshopper.domain.action.ProductAction productAction;

	@DataModel
	private List<OrderItem> orderItemRecordList;

	public void setOrderItemId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getOrderItemId() {
		return (Long) getId();
	}

	@Factory("orderItemRecordList")
	@Observer("archivedOrderItem")
	public void findRecords() {
		search();
	}

	public OrderItem getEntity() {
		return orderItem;
	}

	@Override
	public void setEntity(OrderItem t) {
		this.orderItem = t;
		loadAssociations();
	}

	public OrderItem getOrderItem() {
		return getInstance();
	}

	@Override
	protected OrderItem createInstance() {
		return new OrderItem();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.wc.jshopper.domain.CustomerOrder customerOrder = customerOrderAction
				.getDefinedInstance();
		if (customerOrder != null) {
			getInstance().setCustomerOrder(customerOrder);
		}
		com.wc.jshopper.domain.Product product = productAction
				.getDefinedInstance();
		if (product != null) {
			getInstance().setProduct(product);
		}

	}

	public boolean isWired() {
		return true;
	}

	public OrderItem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setOrderItem(OrderItem t) {
		this.orderItem = t;
		loadAssociations();
	}

	@Override
	public Class<OrderItem> getEntityClass() {
		return OrderItem.class;
	}

	@Override
	public void setEntityList(List<OrderItem> list) {
		this.orderItemRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (orderItem.getCustomerOrder() != null) {
			criteria = criteria.add(Restrictions.eq("customerOrder.id",
					orderItem.getCustomerOrder().getId()));
		}

		if (orderItem.getProduct() != null) {
			criteria = criteria.add(Restrictions.eq("product.id", orderItem
					.getProduct().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (orderItem.getCustomerOrder() != null) {
			customerOrderAction.setEntity(getEntity().getCustomerOrder());
		}

		if (orderItem.getProduct() != null) {
			productAction.setEntity(getEntity().getProduct());
		}

	}

	public void updateAssociations() {

	}

	public List<OrderItem> getEntityList() {
		if (orderItemRecordList == null) {
			findRecords();
		}
		return orderItemRecordList;
	}

}
