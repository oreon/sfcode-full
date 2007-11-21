
/**
 * This is generated code - to edit code or override methods use - Person class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.domain;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

@MappedSuperclass
public abstract class PersonBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String firstName;

	protected String lastName;

	@Column(nullable = true, unique = false)
	public String getFirstName() {

		return this.firstName;
	}

	@Column(nullable = false, unique = false)
	public String getLastName() {

		return this.lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public abstract Person personInstance();

}
