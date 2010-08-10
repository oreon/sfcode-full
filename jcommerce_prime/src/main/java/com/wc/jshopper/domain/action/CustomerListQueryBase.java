package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.Customer;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.wc.jshopper.domain.Customer;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class CustomerListQueryBase extends BaseQuery<Customer, Long> {

	//private static final String EJBQL = "select customer from Customer customer";

	private Customer customer = new Customer();

	private static final String[] RESTRICTIONS = {
			"customer.id = #{customerList.customer.id}",

			"lower(customer.firstName) like concat(lower(#{customerList.customer.firstName}),'%')",

			"lower(customer.lastName) like concat(lower(#{customerList.customer.lastName}),'%')",

			"lower(customer.contactDetails.primaryPhone) like concat(lower(#{customerList.customer.contactDetails.primaryPhone}),'%')",

			"lower(customer.contactDetails.secondaryPhone) like concat(lower(#{customerList.customer.contactDetails.secondaryPhone}),'%')",

			"lower(customer.contactDetails.email) like concat(lower(#{customerList.customer.contactDetails.email}),'%')",

			"customer.customerClass = #{customerList.customer.customerClass}",

			"customer.user = #{customerList.customer.user}",

			"customer.dateCreated <= #{customerList.dateCreatedRange.end}",
			"customer.dateCreated >= #{customerList.dateCreatedRange.begin}",};

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
