package com.oreon.phonestore.web.action.commerce;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.commerce.CustomerOrder;
import com.oreon.phonestore.domain.commerce.OrderItem;

public abstract class CustomerOrderActionBase extends BaseAction<CustomerOrder>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private CustomerOrder customerOrder;

	@In(create = true, value = "customerAction")
	com.oreon.phonestore.web.action.commerce.CustomerAction customerAction;

	public void setCustomerOrderId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		customerOrder = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setCustomerOrderIdForModalDlg(Long id) {
		setId(id);
		customerOrder = loadInstance();
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

	public Long getCustomerOrderId() {
		return (Long) getId();
	}

	public CustomerOrder getEntity() {
		return customerOrder;
	}

	//@Override
	public void setEntity(CustomerOrder t) {
		this.customerOrder = t;
		loadAssociations();
	}

	public CustomerOrder getCustomerOrder() {
		return (CustomerOrder) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('customerOrder', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('customerOrder', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected CustomerOrder createInstance() {
		CustomerOrder instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.phonestore.domain.commerce.Customer customer = customerAction
				.getDefinedInstance();
		if (customer != null && isNew()) {
			getInstance().setCustomer(customer);
		}

	}

	public boolean isWired() {
		return true;
	}

	public CustomerOrder getDefinedInstance() {
		return (CustomerOrder) (isIdDefined() ? getInstance() : null);
	}

	public void setCustomerOrder(CustomerOrder t) {
		this.customerOrder = t;
		if (customerOrder != null)
			setCustomerOrderId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<CustomerOrder> getEntityClass() {
		return CustomerOrder.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (customerOrder.getCustomer() != null) {
			criteria = criteria.add(Restrictions.eq("customer.id",
					customerOrder.getCustomer().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (customerOrder.getCustomer() != null) {
			customerAction.setInstance(getInstance().getCustomer());
			customerAction.loadAssociations();
		}

		initListOrderItems();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.phonestore.domain.commerce.OrderItem> listOrderItems = new ArrayList<com.oreon.phonestore.domain.commerce.OrderItem>();

	void initListOrderItems() {

		if (listOrderItems.isEmpty())
			listOrderItems.addAll(getInstance().getOrderItems());

	}

	public List<com.oreon.phonestore.domain.commerce.OrderItem> getListOrderItems() {

		prePopulateListOrderItems();
		return listOrderItems;
	}

	public void prePopulateListOrderItems() {
	}

	public void setListOrderItems(
			List<com.oreon.phonestore.domain.commerce.OrderItem> listOrderItems) {
		this.listOrderItems = listOrderItems;
	}

	public void deleteOrderItems(int index) {
		listOrderItems.remove(index);
	}

	@Begin(join = true)
	public void addOrderItems() {
		initListOrderItems();
		OrderItem orderItems = new OrderItem();

		orderItems.setCustomerOrder(getInstance());

		getListOrderItems().add(orderItems);
	}

	public void updateComposedAssociations() {

		if (listOrderItems != null) {
			getInstance().getOrderItems().clear();
			getInstance().getOrderItems().addAll(listOrderItems);
		}
	}

	public void clearLists() {
		listOrderItems.clear();

	}

	public String viewCustomerOrder() {
		load(currentEntityId);
		return "viewCustomerOrder";
	}

}
