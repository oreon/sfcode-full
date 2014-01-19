package com.oreon.phonestore.domain.commerce.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class CustomerOrderDto extends BaseEntity {

	private Set<OrderItemDto> orderItemsDto = new HashSet<OrderItemDto>();

	protected String remarks;

	protected CustomerDto customerDto;

	protected BigDecimal total;

	protected com.oreon.phonestore.domain.dto.EmployeeDto servicingEmployeeDto;

	public void setOrderItems(Set<OrderItemDto> orderItemsDto) {
		this.orderItemsDto = orderItemsDto;
	}
	public Set<OrderItemDto> getOrderItems() {
		return orderItemsDto;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setCustomer(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

	public CustomerDto getCustomer() {
		return customerDto;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setServicingEmployee(
			com.oreon.phonestore.domain.dto.EmployeeDto servicingEmployeeDto) {
		this.servicingEmployeeDto = servicingEmployeeDto;
	}

	public com.oreon.phonestore.domain.dto.EmployeeDto getServicingEmployee() {
		return servicingEmployeeDto;
	}

}
