package com.sasktel.om.web.action.omdomain;

import com.sasktel.om.omdomain.ResourceService;

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

import com.sasktel.om.omdomain.ResourceInstance;

public abstract class ResourceServiceActionBase
		extends
			BaseAction<ResourceService> implements java.io.Serializable {

	@Inject
	com.sasktel.om.web.action.omdomain.CustomerServiceAction customerServiceAction;

	@Inject
	com.sasktel.om.web.action.omdomain.TelecomResourceAction telecomResourceAction;

	protected Predicate[] getSearchPredicates(Root<ResourceService> root) {

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
	protected Class<ResourceService> getEntityClass() {
		return ResourceService.class;
	}

	public ResourceService createInstance() {
		return new ResourceService();
	}

	public ResourceService getResourceService() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setResourceService(ResourceService resourceService) {
		this.entity = resourceService;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListResourceInstances();

	}

	public void updateAssociations() {
	}

	protected List<com.sasktel.om.omdomain.ResourceInstance> listResourceInstances = new ArrayList<com.sasktel.om.omdomain.ResourceInstance>();

	void initListResourceInstances() {
		prePopulateListResourceInstances();
		listResourceInstances.addAll(getEntity().getResourceInstances());
	}

	public List<com.sasktel.om.omdomain.ResourceInstance> getListResourceInstances() {
		return listResourceInstances;
	}

	public void setListResourceInstances(
			List<com.sasktel.om.omdomain.ResourceInstance> listResourceInstances) {
		this.listResourceInstances = listResourceInstances;
	}

	public void prePopulateListResourceInstances() {
	}

	public void deleteResourceInstances(int index) {
		listResourceInstances.remove(index);
	}

	public void deleteResourceInstances(ResourceInstance resourceInstance) {
		listResourceInstances.remove(resourceInstance);
	}

	public void addResourceInstances() {

		ResourceInstance resourceInstances = new ResourceInstance();

		resourceInstances.setResourceService(getEntity());

		getListResourceInstances().add(resourceInstances);
	}

	public void updateComposedAssociations() {

		if (listResourceInstances != null) {
			getEntity().getResourceInstances().clear();
			getEntity().getResourceInstances().addAll(listResourceInstances);
		}
	}

	public void clearLists() {
		listResourceInstances.clear();

	}

}
