package com.oreon.phonestore.web.action.commerce;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.web.RequestParameter;
import org.primefaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.commerce.Product;

public abstract class ProductActionBase extends BaseAction<Product>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long productId;

	public void setProductId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		instance = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setProductIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getProductId() {
		return (Long) getId();
	}

	public Product getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Product t) {
		this.instance = t;
		loadAssociations();
	}

	public Product getProduct() {
		return (Product) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('product', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('product', 'delete')}")
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
	
	public void handleFileUpload(FileUploadEvent event) {  
		org.primefaces.model.UploadedFile uploadItem = event.getFile();
		if (getInstance().getImage() == null)
			getInstance().setImage(new FileAttachment());
		getInstance().getImage().setName(uploadItem.getFileName());
		getInstance().getImage().setContentType(uploadItem.getContentType());
		getInstance().getImage().setData((uploadItem.getContents())); 
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

	}

	public boolean isWired() {
		return true;
	}

	public Product getDefinedInstance() {
		return (Product) (isIdDefined() ? getInstance() : null);
	}

	public void setProduct(Product t) {
		this.instance = t;
		if (getInstance() != null)
			setProductId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	public com.oreon.phonestore.domain.commerce.Product findByUnqName(
			String name) {
		return executeSingleResultNamedQuery("product.findByUnqName", name);
	}

	public String downloadImage(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getImage());
		return "success";
	}

	//todo 

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		addDefaultAssociations();
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
