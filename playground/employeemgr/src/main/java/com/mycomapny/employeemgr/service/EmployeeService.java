package com.mycomapny.employeemgr.service;

import com.mycomapny.employeemgr.domain.Employee;
import com.mycomapny.employeemgr.dao.EmployeeDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - Employee
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface EmployeeService extends EmployeeDao, BaseService<Employee> {

}
