package com.oreon.phonestore.web.action.commerce;

import java.math.BigDecimal;
import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.security.Restrict;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.oreon.phonestore.domain.commerce.OrderItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class OrderItemListQueryBase extends BaseQuery<OrderItem, Long> {

	private static final String EJBQL = "select orderItem from OrderItem orderItem";

	protected OrderItem orderItem = new OrderItem();

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
	@Restrict("#{s:hasPermission('orderItem', 'view')}")
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

	public List<OrderItem> getOrderItemsByCustomerOrder(
			com.oreon.phonestore.domain.commerce.CustomerOrder customerOrder) {
		//setMaxResults(10000);
		orderItem.setCustomerOrder(customerOrder);
		return getResultList();
	}

	@Observer("archivedOrderItem")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, OrderItem e) {

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getCustomerOrder() != null ? e.getCustomerOrder()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getProduct() != null ? e.getProduct().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getUnits() != null ? e.getUnits() : "")
				+ "\",");

		builder.append("\""
				+ (e.getSalePrice() != null ? e.getSalePrice() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Remarks" + ",");

		builder.append("CustomerOrder" + ",");

		builder.append("Product" + ",");

		builder.append("Units" + ",");

		builder.append("SalePrice" + ",");

		builder.append("\r\n");
	}
}
