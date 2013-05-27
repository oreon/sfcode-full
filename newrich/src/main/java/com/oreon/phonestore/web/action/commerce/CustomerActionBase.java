package com.oreon.phonestore.web.action.commerce;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;

import com.oreon.phonestore.domain.commerce.Customer;

public abstract class CustomerActionBase
		extends
			com.oreon.phonestore.web.action.domain.PersonAction<Customer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Customer customer;

	public void setCustomerId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		customer = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setCustomerIdForModalDlg(Long id) {
		setId(id);
		customer = loadInstance();
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
	@Restrict("#{s:hasPermission('customer', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('customer', 'delete')}")
	public void archiveById() {
		super.archiveById();
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

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewCustomer() {
		load(currentEntityId);
		return "viewCustomer";
	}

}
