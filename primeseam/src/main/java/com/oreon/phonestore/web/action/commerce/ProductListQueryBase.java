package com.oreon.phonestore.web.action.commerce;

import com.oreon.phonestore.domain.commerce.Product;

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

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.phonestore.domain.commerce.Product;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ProductListQueryBase extends BaseQuery<Product, Long> {

	private static final String EJBQL = "select product from Product product";

	protected Product product = new Product();

	@In(create = true)
	ProductAction productAction;

	public ProductListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Product getProduct() {
		return product;
	}

	@Override
	public Product getInstance() {
		return getProduct();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('product', 'view')}")
	public List<Product> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<BigDecimal> priceRange = new Range<BigDecimal>();

	public Range<BigDecimal> getPriceRange() {
		return priceRange;
	}
	public void setPrice(Range<BigDecimal> priceRange) {
		this.priceRange = priceRange;
	}

	private static final String[] RESTRICTIONS = {
			"product.id = #{productList.product.id}",

			"product.archived = #{productList.product.archived}",

			"lower(product.name) like concat(lower(#{productList.product.name}),'%')",

			"product.price >= #{productList.priceRange.begin}",
			"product.price <= #{productList.priceRange.end}",

			"lower(product.description) like concat(lower(#{productList.product.description}),'%')",

			"product.dateCreated <= #{productList.dateCreatedRange.end}",
			"product.dateCreated >= #{productList.dateCreatedRange.begin}",};

	@Observer("archivedProduct")
	public void onArchive() {
		refresh();
	}

	//@Restrict("#{s:hasPermission('product', 'delete')}")
	public void archiveById(Long id) {
		productAction.archiveById(id);
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

		builder.append("\"" + (e.getPrice() != null ? e.getPrice() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Price" + ",");

		builder.append("Description" + ",");

		builder.append("\r\n");
	}
}
