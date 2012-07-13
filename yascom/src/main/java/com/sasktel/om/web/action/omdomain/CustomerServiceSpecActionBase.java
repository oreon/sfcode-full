package com.sasktel.om.web.action.omdomain;

import com.sasktel.om.omdomain.CustomerServiceSpec;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DualListModel;

import org.witchcraft.utils.ViewUtils;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.sasktel.om.omdomain.ResourceServiceSpec;

public abstract class CustomerServiceSpecActionBase
		extends
			BaseAction<CustomerServiceSpec> implements java.io.Serializable {

	@Inject
	com.sasktel.om.web.action.omdomain.WorkflowAction workflowAction;

	protected Predicate[] getSearchPredicates(Root<CustomerServiceSpec> root) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		/*
		String name = search.getName();
		if (name != null && !"".equals(name)) {
			predicatesList.add(builder.like(root.<String> get("name"),
					'%' + name + '%'));
		}
		
		int stock = search.getStock();
		if (stock != 0) {
			predicatesList.add(builder.equal(root.get("stock"), stock));
		}*/

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	@Override
	protected Class<CustomerServiceSpec> getEntityClass() {
		return CustomerServiceSpec.class;
	}

	public CustomerServiceSpec createInstance() {
		return new CustomerServiceSpec();
	}

	public CustomerServiceSpec getCustomerServiceSpec() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setCustomerServiceSpec(CustomerServiceSpec customerServiceSpec) {
		this.entity = customerServiceSpec;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListResourceServiceSpecs();

	}

	public void updateAssociations() {
	}

	protected List<com.sasktel.om.omdomain.ResourceServiceSpec> listResourceServiceSpecs = new ArrayList<com.sasktel.om.omdomain.ResourceServiceSpec>();

	void initListResourceServiceSpecs() {
		prePopulateListResourceServiceSpecs();
		listResourceServiceSpecs.addAll(getEntity().getResourceServiceSpecs());
	}

	public List<com.sasktel.om.omdomain.ResourceServiceSpec> getListResourceServiceSpecs() {
		return listResourceServiceSpecs;
	}

	public void setListResourceServiceSpecs(
			List<com.sasktel.om.omdomain.ResourceServiceSpec> listResourceServiceSpecs) {
		this.listResourceServiceSpecs = listResourceServiceSpecs;
	}

	public void prePopulateListResourceServiceSpecs() {
	}

	public void deleteResourceServiceSpecs(int index) {
		listResourceServiceSpecs.remove(index);
	}

	public void deleteResourceServiceSpecs(
			ResourceServiceSpec resourceServiceSpec) {
		listResourceServiceSpecs.remove(resourceServiceSpec);
	}

	public void addResourceServiceSpecs() {

		ResourceServiceSpec resourceServiceSpecs = new ResourceServiceSpec();

		resourceServiceSpecs.setCustomerServiceSpec(getEntity());

		getListResourceServiceSpecs().add(resourceServiceSpecs);
	}

	public void updateComposedAssociations() {

		if (listResourceServiceSpecs != null) {
			getEntity().getResourceServiceSpecs().clear();
			getEntity().getResourceServiceSpecs().addAll(
					listResourceServiceSpecs);
		}
	}

	public void clearLists() {
		listResourceServiceSpecs.clear();

	}

	public com.sasktel.om.omdomain.CustomerServiceSpec findByUnqName(String name) {
		return executeSingleResultNamedQuery(
				"customerServiceSpec.findByUnqName", name);
	}

}
