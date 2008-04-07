
/**
 * This is generated code - to edit code or override methods use - Employee class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.mycomapny.employeemgr.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
 @Table(name="Employee",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
/* 
 
 There are 0 constraints.
 */
public abstract class EmployeeBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String firstName;

	protected String lastName;

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private java.util.Set<com.mycomapny.employeemgr.domain.Task> task = new java.util.HashSet<com.mycomapny.employeemgr.domain.Task>();

	public void add(com.mycomapny.employeemgr.domain.Task task) {

		task.setEmployee(employeeInstance());

		this.task.add(task);
	}

	public void remove(com.mycomapny.employeemgr.domain.Task task) {
		this.task.remove(task);
	}

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public java.util.Set<com.mycomapny.employeemgr.domain.Task> getTask() {
		return this.task;
	}

	public void setTask(
			java.util.Set<com.mycomapny.employeemgr.domain.Task> task) {
		this.task = task;
	}

	@Transient
	public java.util.Iterator<com.mycomapny.employeemgr.domain.Task> getTaskIterator() {
		return this.task.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getTaskCount() {
		return this.task.size();
	}
	
	@Override
	@Transient
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return lastName + ", " + firstName;
	}

	public abstract Employee employeeInstance();

}
