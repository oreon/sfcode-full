package com.oreon.inventory.web.action.inventory;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.inventory.inventory.Purchase;

public class PurchaseActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Purchase> {

	PurchaseAction purchaseAction = new PurchaseAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Purchase> getAction() {
		return purchaseAction;
	}

}
