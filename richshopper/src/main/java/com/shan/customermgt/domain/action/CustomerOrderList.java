package com.shan.customermgt.domain.action;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.shan.customermgt.domain.CustomerOrder;

@Name("customerorderList")
public class CustomerOrderList extends EntityQuery<CustomerOrder> {

	private static final String EJBQL = "select customerorder from CustomerOrder customerorder";

	private static final String[] RESTRICTIONS = {"lower(customerorder.notes) like concat(lower(#{customerorderList.customerorder.notes}),'%')",};

	private CustomerOrder customerOrder = new CustomerOrder();

	public CustomerOrderList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}
}
