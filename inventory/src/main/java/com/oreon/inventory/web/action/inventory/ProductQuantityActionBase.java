package com.oreon.inventory.web.action.inventory;

import com.oreon.inventory.inventory.ProductQuantity;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class ProductQuantityActionBase
		extends
			BaseAction<ProductQuantity> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ProductQuantity productQuantity;

	@In(create = true, value = "productAction")
	com.oreon.inventory.web.action.inventory.ProductAction productAction;

	@In(create = true, value = "godownAction")
	com.oreon.inventory.web.action.inventory.GodownAction godownAction;

	@DataModel
	private List<ProductQuantity> productQuantityRecordList;

	public void setProductQuantityId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setProductQuantityIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setProductId(Long id) {

		if (id != null && id > 0)
			getInstance().setProduct(productAction.loadFromId(id));

	}

	public Long getProductId() {
		if (getInstance().getProduct() != null)
			return getInstance().getProduct().getId();
		return 0L;
	}

	public void setGodownId(Long id) {

		if (id != null && id > 0)
			getInstance().setGodown(godownAction.loadFromId(id));

	}

	public Long getGodownId() {
		if (getInstance().getGodown() != null)
			return getInstance().getGodown().getId();
		return 0L;
	}

	public Long getProductQuantityId() {
		return (Long) getId();
	}

	public ProductQuantity getEntity() {
		return productQuantity;
	}

	//@Override
	public void setEntity(ProductQuantity t) {
		this.productQuantity = t;
		loadAssociations();
	}

	public ProductQuantity getProductQuantity() {
		return (ProductQuantity) getInstance();
	}

	@Override
	protected ProductQuantity createInstance() {
		return new ProductQuantity();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.inventory.inventory.Product product = productAction
				.getDefinedInstance();
		if (product != null && isNew()) {
			getInstance().setProduct(product);
		}

		com.oreon.inventory.inventory.Godown godown = godownAction
				.getDefinedInstance();
		if (godown != null && isNew()) {
			getInstance().setGodown(godown);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ProductQuantity getDefinedInstance() {
		return (ProductQuantity) (isIdDefined() ? getInstance() : null);
	}

	public void setProductQuantity(ProductQuantity t) {
		this.productQuantity = t;
		loadAssociations();
	}

	@Override
	public Class<ProductQuantity> getEntityClass() {
		return ProductQuantity.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (productQuantity.getProduct() != null) {
			criteria = criteria.add(Restrictions.eq("product.id",
					productQuantity.getProduct().getId()));
		}

		if (productQuantity.getGodown() != null) {
			criteria = criteria.add(Restrictions.eq("godown.id",
					productQuantity.getGodown().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (productQuantity.getProduct() != null) {
			productAction.setInstance(getInstance().getProduct());
		}

		if (productQuantity.getGodown() != null) {
			godownAction.setInstance(getInstance().getGodown());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
