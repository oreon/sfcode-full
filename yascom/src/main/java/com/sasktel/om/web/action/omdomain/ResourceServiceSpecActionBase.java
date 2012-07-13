package com.sasktel.om.web.action.omdomain;

import com.sasktel.om.omdomain.ResourceServiceSpec;

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

public abstract class ResourceServiceSpecActionBase
		extends
			BaseAction<ResourceServiceSpec> implements java.io.Serializable {

	@Inject
	com.sasktel.om.web.action.omdomain.CustomerServiceSpecAction customerServiceSpecAction;

	@Inject
	com.sasktel.om.web.action.omdomain.TelecomResourceAction telecomResourceAction;

	protected Predicate[] getSearchPredicates(Root<ResourceServiceSpec> root) {

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
	protected Class<ResourceServiceSpec> getEntityClass() {
		return ResourceServiceSpec.class;
	}

	public ResourceServiceSpec createInstance() {
		return new ResourceServiceSpec();
	}

	public ResourceServiceSpec getResourceServiceSpec() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setResourceServiceSpec(ResourceServiceSpec resourceServiceSpec) {
		this.entity = resourceServiceSpec;
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

}
