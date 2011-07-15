package com.oreon.inventory.web.action.inventory;

import com.oreon.inventory.inventory.Product;

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

import com.oreon.inventory.inventory.Product;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ProductListQueryBase extends BaseQuery<Product, Long> {

	private static final String EJBQL = "select product from Product product";

	protected Product product = new Product();

	public Product getProduct() {
		return product;
	}

	private com.oreon.inventory.inventory.Supplier suppliersToSearch;

	public void setSuppliersToSearch(
			com.oreon.inventory.inventory.Supplier supplierToSearch) {
		this.suppliersToSearch = supplierToSearch;
	}

	public com.oreon.inventory.inventory.Supplier getSuppliersToSearch() {
		return suppliersToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> lowStockLevelRange = new Range<Integer>();
	public Range<Integer> getLowStockLevelRange() {
		return lowStockLevelRange;
	}
	public void setLowStockLevel(Range<Integer> lowStockLevelRange) {
		this.lowStockLevelRange = lowStockLevelRange;
	}

	private static final String[] RESTRICTIONS = {
			"product.id = #{productList.product.id}",

			"lower(product.name) like concat(lower(#{productList.product.name}),'%')",

			"product.barcode = #{productList.product.barcode}",

			"product.lowStockLevel >= #{productList.lowStockLevelRange.begin}",
			"product.lowStockLevel <= #{productList.lowStockLevelRange.end}",

			"#{productList.suppliersToSearch} in elements(product.suppliers)",

			"product.dateCreated <= #{productList.dateCreatedRange.end}",
			"product.dateCreated >= #{productList.dateCreatedRange.begin}",};

	@Observer("archivedProduct")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Product e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getBarcode() != null ? e.getBarcode() : "")
				+ "\",");

		builder.append("\""
				+ (e.getLowStockLevel() != null ? e.getLowStockLevel() : "")
				+ "\",");

		builder.append("\""
				+ (e.getSuppliers() != null ? e.getSuppliers() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Barcode" + ",");

		builder.append("LowStockLevel" + ",");

		builder.append("Suppliers" + ",");

		builder.append("\r\n");
	}
}
