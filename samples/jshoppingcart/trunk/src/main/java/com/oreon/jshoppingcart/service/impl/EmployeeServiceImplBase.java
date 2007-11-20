package com.oreon.jshoppingcart.service.impl;

import com.oreon.jshoppingcart.domain.Employee;
import com.oreon.jshoppingcart.service.EmployeeService;
import com.oreon.jshoppingcart.dao.EmployeeDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

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

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
