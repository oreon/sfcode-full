package bizobjects.web.jsf;

import bizobjects.Employee;

import java.util.ArrayList;
import java.util.List;


public class EmployeeBackingBean {
    private Employee employee = new Employee();

    public Employee getEmployee() {
        return employee;
    }

    public void set(Employee employee) {
        this.employee = employee;
    }

    /**Write values to the database
    * @return - a list of
    */
    public String update() {
        return "success";
    }

    /**Get a list of all employees
    * @return - a list of employees
    */
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();

        return employees;
    }
}
