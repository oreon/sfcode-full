package com.oreon.inventory.web.action.inventory;

import com.oreon.inventory.inventory.ProductQuantity;

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

import com.oreon.inventory.inventory.ProductQuantity;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ProductQuantityListQueryBase
		extends
			BaseQuery<ProductQuantity, Long> {

	private static final String EJBQL = "select productQuantity from ProductQuantity productQuantity";

	protected ProductQuantity productQuantity = new ProductQuantity();

	public ProductQuantity getProductQuantity() {
		return productQuantity;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ProductQuantity> getEntityClass() {
		return ProductQuantity.class;
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

	private static final String[] RESTRICTIONS = {
			"productQuantity.id = #{productQuantityList.productQuantity.id}",

			"productQuantity.product.id = #{productQuantityList.productQuantity.product.id}",

			"productQuantity.godown.id = #{productQuantityList.productQuantity.godown.id}",

			"productQuantity.quantity >= #{productQuantityList.quantityRange.begin}",
			"productQuantity.quantity <= #{productQuantityList.quantityRange.end}",

			"productQuantity.dateCreated <= #{productQuantityList.dateCreatedRange.end}",
			"productQuantity.dateCreated >= #{productQuantityList.dateCreatedRange.begin}",};

	public List<ProductQuantity> getProductQuantitysByProduct(
			com.oreon.inventory.inventory.Product product) {
		//setMaxResults(10000);
		productQuantity.setProduct(product);
		return getResultList();
	}

	@Observer("archivedProductQuantity")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ProductQuantity e) {

		builder.append("\""
				+ (e.getProduct() != null ? e.getProduct().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getGodown() != null ? e.getGodown().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getQuantity() != null ? e.getQuantity() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Product" + ",");

		builder.append("Godown" + ",");

		builder.append("Quantity" + ",");

		builder.append("\r\n");
	}
}
