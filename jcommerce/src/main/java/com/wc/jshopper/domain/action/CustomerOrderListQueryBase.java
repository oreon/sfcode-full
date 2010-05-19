package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.CustomerOrder;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.wc.jshopper.domain.CustomerOrder;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class CustomerOrderListQueryBase
		extends
			BaseQuery<CustomerOrder, Long> {

	//private static final String EJBQL = "select customerOrder from CustomerOrder customerOrder";

	private CustomerOrder customerOrder = new CustomerOrder();

	private static final String[] RESTRICTIONS = {
			"customerOrder.id = #{customerOrderList.customerOrder.id}",

			"customerOrder.specialInstructions = #{customerOrderList.customerOrder.specialInstructions}",

			"customerOrder.employee = #{customerOrderList.customerOrder.employee}",

			"customerOrder.dateCreated <= #{customerOrderList.dateCreatedRange.end}",
			"customerOrder.dateCreated >= #{customerOrderList.dateCreatedRange.begin}",};

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	@Override
	public Class<CustomerOrder> getEntityClass() {
		return CustomerOrder.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
