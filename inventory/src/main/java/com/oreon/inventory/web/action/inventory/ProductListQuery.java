
package com.oreon.inventory.web.action.inventory;



import org.jboss.seam.annotations.Name;

import com.oreon.inventory.inventory.Product;
	
	
@Name("productList")
//@Scope(ScopeType.CONVERSATION)
public class ProductListQuery extends ProductListQueryBase implements java.io.Serializable{
	
	@Override
	public Product getProduct() {
		if (!isPostBack()) {
			product.setBarcode(null);
			product.setLowStockLevel(null);
			product.setCurrentLevel(null);
		}
		return product;
	}
}
