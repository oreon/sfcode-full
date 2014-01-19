package com.oreon.phonestore.domain.commerce.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class OrderItemDto extends BaseEntity {

	protected String remarks;

	protected CustomerOrderDto customerOrderDto;

	protected ProductDto productDto;

	protected Integer units;

	protected BigDecimal salePrice;

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setCustomerOrder(CustomerOrderDto customerOrderDto) {
		this.customerOrderDto = customerOrderDto;
	}

	public CustomerOrderDto getCustomerOrder() {
		return customerOrderDto;
	}

	public void setProduct(ProductDto productDto) {
		this.productDto = productDto;
	}

	public ProductDto getProduct() {
		return productDto;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getUnits() {
		return units;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

}
