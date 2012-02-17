package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.Customer;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import com.pcas.datapkg.domain.inventory.Customer;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CustomerListQueryBase extends BaseQuery<Customer, Long> {

	private static final String EJBQL = "select customer from Customer customer";

	protected Customer customer = new Customer();

	public Customer getCustomer() {
		return customer;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"customer.id = #{customerList.customer.id}",

			"lower(customer.name) like concat(lower(#{customerList.customer.name}),'%')",

			"lower(customer.country) like concat(lower(#{customerList.customer.country}),'%')",

			"lower(customer.telephone) like concat(lower(#{customerList.customer.telephone}),'%')",

			"customer.dateCreated <= #{customerList.dateCreatedRange.end}",
			"customer.dateCreated >= #{customerList.dateCreatedRange.begin}",};

	@Observer("archivedCustomer")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Customer e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getCountry() != null
						? e.getCountry().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getTelephone() != null
						? e.getTelephone().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Country" + ",");

		builder.append("Telephone" + ",");

		builder.append("\r\n");
	}
}
