package com.oreon.phonestore.web.action.commerce;

import com.oreon.phonestore.domain.commerce.OrderItem;

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
import org.jboss.seam.annotations.security.Restrict;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class OrderItemActionBase extends BaseAction<OrderItem>
		implements
			java.io.Serializable {

	@Out(required = false)
	private OrderItem orderItem = new OrderItem();

	@In(create = true, value = "customerOrderAction")
	com.oreon.phonestore.web.action.commerce.CustomerOrderAction customerOrderAction;

	@In(create = true, value = "productAction")
	com.oreon.phonestore.web.action.commerce.ProductAction productAction;

	public void setOrderItemId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		orderItem = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setOrderItemIdForModalDlg(Long id) {
		setId(id);
		orderItem = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setCustomerOrderId(Long id) {

		if (id != null && id > 0)
			getInstance().setCustomerOrder(customerOrderAction.loadFromId(id));

	}

	public Long getCustomerOrderId() {
		if (getInstance().getCustomerOrder() != null)
			return getInstance().getCustomerOrder().getId();
		return 0L;
	}

	public void setProductId(Long id) {

		if (id != null && id > 0)
			getInstance().setProduct(productAction.loadFromId(id));

	}

	public Long getProductId() {
		if (getInstance().getProduct() != null)
			return getInstance().getProduct().getId();
		return 0L;
	}

	public Long getOrderItemId() {
		return (Long) getId();
	}

	public OrderItem getEntity() {
		return orderItem;
	}

	//@Override
	public void setEntity(OrderItem t) {
		this.orderItem = t;
		loadAssociations();
	}

	public OrderItem getOrderItem() {
		return (OrderItem) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('orderItem', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('orderItem', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected OrderItem createInstance() {
		OrderItem instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.phonestore.domain.commerce.CustomerOrder customerOrder = customerOrderAction
				.getDefinedInstance();
		if (customerOrder != null && isNew()) {
			getInstance().setCustomerOrder(customerOrder);
		}

		com.oreon.phonestore.domain.commerce.Product product = productAction
				.getDefinedInstance();
		if (product != null && isNew()) {
			getInstance().setProduct(product);
		}

	}

	public boolean isWired() {
		return true;
	}

	public OrderItem getDefinedInstance() {
		return (OrderItem) (isIdDefined() ? getInstance() : null);
	}

	public void setOrderItem(OrderItem t) {
		this.orderItem = t;
		if (orderItem != null)
			setOrderItemId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<OrderItem> getEntityClass() {
		return OrderItem.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

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
			customerOrderAction.setInstance(getInstance().getCustomerOrder());
			customerOrderAction.loadAssociations();
		}

		if (orderItem.getProduct() != null) {
			productAction.setInstance(getInstance().getProduct());
			productAction.loadAssociations();
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewOrderItem() {
		load(currentEntityId);
		return "viewOrderItem";
	}

}
