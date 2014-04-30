package com.oreon.phonestore.web.action.commerce;

import com.oreon.phonestore.domain.commerce.CustomerQuestion;

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
public abstract class CustomerQuestionActionBase
		extends
			BaseAction<CustomerQuestion> implements java.io.Serializable {

	@RequestParameter
	protected Long customerQuestionId;

	@In(create = true, value = "customerAction")
	com.oreon.phonestore.web.action.commerce.CustomerAction customerAction;

	public void setCustomerQuestionId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setCustomerQuestionIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
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

	public Long getCustomerQuestionId() {
		return (Long) getId();
	}

	public CustomerQuestion getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(CustomerQuestion t) {
		this.instance = t;
		loadAssociations();
	}

	public CustomerQuestion getCustomerQuestion() {
		return (CustomerQuestion) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('customerQuestion', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('customerQuestion', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected CustomerQuestion createInstance() {
		CustomerQuestion instance = super.createInstance();

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

		com.oreon.phonestore.domain.commerce.Customer customer = customerAction
				.getDefinedInstance();
		if (customer != null && isNew()) {
			getInstance().setCustomer(customer);
		}

	}

	public boolean isWired() {
		return true;
	}

	public CustomerQuestion getDefinedInstance() {
		return (CustomerQuestion) (isIdDefined() ? getInstance() : null);
	}

	public void setCustomerQuestion(CustomerQuestion t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setCustomerQuestionId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<CustomerQuestion> getEntityClass() {
		return CustomerQuestion.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getCustomer() != null) {
			criteria = criteria.add(Restrictions.eq("customer.id", instance
					.getCustomer().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getCustomer() != null) {
			customerAction.setInstance(getInstance().getCustomer());
			customerAction.loadAssociations();
		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewCustomerQuestion() {
		load(currentEntityId);
		return "viewCustomerQuestion";
	}

}