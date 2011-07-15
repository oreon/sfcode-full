package com.oreon.inventory.web.action.inventory;

import com.oreon.inventory.inventory.Supplier;

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

import com.oreon.inventory.inventory.Supplier;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SupplierListQueryBase extends BaseQuery<Supplier, Long> {

	private static final String EJBQL = "select supplier from Supplier supplier";

	protected Supplier supplier = new Supplier();

	public Supplier getSupplier() {
		return supplier;
	}

	private com.oreon.inventory.inventory.Product productsToSearch;

	public void setProductsToSearch(
			com.oreon.inventory.inventory.Product productToSearch) {
		this.productsToSearch = productToSearch;
	}

	public com.oreon.inventory.inventory.Product getProductsToSearch() {
		return productsToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Supplier> getEntityClass() {
		return Supplier.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"supplier.id = #{supplierList.supplier.id}",

			"#{supplierList.productsToSearch} in elements(supplier.products)",

			"lower(supplier.name) like concat(lower(#{supplierList.supplier.name}),'%')",

			"lower(supplier.address.primaryPhone) like concat(lower(#{supplierList.supplier.address.primaryPhone}),'%')",

			"lower(supplier.address.address) like concat(lower(#{supplierList.supplier.address.address}),'%')",

			"lower(supplier.address.city) like concat(lower(#{supplierList.supplier.address.city}),'%')",

			"lower(supplier.address.state) like concat(lower(#{supplierList.supplier.address.state}),'%')",

			"supplier.dateCreated <= #{supplierList.dateCreatedRange.end}",
			"supplier.dateCreated >= #{supplierList.dateCreatedRange.begin}",};

	@Observer("archivedSupplier")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Supplier e) {

		builder.append("\"" + (e.getProducts() != null ? e.getProducts() : "")
				+ "\",");

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getAddress() != null ? e.getAddress() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Products" + ",");

		builder.append("Name" + ",");

		builder.append("Address" + ",");

		builder.append("\r\n");
	}
}
