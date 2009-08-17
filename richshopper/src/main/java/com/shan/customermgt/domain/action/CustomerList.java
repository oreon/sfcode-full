package com.shan.customermgt.domain.action;


import java.util.Date;
import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.shan.customermgt.domain.Customer;

@Name("customerList")
public class CustomerList extends EntityQuery<Customer> {

	private static final String EJBQL = "select customer from Customer customer";
	
	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

	
	private static final String[] RESTRICTIONS = {
			"lower(customer.firstName) like concat(lower(#{customerList.customer.firstName}),'%')",
			"lower(customer.lastName) like concat(lower(#{customerList.customer.lastName}),'%')",
			"customer.dateCreated >= #{customerList.dateCreatedRange.end}",
			//"customer.dateCreated >= customerList.dateCreatedRange.begin",
	};

	private Customer customer = new Customer();

	public CustomerList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Customer getCustomer() {
		return customer;
	}
}
