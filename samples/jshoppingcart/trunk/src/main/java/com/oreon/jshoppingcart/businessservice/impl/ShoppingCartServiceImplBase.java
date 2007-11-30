
/**
 * This is generated code - to edit code or override methods use - ShoppingCartService class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.businessservice.impl;
import com.oreon.jshoppingcart.businessservice.ShoppingCartService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ShoppingCartServiceImplBase implements ShoppingCartService {

	private static final Logger log = Logger
			.getLogger(ShoppingCartServiceImplBase.class);

	protected Integer additionalTax;

	protected String maxItemsMessage;

	public Integer getAdditionalTax() {
		return this.additionalTax;
	}

	public String getMaxItemsMessage() {
		return this.maxItemsMessage;
	}

	public void setAdditionalTax(Integer additionalTax) {
		this.additionalTax = additionalTax;
	}

	public void setMaxItemsMessage(String maxItemsMessage) {
		this.maxItemsMessage = maxItemsMessage;
	}

	public void addItem(com.oreon.jshoppingcart.domain.Product product,
			Integer quantity) {
	}

	public Integer removeItem(com.oreon.jshoppingcart.domain.Product product) {
		return null;
		//should return Integer
	}

}
