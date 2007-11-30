
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

	protected String email;

	public Integer getEmpCode() {
		return this.empCode;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {

		return this.email;
	}

	public void setEmpCode(Integer empCode) {
		this.empCode = empCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public abstract Employee employeeInstance();

}
