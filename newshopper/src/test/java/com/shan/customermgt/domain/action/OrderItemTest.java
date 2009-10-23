package com.shan.customermgt.domain.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.shan.customermgt.domain.OrderItem;

public class OrderItemTest extends
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
