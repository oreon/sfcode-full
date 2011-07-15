package com.oreon.inventory.web.action.inventory;

import com.oreon.inventory.inventory.Product;

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

import com.oreon.inventory.inventory.ProductQuantity;

public abstract class ProductActionBase extends BaseAction<Product>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Product product;

	@DataModel
	private List<Product> productRecordList;

	public void setProductId(Long id) {
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
	public void setProductIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getProductId() {
		return (Long) getId();
	}

	public Product getEntity() {
		return product;
	}

	//@Override
	public void setEntity(Product t) {
		this.product = t;
		loadAssociations();
	}

	public Product getProduct() {
		return (Product) getInstance();
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

	}

	public boolean isWired() {
		return true;
	}

	public Product getDefinedInstance() {
		return (Product) (isIdDefined() ? getInstance() : null);
	}

	public void setProduct(Product t) {
		this.product = t;
		loadAssociations();
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	public com.oreon.inventory.inventory.Product findByUnqName(String name) {
		return executeSingleResultNamedQuery("product.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListProductQuantitys();

		initListSuppliers();
		initListAvailableSuppliers();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.inventory.inventory.ProductQuantity> listProductQuantitys = new ArrayList<com.oreon.inventory.inventory.ProductQuantity>();

	void initListProductQuantitys() {

		if (listProductQuantitys.isEmpty())
			listProductQuantitys.addAll(getInstance().getProductQuantitys());

	}

	public List<com.oreon.inventory.inventory.ProductQuantity> getListProductQuantitys() {

		prePopulateListProductQuantitys();
		return listProductQuantitys;
	}

	public void prePopulateListProductQuantitys() {
	}

	public void setListProductQuantitys(
			List<com.oreon.inventory.inventory.ProductQuantity> listProductQuantitys) {
		this.listProductQuantitys = listProductQuantitys;
	}

	public void deleteProductQuantitys(int index) {
		listProductQuantitys.remove(index);
	}

	@Begin(join = true)
	public void addProductQuantitys() {
		initListProductQuantitys();
		ProductQuantity productQuantitys = new ProductQuantity();

		productQuantitys.setProduct(getInstance());

		getListProductQuantitys().add(productQuantitys);
	}

	protected List<com.oreon.inventory.inventory.Supplier> listSuppliers = new ArrayList<com.oreon.inventory.inventory.Supplier>();

	void initListSuppliers() {

		if (listSuppliers.isEmpty())
			listSuppliers.addAll(getInstance().getSuppliers());

	}

	public List<com.oreon.inventory.inventory.Supplier> getListSuppliers() {

		prePopulateListSuppliers();
		return listSuppliers;
	}

	public void prePopulateListSuppliers() {
	}

	public void setListSuppliers(
			List<com.oreon.inventory.inventory.Supplier> listSuppliers) {
		this.listSuppliers = listSuppliers;
	}

	protected List<com.oreon.inventory.inventory.Supplier> listAvailableSuppliers = new ArrayList<com.oreon.inventory.inventory.Supplier>();

	void initListAvailableSuppliers() {

		listAvailableSuppliers = getEntityManager().createQuery(
				"select r from Supplier r").getResultList();
		listAvailableSuppliers.removeAll(getInstance().getSuppliers());

	}

	@Begin(join = true)
	public List<com.oreon.inventory.inventory.Supplier> getListAvailableSuppliers() {

		prePopulateListAvailableSuppliers();
		return listAvailableSuppliers;
	}

	public void prePopulateListAvailableSuppliers() {
	}

	public void setListAvailableSuppliers(
			List<com.oreon.inventory.inventory.Supplier> listAvailableSuppliers) {
		this.listAvailableSuppliers = listAvailableSuppliers;
	}

	public void updateComposedAssociations() {

		if (listProductQuantitys != null) {
			getInstance().getProductQuantitys().clear();
			getInstance().getProductQuantitys().addAll(listProductQuantitys);
		}

		if (listSuppliers != null) {
			getInstance().getSuppliers().clear();
			getInstance().getSuppliers().addAll(listSuppliers);
		}
	}

	public void clearLists() {
		listProductQuantitys.clear();

		listSuppliers.clear();

	}

}
