package com.oreon.smartsis.web.action.fees;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.fees.FeeItem;

public class FeeItemActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FeeItem> {

	FeeItemAction feeItemAction = new FeeItemAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FeeItem> getAction() {
		return feeItemAction;
	}

}
