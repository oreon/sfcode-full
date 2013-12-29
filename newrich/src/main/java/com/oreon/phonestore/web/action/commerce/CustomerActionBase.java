package com.oreon.phonestore.web.action.commerce;

import com.oreon.phonestore.domain.commerce.Customer;

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
import org.jboss.seam.annotations.Transactional;
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

public abstract class CustomerActionBase
		extends
			com.oreon.phonestore.web.action.domain.PersonAction<Customer>
		implements
			java.io.Serializable {

	@Out(required = false)
	private Customer customer = new Customer();

	private Customer selectedCustomer;
	
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
	//@Restrict("#{s:hasPermission('customer', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('customer', 'delete')}")
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

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	@Transactional
	public void store() {
		save(selectedCustomer, Customer.class);
	}
	
}
