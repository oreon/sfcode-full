package com.oreon.phonestore.web.action.commerce;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.commerce.OrderItem;

public class OrderItemActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<OrderItem> {

	OrderItemAction orderItemAction = new OrderItemAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<OrderItem> getAction() {
		return orderItemAction;
	}

}
