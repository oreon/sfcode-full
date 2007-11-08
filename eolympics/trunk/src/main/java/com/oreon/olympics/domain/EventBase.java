
/**
 * This is generated code - to edit code or override methods use - Event class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.olympics.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Event",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class EventBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected Gender gender;

	protected String name;

	public Gender getGender() {
		return this.gender;
	}

	public String getName() {
		return this.name;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	private com.oreon.olympics.domain.Category category;

	public void setCategory(com.oreon.olympics.domain.Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "category_ID", nullable = true)
	public com.oreon.olympics.domain.Category getCategory() {
		return this.category;
	}

	public abstract Event eventInstance();

}
