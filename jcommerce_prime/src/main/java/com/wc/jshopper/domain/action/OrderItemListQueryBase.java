package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.OrderItem;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.wc.jshopper.domain.OrderItem;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class OrderItemListQueryBase extends BaseQuery<OrderItem, Long> {

	//private static final String EJBQL = "select orderItem from OrderItem orderItem";

	private OrderItem orderItem = new OrderItem();

	private Range<Integer> orderItem_quantityRange = new Range<Integer>();
	public Range<Integer> getOrderItem_quantityRange() {
		return orderItem_quantityRange;
	}
	public void setOrderItem_quantity(Range<Integer> orderItem_quantityRange) {
		this.orderItem_quantityRange = orderItem_quantityRange;
	}

	private static final String[] RESTRICTIONS = {
			"orderItem.id = #{orderItemList.orderItem.id}",

			"orderItem.customerOrder = #{orderItemList.orderItem.customerOrder}",

			"orderItem.product = #{orderItemList.orderItem.product}",

			"orderItem.quantity >= #{orderItemList.orderItem_quantityRange.begin}",
			"orderItem.quantity <= #{orderItemList.orderItem_quantityRange.end}",

			"orderItem.dateCreated <= #{orderItemList.dateCreatedRange.end}",
			"orderItem.dateCreated >= #{orderItemList.dateCreatedRange.begin}",};

	public OrderItem getOrderItem() {
		return orderItem;
	}

	@Override
	public Class<OrderItem> getEntityClass() {
		return OrderItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
