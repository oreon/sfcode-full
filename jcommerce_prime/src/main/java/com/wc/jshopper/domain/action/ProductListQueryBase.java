package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.Product;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.wc.jshopper.domain.Product;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ProductListQueryBase extends BaseQuery<Product, Long> {

	//private static final String EJBQL = "select product from Product product";

	private Product product = new Product();

	private Range<Double> product_priceRange = new Range<Double>();
	public Range<Double> getProduct_priceRange() {
		return product_priceRange;
	}
	public void setProduct_price(Range<Double> product_priceRange) {
		this.product_priceRange = product_priceRange;
	}

	private static final String[] RESTRICTIONS = {
			"product.id = #{productList.product.id}",

			"lower(product.name) like concat(lower(#{productList.product.name}),'%')",

			"product.price >= #{productList.product_priceRange.begin}",
			"product.price <= #{productList.product_priceRange.end}",

			"product.image = #{productList.product.image}",

			"product.description = #{productList.product.description}",

			"product.category = #{productList.product.category}",

			"product.dateCreated <= #{productList.dateCreatedRange.end}",
			"product.dateCreated >= #{productList.dateCreatedRange.begin}",};

	public Product getProduct() {
		return product;
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
