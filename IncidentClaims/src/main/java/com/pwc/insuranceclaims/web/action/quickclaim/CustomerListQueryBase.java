package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.Customer;

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

import com.pwc.insuranceclaims.quickclaim.Customer;

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

			"lower(customer.firstName) like concat(lower(#{customerList.customer.firstName}),'%')",

			"lower(customer.lastName) like concat(lower(#{customerList.customer.lastName}),'%')",

			"lower(customer.address1) like concat(lower(#{customerList.customer.address1}),'%')",

			"lower(customer.address2) like concat(lower(#{customerList.customer.address2}),'%')",

			"lower(customer.city) like concat(lower(#{customerList.customer.city}),'%')",

			"lower(customer.province) like concat(lower(#{customerList.customer.province}),'%')",

			"lower(customer.postalCode) like concat(lower(#{customerList.customer.postalCode}),'%')",

			"lower(customer.phone) like concat(lower(#{customerList.customer.phone}),'%')",

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
				+ (e.getFirstName() != null
						? e.getFirstName().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getLastName() != null
						? e.getLastName().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getAddress1() != null
						? e.getAddress1().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getAddress2() != null
						? e.getAddress2().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getCity() != null ? e.getCity().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getProvince() != null
						? e.getProvince().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getPostalCode() != null ? e.getPostalCode().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getPhone() != null ? e.getPhone().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("FirstName" + ",");

		builder.append("LastName" + ",");

		builder.append("Address1" + ",");

		builder.append("Address2" + ",");

		builder.append("City" + ",");

		builder.append("Province" + ",");

		builder.append("PostalCode" + ",");

		builder.append("Phone" + ",");

		builder.append("\r\n");
	}
}
