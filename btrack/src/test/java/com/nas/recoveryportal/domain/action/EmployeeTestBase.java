package com.nas.recoveryportal.domain.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recoveryportal.domain.Employee;

public class EmployeeTestBase
		extends
			org.witchcraft.action.test.BaseTest<Employee> {

	EmployeeAction employeeAction = new EmployeeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Employee> getAction() {
		return employeeAction;
	}

}
