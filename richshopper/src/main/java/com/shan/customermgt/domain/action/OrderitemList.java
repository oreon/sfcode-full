package com.shan.customermgt.domain.action;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.shan.customermgt.domain.OrderItem;

@Name("orderitemList")
public class OrderitemList extends EntityQuery<OrderItem> {

	private static final String EJBQL = "select orderitem from Orderitem orderitem";

	private static final String[] RESTRICTIONS = {};

	private OrderItem orderitem = new OrderItem();

	public OrderitemList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public OrderItem getOrderItem() {
		return orderitem;
	}
}
