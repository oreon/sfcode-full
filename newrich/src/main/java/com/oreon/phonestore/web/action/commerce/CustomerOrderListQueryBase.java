package com.oreon.phonestore.web.action.commerce;

import com.oreon.phonestore.domain.commerce.CustomerOrder;

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

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;

import com.oreon.phonestore.domain.commerce.CustomerOrder;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CustomerOrderListQueryBase
		extends
			BaseQuery<CustomerOrder, Long> {

	private static final String EJBQL = "select customerOrder from CustomerOrder customerOrder";

	protected CustomerOrder customerOrder = new CustomerOrder();

	@In(create = true)
	CustomerOrderAction customerOrderAction;

	public CustomerOrderListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	@Override
	public CustomerOrder getInstance() {
		return getCustomerOrder();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('customerOrder', 'view')}")
	public List<CustomerOrder> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<CustomerOrder> getEntityClass() {
		return CustomerOrder.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<BigDecimal> totalRange = new Range<BigDecimal>();

	public Range<BigDecimal> getTotalRange() {
		return totalRange;
	}
	public void setTotal(Range<BigDecimal> totalRange) {
		this.totalRange = totalRange;
	}

	private static final String[] RESTRICTIONS = {
			"customerOrder.id = #{customerOrderList.customerOrder.id}",

			"customerOrder.archived = #{customerOrderList.customerOrder.archived}",

			"lower(customerOrder.remarks) like concat(lower(#{customerOrderList.customerOrder.remarks}),'%')",

			"customerOrder.customer.id = #{customerOrderList.customerOrder.customer.id}",

			"customerOrder.total >= #{customerOrderList.totalRange.begin}",
			"customerOrder.total <= #{customerOrderList.totalRange.end}",

			"customerOrder.dateCreated <= #{customerOrderList.dateCreatedRange.end}",
			"customerOrder.dateCreated >= #{customerOrderList.dateCreatedRange.begin}",};

	@Observer("archivedCustomerOrder")
	public void onArchive() {
		refresh();
	}

	public void setCustomerId(Long id) {
		if (customerOrder.getCustomer() == null) {
			customerOrder
					.setCustomer(new com.oreon.phonestore.domain.commerce.Customer());
		}
		customerOrder.getCustomer().setId(id);
	}

	public Long getCustomerId() {
		return customerOrder.getCustomer() == null ? null : customerOrder
				.getCustomer().getId();
	}

	//@Restrict("#{s:hasPermission('customerOrder', 'delete')}")
	public void archiveById(Long id) {
		customerOrderAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, CustomerOrder e) {

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getCustomer() != null ? e.getCustomer().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getTotal() != null ? e.getTotal() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Remarks" + ",");

		builder.append("Customer" + ",");

		builder.append("Total" + ",");

		builder.append("\r\n");
	}
}
