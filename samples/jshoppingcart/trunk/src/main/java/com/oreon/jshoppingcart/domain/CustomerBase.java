
/**
 * This is generated code - to edit code or override methods use - Customer class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Customer",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class CustomerBase extends Person
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String remarks;

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public abstract Customer customerInstance();

	@Transient
	public String getDisplayName() {
		return remarks + "";
	}

}
