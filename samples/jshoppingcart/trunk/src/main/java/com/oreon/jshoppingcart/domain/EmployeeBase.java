
/**
 * This is generated code - to edit code or override methods use - Employee class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Employee",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class EmployeeBase extends Person
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer empCode;

	public Integer getEmpCode() {
		return this.empCode;
	}

	public void setEmpCode(Integer empCode) {
		this.empCode = empCode;
	}

	public abstract Employee employeeInstance();

	@Transient
	public String getDisplayName() {
		return empCode + "";
	}

}
