package bizobjects.dao;

import bizobjects.Employee;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public interface EmployeeDao {
    public Employee save(Employee employee);

    public void delete(Employee employee);

    public Employee load(Long id);

    public List<Employee> loadAll();

    public List<Employee> findBycode(Object code);

    public List<Employee> findByfirstName(Object firstName);

    public List<Employee> findBylastName(Object lastName);

    public List<Employee> searchByExample(Employee employee);
}
