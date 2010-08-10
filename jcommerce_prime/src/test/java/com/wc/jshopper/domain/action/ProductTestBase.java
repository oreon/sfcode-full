package com.wc.jshopper.domain.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.wc.jshopper.domain.Product;

public class ProductTestBase
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
