package com.oreon.phonestore.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class DepartmentDto extends BaseEntity {

	private Set<EmployeeDto> employeesDto = new HashSet<EmployeeDto>();

	protected String name;

	public void setEmployees(Set<EmployeeDto> employeesDto) {
		this.employeesDto = employeesDto;
	}
	public Set<EmployeeDto> getEmployees() {
		return employeesDto;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
