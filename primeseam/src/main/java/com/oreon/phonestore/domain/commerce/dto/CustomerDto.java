package com.oreon.phonestore.domain.commerce.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class CustomerDto extends com.oreon.phonestore.domain.Person {

	protected CustomerType type;

	public void setType(CustomerType type) {
		this.type = type;
	}

	public CustomerType getType() {
		return type;
	}

}
