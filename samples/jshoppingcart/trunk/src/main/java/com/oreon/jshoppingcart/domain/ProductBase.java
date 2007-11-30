
/**
 * This is generated code - to edit code or override methods use - Product class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Product",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class ProductBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;

	protected Double price;

	public String getName() {
		return this.name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	private com.oreon.jshoppingcart.domain.Category category;

	public void setCategory(com.oreon.jshoppingcart.domain.Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "Category_ID", nullable = true)
	public com.oreon.jshoppingcart.domain.Category getCategory() {
		return this.category;
	}

	public abstract Product productInstance();

}
