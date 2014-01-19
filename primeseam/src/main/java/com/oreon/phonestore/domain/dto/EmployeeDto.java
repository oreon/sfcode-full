package com.oreon.phonestore.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class EmployeeDto extends com.oreon.phonestore.domain.Person {

	protected DepartmentDto departmentDto;

	protected String employeeNumber;

	protected EmployeeType employeeType;

	public void setDepartment(DepartmentDto departmentDto) {
		this.departmentDto = departmentDto;
	}

	public DepartmentDto getDepartment() {
		return departmentDto;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

}
