package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.Product;

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

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public class ProductActionBase extends BaseAction<Product>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Product product;

	@In(create = true, value = "categoryAction")
	com.wc.jshopper.domain.action.CategoryAction categoryAction;

	@DataModel
	private List<Product> productRecordList;

	public void setProductId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getProductId() {
		return (Long) getId();
	}

	@Factory("productRecordList")
	@Observer("archivedProduct")
	public void findRecords() {
		search();
	}

	public Product getEntity() {
		return product;
	}

	@Override
	public void setEntity(Product t) {
		this.product = t;
		loadAssociations();
	}

	public Product getProduct() {
		return getInstance();
	}

	@Override
	protected Product createInstance() {
		return new Product();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.wc.jshopper.domain.Category category = categoryAction
				.getDefinedInstance();
		if (category != null) {
			getInstance().setCategory(category);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Product getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setProduct(Product t) {
		this.product = t;
		loadAssociations();
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public void setEntityList(List<Product> list) {
		this.productRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (product.getCategory() != null) {
			criteria = criteria.add(Restrictions.eq("category.id", product
					.getCategory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (product.getCategory() != null) {
			categoryAction.setEntity(getEntity().getCategory());
		}

	}

	public void updateAssociations() {

	}

	public List<Product> getEntityList() {
		if (productRecordList == null) {
			findRecords();
		}
		return productRecordList;
	}

	public List findSimilar() {

		return executeSingleResultNamedQuery("findSimilar");

	}

}
