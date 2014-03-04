package com.oreon.cerebrum.web.action.billing;

import com.oreon.cerebrum.billing.InvoiceItem;

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
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

//
public abstract class InvoiceItemActionBase extends BaseAction<InvoiceItem>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long invoiceItemId;

	@In(create = true, value = "serviceAction")
	com.oreon.cerebrum.web.action.billing.ServiceAction serviceAction;

	@In(create = true, value = "invoiceAction")
	com.oreon.cerebrum.web.action.billing.InvoiceAction invoiceAction;

	public void setInvoiceItemId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setInvoiceItemIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setServiceId(Long id) {

		if (id != null && id > 0)
			getInstance().setService(serviceAction.loadFromId(id));

	}

	public Long getServiceId() {
		if (getInstance().getService() != null)
			return getInstance().getService().getId();
		return 0L;
	}

	public void setInvoiceId(Long id) {

		if (id != null && id > 0)
			getInstance().setInvoice(invoiceAction.loadFromId(id));

	}

	public Long getInvoiceId() {
		if (getInstance().getInvoice() != null)
			return getInstance().getInvoice().getId();
		return 0L;
	}

	public Long getInvoiceItemId() {
		return (Long) getId();
	}

	public InvoiceItem getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(InvoiceItem t) {
		this.instance = t;
		loadAssociations();
	}

	public InvoiceItem getInvoiceItem() {
		return (InvoiceItem) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('invoiceItem', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('invoiceItem', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected InvoiceItem createInstance() {
		InvoiceItem instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}

	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.billing.Service service = serviceAction
				.getDefinedInstance();
		if (service != null && isNew()) {
			getInstance().setService(service);
		}

		com.oreon.cerebrum.billing.Invoice invoice = invoiceAction
				.getDefinedInstance();
		if (invoice != null && isNew()) {
			getInstance().setInvoice(invoice);
		}

	}

	public boolean isWired() {
		return true;
	}

	public InvoiceItem getDefinedInstance() {
		return (InvoiceItem) (isIdDefined() ? getInstance() : null);
	}

	public void setInvoiceItem(InvoiceItem t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setInvoiceItemId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<InvoiceItem> getEntityClass() {
		return InvoiceItem.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getService() != null) {
			criteria = criteria.add(Restrictions.eq("service.id", instance
					.getService().getId()));
		}

		if (instance.getInvoice() != null) {
			criteria = criteria.add(Restrictions.eq("invoice.id", instance
					.getInvoice().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getService() != null) {
			serviceAction.setInstance(getInstance().getService());
			serviceAction.loadAssociations();
		}

		if (getInstance().getInvoice() != null) {
			invoiceAction.setInstance(getInstance().getInvoice());
			invoiceAction.loadAssociations();
		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewInvoiceItem() {
		load(currentEntityId);
		return "viewInvoiceItem";
	}

}
