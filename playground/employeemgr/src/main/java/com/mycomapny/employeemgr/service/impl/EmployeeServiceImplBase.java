
/**
 * This is generated code - to edit code or override methods use - Employee class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.mycomapny.employeemgr.service.impl;

import com.mycomapny.employeemgr.domain.Employee;
import com.mycomapny.employeemgr.service.EmployeeService;
import com.mycomapny.employeemgr.dao.EmployeeDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.Range;
import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class EmployeeServiceImplBase extends BaseServiceImpl<Employee>
		implements
			EmployeeService {

	private static final Logger log = Logger
			.getLogger(EmployeeServiceImplBase.class);

	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public GenericDAO<Employee> getDao() {
		return employeeDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Employee save(Employee employee) {
		Long id = employee.getId();

		employeeDao.save(employee);

		return employee;
	}

	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

	public Employee load(Long id) {
		return employeeDao.load(id);
	}

	public List<Employee> loadAll() {
		return employeeDao.loadAll();
	}

	public List<Employee> searchByExample(Employee employee) {
		return employeeDao.searchByExample(employee);
	}

	public List<Employee> searchByExample(Employee arg0, List<Range> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 public List query(String queryString, Object... params) {
	 return basicDAO.query(queryString, params);
	 }*/

}
