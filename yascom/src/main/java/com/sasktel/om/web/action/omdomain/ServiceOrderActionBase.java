package com.sasktel.om.web.action.omdomain;

import com.sasktel.om.omdomain.ServiceOrder;

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

import com.sasktel.om.omdomain.ServiceOrderItem;
import com.sasktel.om.omdomain.ServiceOrderTrail;

public abstract class ServiceOrderActionBase extends BaseAction<ServiceOrder>
		implements
			java.io.Serializable {

	@Inject
	com.sasktel.om.web.action.omdomain.SubscriberAction subscriberAction;

	protected Predicate[] getSearchPredicates(Root<ServiceOrder> root) {

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
	protected Class<ServiceOrder> getEntityClass() {
		return ServiceOrder.class;
	}

	public ServiceOrder createInstance() {
		return new ServiceOrder();
	}

	public ServiceOrder getServiceOrder() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.entity = serviceOrder;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListServiceOrderItems();

		initListServiceOrderTrails();

	}

	public void updateAssociations() {
	}

	protected List<com.sasktel.om.omdomain.ServiceOrderItem> listServiceOrderItems = new ArrayList<com.sasktel.om.omdomain.ServiceOrderItem>();

	void initListServiceOrderItems() {
		prePopulateListServiceOrderItems();
		listServiceOrderItems.addAll(getEntity().getServiceOrderItems());
	}

	public List<com.sasktel.om.omdomain.ServiceOrderItem> getListServiceOrderItems() {
		return listServiceOrderItems;
	}

	public void setListServiceOrderItems(
			List<com.sasktel.om.omdomain.ServiceOrderItem> listServiceOrderItems) {
		this.listServiceOrderItems = listServiceOrderItems;
	}

	public void prePopulateListServiceOrderItems() {
	}

	public void deleteServiceOrderItems(int index) {
		listServiceOrderItems.remove(index);
	}

	public void deleteServiceOrderItems(ServiceOrderItem serviceOrderItem) {
		listServiceOrderItems.remove(serviceOrderItem);
	}

	public void addServiceOrderItems() {

		ServiceOrderItem serviceOrderItems = new ServiceOrderItem();

		serviceOrderItems.setServiceOrder(getEntity());

		getListServiceOrderItems().add(serviceOrderItems);
	}

	protected List<com.sasktel.om.omdomain.ServiceOrderTrail> listServiceOrderTrails = new ArrayList<com.sasktel.om.omdomain.ServiceOrderTrail>();

	void initListServiceOrderTrails() {
		prePopulateListServiceOrderTrails();
		listServiceOrderTrails.addAll(getEntity().getServiceOrderTrails());
	}

	public List<com.sasktel.om.omdomain.ServiceOrderTrail> getListServiceOrderTrails() {
		return listServiceOrderTrails;
	}

	public void setListServiceOrderTrails(
			List<com.sasktel.om.omdomain.ServiceOrderTrail> listServiceOrderTrails) {
		this.listServiceOrderTrails = listServiceOrderTrails;
	}

	public void prePopulateListServiceOrderTrails() {
	}

	public void deleteServiceOrderTrails(int index) {
		listServiceOrderTrails.remove(index);
	}

	public void deleteServiceOrderTrails(ServiceOrderTrail serviceOrderTrail) {
		listServiceOrderTrails.remove(serviceOrderTrail);
	}

	public void addServiceOrderTrails() {

		ServiceOrderTrail serviceOrderTrails = new ServiceOrderTrail();

		serviceOrderTrails.setServiceOrder(getEntity());

		getListServiceOrderTrails().add(serviceOrderTrails);
	}

	public void updateComposedAssociations() {

		if (listServiceOrderItems != null) {
			getEntity().getServiceOrderItems().clear();
			getEntity().getServiceOrderItems().addAll(listServiceOrderItems);
		}

		if (listServiceOrderTrails != null) {
			getEntity().getServiceOrderTrails().clear();
			getEntity().getServiceOrderTrails().addAll(listServiceOrderTrails);
		}
	}

	public void clearLists() {
		listServiceOrderItems.clear();
		listServiceOrderTrails.clear();

	}

}
