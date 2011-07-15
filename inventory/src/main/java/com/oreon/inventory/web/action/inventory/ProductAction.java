package com.oreon.inventory.web.action.inventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.witchcraft.exceptions.BusinessException;
import org.witchcraft.exceptions.UniqueException;

import com.oreon.inventory.inventory.Godown;
import com.oreon.inventory.inventory.Product;
import com.oreon.inventory.inventory.ProductQuantity;

//@Scope(ScopeType.CONVERSATION)
@Name("productAction")
public class ProductAction extends ProductActionBase implements
		java.io.Serializable {

	List<Product> importedProducts = new ArrayList<Product>();
	
	private boolean merge;

	public boolean isMerge() {
		return merge;
	}
	
	public boolean getMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}

	public List<Product> getImportedProducts() {
		return importedProducts;
	}

	public void setImportedProducts(List<Product> importedProducts) {
		this.importedProducts = importedProducts;
	}

	@SuppressWarnings("unchecked")
	public void importUploadListener(UploadEvent event) throws Exception {
		System.out.println("upload listener called");
		UploadItem uploadItem = event.getUploadItem();
		File file = uploadItem.getFile();

		List<String> fileData = FileUtils.readLines(file);
		
		importedProducts.clear();

		int currentCount = 1;

		for (String line : fileData) {
			String[] data = line.split(",");

			try {
				Product product = new Product();
				// product.setId(Long.parseLong(data[0].trim()) );
				product.setName(data[1].trim());
				product.setBarcode(Long.parseLong(data[2].trim()));
				product.setLowStockLevel(Integer.parseInt(data[3].trim()));
				importedProducts.add(product);
				currentCount++;

			} catch (Exception e) {
				addErrorMessage("An error occured at line " + currentCount);
				log.error("error importing data", e);
			}
		}

	}

	public String importData() {
		return "/admin/entities/inventory/product/importProducts.xhtml";
	}

	public String commitImports() {
		
		for (int i = 0; i < importedProducts.size(); i++) {
			try{
				Product importedProduct = importedProducts.get(i);
				if(merge){
					Product existingProduct  = findByUnqName(importedProduct.getName());
					if(existingProduct != null)
						importedProduct.setId(existingProduct.getId());
				}
				persist(importedProduct);
			}
			catch(UniqueException ue){
				addErrorMessage(ue.getMessage());
			}
			catch(BusinessException be){
				addErrorMessage("Unkown Persistence error : " + be.getMessage());
			}
		}

		return "/admin/entities/inventory/product/listProduct.xhtml";
	}

	@In(create = true, value = "godownList")
	GodownListQuery godownListQuery;

	@Override
	public void prePopulateListProductQuantitys() {
		List<Godown> godowns = godownListQuery.getResultList();

		if (getInstance().getId() == null && listProductQuantitys.isEmpty()) {

			for (Godown godown : godowns) {
				ProductQuantity productQuantity = new ProductQuantity();
				productQuantity.setGodown(godown);
				productQuantity.setProduct(getInstance());
				listProductQuantitys.add(productQuantity);
			}
		}
	}

}
