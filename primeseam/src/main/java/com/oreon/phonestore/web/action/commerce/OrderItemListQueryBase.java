package com.oreon.phonestore.web.action.commerce;

import com.oreon.phonestore.domain.commerce.OrderItem;

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

import com.oreon.phonestore.domain.commerce.OrderItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class OrderItemListQueryBase extends BaseQuery<OrderItem, Long> {

	private static final String EJBQL = "select orderItem from OrderItem orderItem";

	protected OrderItem orderItem = new OrderItem();

	@In(create = true)
	OrderItemAction orderItemAction;

	public OrderItemListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	@Override
	public OrderItem getInstance() {
		return getOrderItem();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('orderItem', 'view')}")
	public List<OrderItem> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<OrderItem> getEntityClass() {
		return OrderItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> unitsRange = new Range<Integer>();

	public Range<Integer> getUnitsRange() {
		return unitsRange;
	}
	public void setUnits(Range<Integer> unitsRange) {
		this.unitsRange = unitsRange;
	}

	private Range<BigDecimal> salePriceRange = new Range<BigDecimal>();

	public Range<BigDecimal> getSalePriceRange() {
		return salePriceRange;
	}
	public void setSalePrice(Range<BigDecimal> salePriceRange) {
		this.salePriceRange = salePriceRange;
	}

	private static final String[] RESTRICTIONS = {
			"orderItem.id = #{orderItemList.orderItem.id}",

			"orderItem.archived = #{orderItemList.orderItem.archived}",

			"lower(orderItem.remarks) like concat(lower(#{orderItemList.orderItem.remarks}),'%')",

			"orderItem.customerOrder.id = #{orderItemList.orderItem.customerOrder.id}",

			"orderItem.product.id = #{orderItemList.orderItem.product.id}",

			"orderItem.units >= #{orderItemList.unitsRange.begin}",
			"orderItem.units <= #{orderItemList.unitsRange.end}",

			"orderItem.salePrice >= #{orderItemList.salePriceRange.begin}",
			"orderItem.salePrice <= #{orderItemList.salePriceRange.end}",

			"orderItem.dateCreated <= #{orderItemList.dateCreatedRange.end}",
			"orderItem.dateCreated >= #{orderItemList.dateCreatedRange.begin}",};

	public LazyDataModel<OrderItem> getOrderItemsByCustomerOrder(
			final com.oreon.phonestore.domain.commerce.CustomerOrder customerOrder) {

		EntityLazyDataModel<OrderItem, Long> orderItemLazyDataModel = new EntityLazyDataModel<OrderItem, Long>(
				this) {

			@Override
			public List<OrderItem> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				orderItem.setCustomerOrder(customerOrder);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return orderItemLazyDataModel;

	}

	@Observer("archivedOrderItem")
	public void onArchive() {
		refresh();
	}

	public void setCustomerOrderId(Long id) {
		if (orderItem.getCustomerOrder() == null) {
			orderItem
					.setCustomerOrder(new com.oreon.phonestore.domain.commerce.CustomerOrder());
		}
		orderItem.getCustomerOrder().setId(id);
	}

	public Long getCustomerOrderId() {
		return orderItem.getCustomerOrder() == null ? null : orderItem
				.getCustomerOrder().getId();
	}

	public void setProductId(Long id) {
		if (orderItem.getProduct() == null) {
			orderItem
					.setProduct(new com.oreon.phonestore.domain.commerce.Product());
		}
		orderItem.getProduct().setId(id);
	}

	public Long getProductId() {
		return orderItem.getProduct() == null ? null : orderItem.getProduct()
				.getId();
	}

	//@Restrict("#{s:hasPermission('orderItem', 'delete')}")
	public void archiveById(Long id) {
		orderItemAction.archiveById(id);
		refresh();
	}

}
