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

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

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

	private Range<Date> dateDeliverByRange = new Range<Date>();

	public Range<Date> getDateDeliverByRange() {
		return dateDeliverByRange;
	}
	public void setDateDeliverBy(Range<Date> dateDeliverByRange) {
		this.dateDeliverByRange = dateDeliverByRange;
	}

	private static final String[] RESTRICTIONS = {
			"customerOrder.id = #{customerOrderList.customerOrder.id}",

			"customerOrder.archived = #{customerOrderList.customerOrder.archived}",

			"lower(customerOrder.remarks) like concat(lower(#{customerOrderList.customerOrder.remarks}),'%')",

			"customerOrder.total >= #{customerOrderList.totalRange.begin}",
			"customerOrder.total <= #{customerOrderList.totalRange.end}",

			"customerOrder.servicingEmployee.id = #{customerOrderList.customerOrder.servicingEmployee.id}",

			"customerOrder.dateDeliverBy >= #{customerOrderList.dateDeliverByRange.begin}",
			"customerOrder.dateDeliverBy <= #{customerOrderList.dateDeliverByRange.end}",

			"customerOrder.customer.id = #{customerOrderList.customerOrder.customer.id}",

			"customerOrder.dateCreated <= #{customerOrderList.dateCreatedRange.end}",
			"customerOrder.dateCreated >= #{customerOrderList.dateCreatedRange.begin}",};

	public LazyDataModel<CustomerOrder> getCustomerOrdersByCustomer(
			final com.oreon.phonestore.domain.commerce.Customer customer) {

		EntityLazyDataModel<CustomerOrder, Long> customerOrderLazyDataModel = new EntityLazyDataModel<CustomerOrder, Long>(
				this) {

			@Override
			public List<CustomerOrder> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				customerOrder.setCustomer(customer);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return customerOrderLazyDataModel;

	}

	@Observer("archivedCustomerOrder")
	public void onArchive() {
		refresh();
	}

	public void setServicingEmployeeId(Long id) {
		if (customerOrder.getServicingEmployee() == null) {
			customerOrder
					.setServicingEmployee(new com.oreon.phonestore.domain.Employee());
		}
		customerOrder.getServicingEmployee().setId(id);
	}

	public Long getServicingEmployeeId() {
		return customerOrder.getServicingEmployee() == null
				? null
				: customerOrder.getServicingEmployee().getId();
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

}
