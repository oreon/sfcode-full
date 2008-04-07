package com.mycomapny.employeemgr.dao;

import com.mycomapny.employeemgr.domain.Employee;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface EmployeeDao extends GenericDAO<Employee> {

}
