package com.oreon.inventory.web.action.inventory;

import com.oreon.inventory.inventory.Purchase;

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

public abstract class PurchaseActionBase extends BaseAction<Purchase>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Purchase purchase;

	@In(create = true, value = "productAction")
	com.oreon.inventory.web.action.inventory.ProductAction productAction;

	@In(create = true, value = "supplierAction")
	com.oreon.inventory.web.action.inventory.SupplierAction supplierAction;

	@DataModel
	private List<Purchase> purchaseRecordList;

	public void setPurchaseId(Long id) {
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
	public void setPurchaseIdForModalDlg(Long id) {
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

	public void setSupplierId(Long id) {

		if (id != null && id > 0)
			getInstance().setSupplier(supplierAction.loadFromId(id));

	}

	public Long getSupplierId() {
		if (getInstance().getSupplier() != null)
			return getInstance().getSupplier().getId();
		return 0L;
	}

	public Long getPurchaseId() {
		return (Long) getId();
	}

	public Purchase getEntity() {
		return purchase;
	}

	//@Override
	public void setEntity(Purchase t) {
		this.purchase = t;
		loadAssociations();
	}

	public Purchase getPurchase() {
		return (Purchase) getInstance();
	}

	@Override
	protected Purchase createInstance() {
		return new Purchase();
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

		com.oreon.inventory.inventory.Supplier supplier = supplierAction
				.getDefinedInstance();
		if (supplier != null && isNew()) {
			getInstance().setSupplier(supplier);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Purchase getDefinedInstance() {
		return (Purchase) (isIdDefined() ? getInstance() : null);
	}

	public void setPurchase(Purchase t) {
		this.purchase = t;
		loadAssociations();
	}

	@Override
	public Class<Purchase> getEntityClass() {
		return Purchase.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (purchase.getProduct() != null) {
			criteria = criteria.add(Restrictions.eq("product.id", purchase
					.getProduct().getId()));
		}

		if (purchase.getSupplier() != null) {
			criteria = criteria.add(Restrictions.eq("supplier.id", purchase
					.getSupplier().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (purchase.getProduct() != null) {
			productAction.setInstance(getInstance().getProduct());
		}

		if (purchase.getSupplier() != null) {
			supplierAction.setInstance(getInstance().getSupplier());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
