
/**
 * This is generated code - to edit code or override methods use - Task class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.mycomapny.employeemgr.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
 @Table(name="Task",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
/* 
 
 There are 0 constraints.
 */
public abstract class TaskBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String title;

	protected String description;

	protected Status status;

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	private com.mycomapny.employeemgr.domain.Employee employee = new com.mycomapny.employeemgr.domain.Employee();

	public void setEmployee(com.mycomapny.employeemgr.domain.Employee employee) {
		this.employee = employee;
	}

	@ManyToOne
	@JoinColumn(name = "Employee_ID", nullable = false)
	public com.mycomapny.employeemgr.domain.Employee getEmployee() {
		return this.employee;
	}

	public abstract Task taskInstance();

}
