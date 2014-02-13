package com.oreon.phonestore.web.action.commerce;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.commerce.Product;

public class ProductActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Product> {

	ProductAction productAction = new ProductAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Product> getAction() {
		return productAction;
	}

}
