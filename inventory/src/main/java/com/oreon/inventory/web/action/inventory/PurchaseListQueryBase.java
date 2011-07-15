package com.oreon.inventory.web.action.inventory;

import com.oreon.inventory.inventory.Purchase;

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

import com.oreon.inventory.inventory.Purchase;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PurchaseListQueryBase extends BaseQuery<Purchase, Long> {

	private static final String EJBQL = "select purchase from Purchase purchase";

	protected Purchase purchase = new Purchase();

	public Purchase getPurchase() {
		return purchase;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Purchase> getEntityClass() {
		return Purchase.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> quantityRange = new Range<Integer>();
	public Range<Integer> getQuantityRange() {
		return quantityRange;
	}
	public void setQuantity(Range<Integer> quantityRange) {
		this.quantityRange = quantityRange;
	}

	private Range<Double> priceRange = new Range<Double>();
	public Range<Double> getPriceRange() {
		return priceRange;
	}
	public void setPrice(Range<Double> priceRange) {
		this.priceRange = priceRange;
	}

	private static final String[] RESTRICTIONS = {
			"purchase.id = #{purchaseList.purchase.id}",

			"purchase.quantity >= #{purchaseList.quantityRange.begin}",
			"purchase.quantity <= #{purchaseList.quantityRange.end}",

			"purchase.product.id = #{purchaseList.purchase.product.id}",

			"purchase.supplier.id = #{purchaseList.purchase.supplier.id}",

			"purchase.price >= #{purchaseList.priceRange.begin}",
			"purchase.price <= #{purchaseList.priceRange.end}",

			"purchase.dateCreated <= #{purchaseList.dateCreatedRange.end}",
			"purchase.dateCreated >= #{purchaseList.dateCreatedRange.begin}",};

	@Observer("archivedPurchase")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Purchase e) {

		builder.append("\"" + (e.getQuantity() != null ? e.getQuantity() : "")
				+ "\",");

		builder.append("\""
				+ (e.getProduct() != null ? e.getProduct().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getSupplier() != null ? e.getSupplier().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getPrice() != null ? e.getPrice() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Quantity" + ",");

		builder.append("Product" + ",");

		builder.append("Supplier" + ",");

		builder.append("Price" + ",");

		builder.append("\r\n");
	}
}
