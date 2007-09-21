package bizobjects.service.impl;

import bizobjects.Employee;

import bizobjects.dao.EmployeeDao;

import bizobjects.service.EmployeeService;
import bizobjects.service.EmployeeService;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //// Delegate all crud operations to the Dao ////
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
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

    public List<Employee> findBycode(Object code) {
        return employeeDao.findBycode(code);
    }

    public List<Employee> findByfirstName(Object firstName) {
        return employeeDao.findByfirstName(firstName);
    }

    public List<Employee> findBylastName(Object lastName) {
        return employeeDao.findBylastName(lastName);
    }

    public List<Employee> searchByExample(Employee employee) {
        return employeeDao.searchByExample(employee);
    }

    /*
    public List query(String queryString, Object... params) {
            return basicDAO.query(queryString, params);
    }*/
}
