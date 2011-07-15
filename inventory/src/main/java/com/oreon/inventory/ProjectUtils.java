package com.oreon.inventory;

import com.oreon.inventory.inventory.Product;
import com.oreon.inventory.inventory.ProductQuantity;

public class ProjectUtils {
	
	
	public static Integer getCurrentTotal(Product product){
		int currentLevel = 0;
		for (ProductQuantity productQuantity : product.getProductQuantitys()) {
			currentLevel += productQuantity.getQuantity();
		}
		return currentLevel;
	}
	
	

}
