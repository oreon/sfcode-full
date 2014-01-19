package com.oreon.phonestore.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class ContactDetailsDto {

	protected String phone;

	protected String secondaryPhone;

	protected String city;

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

}
