package org.wc.sample;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.seam.action.BaseAction;

public class ProductTest extends BaseTest<Product> {

	ProductAction productAction = new ProductAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Product> getAction() {
		return productAction;
	}
	
	@Test
	public void createProduct(){
		Product product = new Product();
		product.setName("PRD1");
		productAction.setEntity(product);
		productAction.save();
		System.out.println ( " records " + productAction.getEntityList().size() );
		assert(productAction.getEntityList().size() > 0 );
		 
	}
}
