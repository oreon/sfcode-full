package com.oreon.phonestore.web.action.commerce;

import com.oreon.phonestore.domain.commerce.Product;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class ProductActionBase extends BaseAction<Product>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Product product;

	public void setProductId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		product = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setProductIdForModalDlg(Long id) {
		setId(id);
		product = loadInstance();
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
	@Restrict("#{s:hasPermission('product', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('product', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Product createInstance() {
		Product instance = super.createInstance();

		return instance;
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
		if (product != null)
			setProductId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	public String downloadImage(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getImage());
		return "success";
	}

	public void imageUploadListener(UploadEvent event) throws Exception {
		UploadItem uploadItem = event.getUploadItem();
		if (getInstance().getImage() == null)
			getInstance().setImage(new FileAttachment());
		getInstance().getImage().setName(uploadItem.getFileName());
		getInstance().getImage().setContentType(uploadItem.getContentType());
		getInstance().getImage().setData(
				FileUtils.readFileToByteArray(uploadItem.getFile()));
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

	public String viewProduct() {
		load(currentEntityId);
		return "viewProduct";
	}

}
