package com.oreon.jshoppingcart.businessservice;

/**
 * This interface manages shopping cart and is responsible for adding and removing items to cart.
 */
public interface ShoppingCartService {

	public void addItem(com.oreon.jshoppingcart.domain.Product product,
			Integer quantity);

	public Integer removeItem(com.oreon.jshoppingcart.domain.Product product);

}
