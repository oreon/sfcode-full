package org.cerebrum.domain;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "Product")
@Name("product")
public class Product extends BusinessEntity {

	protected String name;

	protected String description;

	protected Double price;

	protected byte[] picture;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public byte[] getPicture() {
		return picture;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
