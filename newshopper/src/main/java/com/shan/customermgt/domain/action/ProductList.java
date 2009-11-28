package com.shan.customermgt.domain.action;

import java.util.Arrays;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import com.shan.customermgt.domain.Product;

@Name("productList")
@Scope(ScopeType.CONVERSATION)
public class ProductList extends EntityQuery<Product> {

	/**
     * 
     */
    private static final long serialVersionUID = -2197823398395318313L;

    private static final String EJBQL = "select product from Product product";

	private static final String[] RESTRICTIONS = {"lower(product.name) like concat(lower(#{productList.product.name}),'%')",};

	private Product product = new Product();

	public ProductList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Product getProduct() {
		return product;
	}
}
